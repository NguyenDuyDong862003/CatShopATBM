package vn.edu.hcmuaf.fit.tool.src.controller;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import vn.edu.hcmuaf.fit.tool.src.model.Model;
import vn.edu.hcmuaf.fit.tool.src.model.DS;

import vn.edu.hcmuaf.fit.tool.src.view.View;
import vn.edu.hcmuaf.fit.tool.src.view.ViewChuKy;

public class Controller {
	private Model model;
	private View view;

	public Controller(Model model, View view) {
		super();
		this.model = model;
		this.view = view;

		this.initItemsForjComboBoxs();
		this.registerEvent();

		JFileChooser jFileChooser = view.getJFileChooser();
		// Lấy thư mục gốc của project
		String projectDirectory = System.getProperty("user.dir");
		// Set thư mục mặc định là thư mục project
		jFileChooser.setCurrentDirectory(new File(projectDirectory));
	}

	private void initItemsForjComboBoxs() {
		ViewChuKy viewChuKy = view.getViewChuKy();
		viewChuKy.setItemsjComboKeySize(Model.DSA_KEY_SIZES);
	}

	private void registerEvent() {
		registerEventViewDSA();
	}

	private void registerEventViewDSA() {
		DS dsa = model.getDsa();

		ViewChuKy viewChuKy = view.getViewChuKy();

		JComboBox<Integer> jComboKeySize = viewChuKy.getJComboKeySize();
		JButton btnGenPairKey = viewChuKy.getBtnGenPairKey();
		JTextArea outputGenPublicKey = viewChuKy.getOutputGenPublicKey();
		JTextArea outputGenPrivateKey = viewChuKy.getOutputGenPrivateKey();
		JButton btnChooseFolderSavePublicKey = viewChuKy.getBtnChooseFolderSavePublicKey();
		JButton btnChooseFolderSavePrivateKey = viewChuKy.getBtnChooseFolderSavePrivateKey();

		jComboKeySize.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				outputGenPublicKey.setText("");
				outputGenPrivateKey.setText("");
			}
		});

		btnGenPairKey.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int keySize = (int) jComboKeySize.getSelectedItem();
				dsa.genKey(keySize);
				outputGenPublicKey.setText(Base64.getEncoder().encodeToString(dsa.getPublicKey().getEncoded()));
				outputGenPrivateKey.setText(Base64.getEncoder().encodeToString(dsa.getPrivateKey().getEncoded()));
			}
		});

		btnChooseFolderSavePublicKey.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String base64PublicKey = outputGenPublicKey.getText();
				if (base64PublicKey.equals("")) {
					JOptionPane.showMessageDialog(null, "Bạn chưa tạo khóa nên không thể lưu", "Thông báo",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				int keySize = (int) jComboKeySize.getSelectedItem();
				showDialogSaveFileFormatBase64("Chọn nơi lưu public key",
						String.format(Model.nameFileSavePublicKeyDSA, keySize),
						Base64.getDecoder().decode(base64PublicKey));
			}
		});

		btnChooseFolderSavePrivateKey.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String base64PrivateKey = outputGenPrivateKey.getText();
				if (base64PrivateKey.equals("")) {
					JOptionPane.showMessageDialog(null, "Bạn chưa tạo khóa nên không thể lưu", "Thông báo",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				int keySize = (int) jComboKeySize.getSelectedItem();
				showDialogSaveFileFormatBase64("Chọn nơi lưu private key",
						String.format(Model.nameFileSavePrivateKeyDSA, keySize),
						Base64.getDecoder().decode(base64PrivateKey));
			}
		});

		JTextArea inputPlainText = viewChuKy.getInputPlainText();
		JTextArea inputPrivateKey = viewChuKy.getInputPrivateKey();
		JButton btnSign = viewChuKy.getBtnSign();
		JTextArea outputDigitalSignature = viewChuKy.getOutputDigitalSignature();

		btnSign.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					dsa.setPrivateKey(inputPrivateKey.getText());
					String resultSign = dsa.sign(inputPlainText.getText());
					outputDigitalSignature.setText(resultSign);
				} catch (NoSuchAlgorithmException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
				} catch (InvalidKeySpecException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
				} catch (InvalidKeyException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
				} catch (SignatureException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}

	public String showDialogChooseFile(String title) {
		JFileChooser jFileChooser = this.view.getJFileChooser();

		jFileChooser.setDialogTitle(title);

		int result = jFileChooser.showOpenDialog(null);
		if (result == JFileChooser.APPROVE_OPTION) { // nhấn OK
			File selectedFolder = jFileChooser.getSelectedFile();
			return selectedFolder.getAbsolutePath();
		} else {
			return "Không có file nào được chọn.";
		}
	}

	public void showDialogSaveFileFormatBase64(String title, String nameSaveFile, byte[] byteContent) {
//		System.out.println(nameSaveFile);

		JFileChooser jFileChooser = this.view.getJFileChooser();

		jFileChooser.setDialogTitle(title);
		// Đặt tên file mặc định theo loại khóa
		String pathDefault = jFileChooser.getCurrentDirectory().getAbsolutePath();
//		System.out.println(pathDefault);
//		folderChooser.setSelectedFile(new File(nameSaveFile));
		jFileChooser.setSelectedFile(new File(pathDefault + File.separator + nameSaveFile));
		// nếu nhập đường dẫn có tên file tuyệt đối vô field name thì vẫn hoạt động
		// nếu chỉ nhập đường dẫn mà ko có tên file thì nút save ko hoạt động
		// nếu chỉ nhập tên file vẫn hoạt động (sẽ lưu vào thư mục hiện tại VD document)

		int userSelection = jFileChooser.showSaveDialog(null);

		if (userSelection == JFileChooser.APPROVE_OPTION) {
			File fileToSave = jFileChooser.getSelectedFile();
			try (PrintWriter pw = new PrintWriter(new FileOutputStream(fileToSave))) {
				pw.write(Base64.getEncoder().encodeToString(byteContent));
//				System.out.println("Lưu thành công tại: " + fileToSave.getAbsolutePath());
				JOptionPane.showMessageDialog(null, "Lưu thành công tại: " + fileToSave.getAbsolutePath(), "Thông báo",
						JOptionPane.INFORMATION_MESSAGE);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
	}

	public void showDialogSaveFileFormatText(String title, String nameSaveFile, String textContent) {
//		System.out.println(nameSaveFile);

		JFileChooser jFileChooser = this.view.getJFileChooser();

		jFileChooser.setDialogTitle(title);
		// Đặt tên file mặc định theo loại khóa
		String pathDefault = jFileChooser.getCurrentDirectory().getAbsolutePath();
//		System.out.println(pathDefault);
//		folderChooser.setSelectedFile(new File(nameSaveFile));
		jFileChooser.setSelectedFile(new File(pathDefault + File.separator + nameSaveFile));
		// nếu nhập đường dẫn có tên file tuyệt đối vô field name thì vẫn hoạt động
		// nếu chỉ nhập đường dẫn mà ko có tên file thì nút save ko hoạt động
		// nếu chỉ nhập tên file vẫn hoạt động (sẽ lưu vào thư mục hiện tại VD document)

		int userSelection = jFileChooser.showSaveDialog(null);

		if (userSelection == JFileChooser.APPROVE_OPTION) {
			File fileToSave = jFileChooser.getSelectedFile();
			try (PrintWriter pw = new PrintWriter(new FileOutputStream(fileToSave))) {
				pw.write(textContent);
//				System.out.println("Lưu thành công tại: " + fileToSave.getAbsolutePath());
				JOptionPane.showMessageDialog(null, "Lưu thành công tại: " + fileToSave.getAbsolutePath(), "Thông báo",
						JOptionPane.INFORMATION_MESSAGE);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
	}

	public void showWaiting(String title, String content) {
		int widthWaiting = 300;
		int heightWaiting = 100;

		JDialog waitingDialog = view.getWaitingDialog();
		waitingDialog.setTitle(title);
		waitingDialog.setSize(widthWaiting, heightWaiting);
//		waitingDialog.setLocationRelativeTo(null);
//		waitingDialog.setModal(true); // ko cho tương tác với cửa sổ khác
		waitingDialog.setAlwaysOnTop(true); // nếu bật lên thì khi hiện thông báo lỗi bị che luôn

		Point locationFrame = view.getLocation();
		Dimension dimensionFrame = view.getSize();
		int widthFrame = (int) dimensionFrame.getWidth();
		int heightFrame = (int) dimensionFrame.getHeight();
		waitingDialog.setLocation((int) (locationFrame.getX() + widthFrame / 2 - widthWaiting / 2),
				(int) (locationFrame.getY() + heightFrame / 2 - heightWaiting / 2 - 200));

		JLabel label = new JLabel(content, JLabel.CENTER);
		waitingDialog.add(label);
		waitingDialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE); // ko cho đóng
		waitingDialog.setVisible(true);
	}

	public void closeWaiting() {
		JDialog waitingDialog = view.getWaitingDialog();
		waitingDialog.setVisible(false);
	}
}