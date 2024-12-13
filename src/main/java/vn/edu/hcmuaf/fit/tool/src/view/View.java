package vn.edu.hcmuaf.fit.tool.src.view;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

public class View extends JFrame {
	private static final long serialVersionUID = 1L;

	private JFileChooser jFileChooser;
	private JDialog waitingDialog;

	private ViewChuKy viewChuKy;

	public View(String s) {
		super(s);

		jFileChooser = new JFileChooser();

		waitingDialog = new JDialog();

		JTabbedPane tabPanel = new JTabbedPane();
		this.add(tabPanel);

		JScrollPane scrollPane;

		viewChuKy = new ViewChuKy();
		scrollPane = new JScrollPane(viewChuKy);
		tabPanel.addTab("Chữ ký điện tử", scrollPane);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setPreferredSize(new Dimension(700, 350));

		this.setSize(900, 550);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int screenWidth = screenSize.width;
//		int screenHeight = screenSize.height;
		this.setLocation(screenWidth / 2 - getWidth() / 2, 0);
//		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	public JFileChooser getJFileChooser() {
		return jFileChooser;
	}

	public JDialog getWaitingDialog() {
		return waitingDialog;
	}

	public ViewChuKy getViewChuKy() {
		return viewChuKy;
	}
}