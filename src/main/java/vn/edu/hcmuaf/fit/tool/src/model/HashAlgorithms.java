package vn.edu.hcmuaf.fit.tool.src.model;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashAlgorithms {
	public String hash(String data, String nameHash) throws Exception {
		byte[] bytes = data.getBytes();
		MessageDigest md = MessageDigest.getInstance(nameHash);
		byte[] digest = md.digest(bytes);
		BigInteger re = new BigInteger(1, digest);
		return re.toString(16);
	}

	public String hashFile(String src, String nameHash)
			throws NoSuchAlgorithmException, FileNotFoundException, IOException {
		File file = new File(src);
		if (file.exists() == false) {
			throw new FileNotFoundException("File không tồn tại");
		}

		MessageDigest md = MessageDigest.getInstance(nameHash);

		try (DigestInputStream di = new DigestInputStream(new BufferedInputStream(new FileInputStream(file)), md)) {
			byte[] buff = new byte[1024];
			int read;
			do {
				read = di.read(buff);
			} while (read != -1);

			byte[] digest = di.getMessageDigest().digest();
			BigInteger re = new BigInteger(1, digest);
			return re.toString(16);
		}
	}
}