package vn.edu.hcmuaf.fit.tool;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame {

    private JPanel mainPanel, inputPanel, keyPanel, resultPanel;
    private JTextArea inputKey, inputHash, result;
    private JScrollPane keyPanelScroll, InputPanelScroll;
    private JButton btnSign, btnCopy;

    public GUI() {
        Color backgroundColorHex = Color.decode("#FDF0F0");
        Color panelColor = Color.decode("#FDF0F0");
        Color buttonColor = Color.decode("#F69F98");

        ImageIcon logoIcon = new ImageIcon("src\\main\\webapp\\img\\logo.png");
        setBackground(backgroundColorHex);
        UIManager.put("Label.foreground", Color.BLACK);
        UIManager.put("Button.background", buttonColor);
        UIManager.put("Button.foreground", Color.BLACK);
        UIManager.put("TextArea.background", Color.WHITE);
        UIManager.put("TextArea.foreground", Color.BLACK);

        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(backgroundColorHex);
        add(mainPanel, BorderLayout.CENTER);

        // Input panel
        inputPanel = new JPanel();
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
        inputPanel.setBorder(new EmptyBorder(5, 10, 5, 10));
        inputPanel.setBackground(panelColor);

        inputHash = new JTextArea(3, 30);
        inputHash.setLineWrap(true);
        inputHash.setWrapStyleWord(true);
        InputPanelScroll = new JScrollPane(inputHash);
        InputPanelScroll.setBorder(new TitledBorder("Nhập dữ liệu cần ký"));
        inputPanel.add(InputPanelScroll);
        mainPanel.add(inputPanel);

        // Key panel
        keyPanel = new JPanel();
        keyPanel.setLayout(new BoxLayout(keyPanel, BoxLayout.Y_AXIS));
        keyPanel.setBorder(new EmptyBorder(5, 10, 5, 10));
        keyPanel.setBackground(panelColor);

        inputKey = new JTextArea(3, 30);
        inputKey.setLineWrap(true);
        inputKey.setWrapStyleWord(true);
        keyPanelScroll = new JScrollPane(inputKey);
        keyPanelScroll.setBorder(new TitledBorder("Nhập Private Key"));
        keyPanel.add(keyPanelScroll);
        mainPanel.add(keyPanel);

        btnSign = new JButton("Bấm vào đây để ký");
        btnSign.setForeground(Color.WHITE);
        btnSign.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    sign();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(GUI.this, "Lỗi: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        btnSign.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(btnSign);

        // Result panel
        resultPanel = new JPanel();
        resultPanel.setLayout(new BoxLayout(resultPanel, BoxLayout.Y_AXIS));
        resultPanel.setBorder(new EmptyBorder(5, 10, 5, 10));
        resultPanel.setBackground(panelColor);

        result = new JTextArea(3,10);
        result.setFont(new Font("Times New Roman", Font.BOLD, 14));
        result.setEditable(false);
        result.setLineWrap(true);
        result.setWrapStyleWord(true);
        JScrollPane jScrollPaneResult = new JScrollPane(result);
        jScrollPaneResult.setBorder(new TitledBorder("Chữ ký sẽ hiển thị ở đây:"));
        resultPanel.add(jScrollPaneResult);
        mainPanel.add(resultPanel);
        // Button Copy
        btnCopy = new JButton("Sao chép");
        btnCopy.setFont(new Font("Times New Roman", Font.BOLD, 14));
        btnCopy.setForeground(Color.WHITE);
        btnCopy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = result.getText(); // Lấy văn bản từ JTextArea
                if (text.isEmpty()) {
                    JOptionPane.showMessageDialog(GUI.this, "Không có văn bản để sao chép!", "Thông báo", JOptionPane.WARNING_MESSAGE);
                } else {
                    Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                    StringSelection selection = new StringSelection(text);
                    clipboard.setContents(selection, null);
                    JOptionPane.showMessageDialog(GUI.this, "Đã sao chép văn bản vào clipboard!", "Thành công", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        btnCopy.setAlignmentX(Component.CENTER_ALIGNMENT);
        resultPanel.add(btnCopy);


        setTitle("Chữ ký điện tử");
        setSize(500, 500);
        setIconImage(logoIcon.getImage());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

     public void sign() throws Exception {
        String input = inputHash.getText();
        String key = inputKey.getText();
        DSA dsa = new DSA();
        if (input == null || input.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Chưa nhập mã đơn hàng", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (key == null || key.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Chưa nhập khóa", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String text = dsa.sign(input, dsa.importKey(key, "DSA"));
        result.setText(text);
    }

    public static void main(String[] args) {
        new GUI();
    }
}