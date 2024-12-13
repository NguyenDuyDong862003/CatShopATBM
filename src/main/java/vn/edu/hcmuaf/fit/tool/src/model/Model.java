package vn.edu.hcmuaf.fit.tool.src.model;

public class Model {
	private DS dsa;
	public static final Integer[] DSA_KEY_SIZES = new Integer[] { 1024, 2048, 3072 };
	public static String nameFileSavePrivateKeyDSA = "DSA_%d private key.dat";
	public static String nameFileSavePublicKeyDSA = "DSA_%d public key.dat";

	public Model() {
		dsa = new DS();
	}

	public DS getDsa() {
		return dsa;
	}
}