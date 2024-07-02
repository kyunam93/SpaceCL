package BoardTest.UI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Dimension;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;

public class BoardDetail extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textField;

	/**
	 * Create the dialog.
	 */
	public BoardDetail() {
		System.out.println("\n[CALL] BoardDetail");

		init();
	}// constructor
	
	public void init() {
		System.out.println("[CALL] " + new Throwable().getStackTrace()[0].getMethodName());

		setBounds(100, 100, 600, 700);
		setLocationRelativeTo(contentPanel);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel pnlTitle = new JPanel();
		contentPanel.add(pnlTitle, BorderLayout.NORTH);
		pnlTitle.setLayout(new BorderLayout(0, 0));
		
		JLabel lblTitle = new JLabel("제목 : ");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setPreferredSize(new Dimension(60, 30));
		pnlTitle.add(lblTitle, BorderLayout.WEST);
		
		textField = new JTextField();
		pnlTitle.add(textField, BorderLayout.CENTER);
		textField.setColumns(10);
		
		JPanel pnlContent = new JPanel();
		contentPanel.add(pnlContent, BorderLayout.CENTER);
		pnlContent.setLayout(new BorderLayout(0, 0));
		
		JLabel lblContents = new JLabel("제목 : ");
		lblContents.setPreferredSize(new Dimension(60, 30));
		lblContents.setHorizontalAlignment(SwingConstants.CENTER);
		pnlContent.add(lblContents, BorderLayout.WEST);
		
		JTextArea txtContents = new JTextArea();
		pnlContent.add(txtContents, BorderLayout.CENTER);

		JPanel pnlButton = new JPanel();
		pnlButton.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(pnlButton, BorderLayout.SOUTH);

		JButton btnUpdate = new JButton("수정");
		btnUpdate.setActionCommand("OK");
		pnlButton.add(btnUpdate);
		getRootPane().setDefaultButton(btnUpdate);

		JButton btnDelete = new JButton("삭제");
		btnDelete.setActionCommand("Cancel");
		pnlButton.add(btnDelete);
		
		JButton btnCancel = new JButton("취소");
		btnCancel.setActionCommand("OK");
		pnlButton.add(btnCancel);
	}// method init

}// class
