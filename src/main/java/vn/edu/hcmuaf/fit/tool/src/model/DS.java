package vn.edu.hcmuaf.fit.tool.src.model;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class DS {
	private KeyPair keyPair;
	private SecureRandom secureRandom;
	private Signature signature;
	private PublicKey publicKey;
	private PrivateKey privateKey;

	public DS() {
		try {
			signature = Signature.getInstance("DSA", "SUN");
		} catch (NoSuchAlgorithmException | NoSuchProviderException e) {
			e.printStackTrace();
		}
	}

	public void genKey(int keysize) {
		KeyPairGenerator generator;
		try {
			generator = KeyPairGenerator.getInstance("DSA", "SUN");
			secureRandom = SecureRandom.getInstance("SHA1PRNG", "SUN");
			generator.initialize(keysize, secureRandom);
			keyPair = generator.genKeyPair();
			publicKey = keyPair.getPublic();
			privateKey = keyPair.getPrivate();
		} catch (NoSuchAlgorithmException | NoSuchProviderException e) {
			e.printStackTrace();
		}
	}

	public PublicKey getPublicKey() {
		return publicKey;
	}

	public void setPublicKey(String publicKeyBase64) throws NoSuchAlgorithmException, InvalidKeySpecException {
		byte[] decodedKey = Base64.getDecoder().decode(publicKeyBase64);
		// Tạo đối tượng PublicKey từ mảng byte đã giải mã
		X509EncodedKeySpec keySpec = new X509EncodedKeySpec(decodedKey);
		KeyFactory keyFactory = KeyFactory.getInstance("DSA");
		this.publicKey = keyFactory.generatePublic(keySpec);
	}

	public PrivateKey getPrivateKey() {
		return privateKey;
	}

	public void setPrivateKey(String privateKeyBase64) throws NoSuchAlgorithmException, InvalidKeySpecException {
		byte[] decodedKey = Base64.getDecoder().decode(privateKeyBase64);
		// Tạo đối tượng PKCS8EncodedKeySpec từ mảng byte đã giải mã
		PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(decodedKey);
		// Sử dụng KeyFactory để tạo PrivateKey cho DSA
		KeyFactory keyFactory = KeyFactory.getInstance("DSA");
		this.privateKey = keyFactory.generatePrivate(keySpec);
	}

	public String sign(String mes) throws InvalidKeyException, SignatureException {
		byte[] data = mes.getBytes();
		signature.initSign(privateKey);
		signature.update(data);
		byte[] sign = signature.sign();
		return Base64.getEncoder().encodeToString(sign);
	}

	public String signFile(String src) throws InvalidKeyException, SignatureException, IOException {
//		byte[] data = src.getBytes();
		signature.initSign(privateKey);

		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(src));
		byte[] buff = new byte[1024];
		int read;
		while ((read = bis.read(buff)) != -1) {
			signature.update(buff, 0, read);
		}
		bis.close();

		byte[] sign = signature.sign();
		return Base64.getEncoder().encodeToString(sign);
	}

	public boolean verify(String mes, String sign) throws InvalidKeyException, SignatureException {
		signature.initVerify(publicKey);
		byte[] data = mes.getBytes();
		byte[] signValue = Base64.getDecoder().decode(sign);
		signature.update(data);
		return signature.verify(signValue);
	}

	public boolean verifyFile(String src, String sign) throws InvalidKeyException, SignatureException, IOException {
		signature.initVerify(publicKey);
//		byte[] data = src.getBytes();
		byte[] signValue = Base64.getDecoder().decode(sign);

		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(src));
		byte[] buff = new byte[1024];
		int read;
		while ((read = bis.read(buff)) != -1) {
			signature.update(buff, 0, read);
		}
		bis.close();

		return signature.verify(signValue);
	}
}