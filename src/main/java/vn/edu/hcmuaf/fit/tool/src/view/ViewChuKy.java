package vn.edu.hcmuaf.fit.tool.src.view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

public class ViewChuKy extends JPanel {
	private static final long serialVersionUID = 1L;

	private JComboBox<Integer> jComboKeySize;
	private JButton btnGenPairKey;
	private JTextArea outputGenPublicKey;
	private JTextArea outputGenPrivateKey;
	private JButton btnChooseFolderSavePublicKey;
	private JButton btnChooseFolderSavePrivateKey;

	private JTextArea inputPlainText;
	private JTextArea inputPrivateKey;
	private JButton btnSign;
	private JTextArea outputDigitalSignature;

	public ViewChuKy() {
		// gồm 3 phần: + tạo khóa theo kích thước dc chọn trong combo box (có area để
		// copy key, có phần lưu khóa luôn)
		// + Ký DSA (text) (trong này cũng có input key)
		// + Xác thực DSA (text) (trong này cũng có input key)
		// + Ký DSA (file) (trong này cũng có input key)
		// + Xác thực DSA (file) (trong này cũng có input key)

		JPanel panelContainPart = new JPanel();
		this.add(panelContainPart);
		panelContainPart.setLayout(new BoxLayout(panelContainPart, BoxLayout.Y_AXIS));

		JPanel jPanelGenAndSaveKey = new JPanel(new BorderLayout());
		panelContainPart.add(jPanelGenAndSaveKey);
		jPanelGenAndSaveKey.setBorder(new TitledBorder("Tạo và lưu cặp khóa DSA"));

		JPanel jPanelTemp;

		jPanelTemp = new JPanel();
		jPanelGenAndSaveKey.add(jPanelTemp, BorderLayout.NORTH);

		jComboKeySize = new JComboBox<Integer>(new Integer[] { 1 });
		jPanelTemp.add(jComboKeySize);
		jComboKeySize.setBorder(new TitledBorder("Key size (bit)"));
		int heightComboBox = (int) jComboKeySize.getPreferredSize().getHeight();
		jComboKeySize.setPreferredSize(new Dimension(100, heightComboBox));

		btnGenPairKey = new JButton("Gen new pair key");
		jPanelTemp.add(btnGenPairKey);

		jPanelTemp = new JPanel();
		jPanelGenAndSaveKey.add(jPanelTemp, BorderLayout.CENTER);

		JScrollPane scrollPane;

		outputGenPublicKey = new JTextArea(4, 25);
		outputGenPublicKey.setEditable(false);
		outputGenPublicKey.setLineWrap(true);
		scrollPane = new JScrollPane(outputGenPublicKey);
		jPanelTemp.add(scrollPane);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBorder(new TitledBorder("Result gen public key (Base64):"));

		outputGenPrivateKey = new JTextArea(4, 25);
		outputGenPrivateKey.setEditable(false);
		outputGenPrivateKey.setLineWrap(true);
		scrollPane = new JScrollPane(outputGenPrivateKey);
		jPanelTemp.add(scrollPane);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBorder(new TitledBorder("Result gen private key (Base64):"));

		jPanelTemp = new JPanel();
		jPanelGenAndSaveKey.add(jPanelTemp, BorderLayout.SOUTH);

		btnChooseFolderSavePublicKey = new JButton("Choose folder save public key");
		jPanelTemp.add(btnChooseFolderSavePublicKey);

		btnChooseFolderSavePrivateKey = new JButton("Choose folder save private key");
		jPanelTemp.add(btnChooseFolderSavePrivateKey);

		// phần ký text (nhập key thôi cho đơn giản)
		// phải dùng private key để ký

		JPanel jPanelSignDSA = new JPanel(new BorderLayout());
		panelContainPart.add(jPanelSignDSA);
		jPanelSignDSA.setBorder(new TitledBorder("Ký (Text)"));

		jPanelTemp = new JPanel();
		jPanelSignDSA.add(jPanelTemp, BorderLayout.NORTH);

		inputPlainText = new JTextArea(4, 20);
		inputPlainText.setLineWrap(true);
		scrollPane = new JScrollPane(inputPlainText);
		jPanelTemp.add(scrollPane);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBorder(new TitledBorder("Input plain text:"));

		inputPrivateKey = new JTextArea(4, 25);
		inputPrivateKey.setLineWrap(true);
		scrollPane = new JScrollPane(inputPrivateKey);
		jPanelTemp.add(scrollPane);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBorder(new TitledBorder("Input private key DSA (Base64):"));

		jPanelTemp = new JPanel();
		jPanelSignDSA.add(jPanelTemp, BorderLayout.SOUTH);

		btnSign = new JButton("Sign");
		jPanelTemp.add(btnSign);

		outputDigitalSignature = new JTextArea(4, 25);
		outputDigitalSignature.setEditable(false);
		outputDigitalSignature.setLineWrap(true);
		scrollPane = new JScrollPane(outputDigitalSignature);
		jPanelTemp.add(scrollPane);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBorder(new TitledBorder("Output digital signature (Base64):"));

//		this.setVisible(false);
		this.setVisible(true);
	}

	public JComboBox<Integer> getJComboKeySize() {
		return jComboKeySize;
	}

	public void setItemsjComboKeySize(Integer[] arrKeySize) {
		jComboKeySize.setModel(new DefaultComboBoxModel<>(arrKeySize));
	}

	public JButton getBtnGenPairKey() {
		return btnGenPairKey;
	}

	public JTextArea getOutputGenPublicKey() {
		return outputGenPublicKey;
	}

	public JTextArea getOutputGenPrivateKey() {
		return outputGenPrivateKey;
	}

	public JButton getBtnChooseFolderSavePublicKey() {
		return btnChooseFolderSavePublicKey;
	}

	public JButton getBtnChooseFolderSavePrivateKey() {
		return btnChooseFolderSavePrivateKey;
	}

	public JTextArea getInputPlainText() {
		return inputPlainText;
	}

	public JTextArea getInputPrivateKey() {
		return inputPrivateKey;
	}

	public JButton getBtnSign() {
		return btnSign;
	}

	public JTextArea getOutputDigitalSignature() {
		return outputDigitalSignature;
	}
}