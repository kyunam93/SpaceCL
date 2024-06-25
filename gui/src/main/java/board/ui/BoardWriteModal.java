package board.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import board.db.BoardBean;
import board.db.BoardCRUD;
import board.db.MemberBean;

public class BoardWriteModal extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtTitle;
	private JTextArea txtContent;
	private MemberBean mBean;
	BoardCRUD bCRUD = new BoardCRUD();
	MainBoard mainBoard;

	/**
	 * Create the dialog.
	 */
	public BoardWriteModal(MemberBean mBean, MainBoard mainBoard) {
		this.mBean = mBean;
		this.mainBoard = mainBoard;

		setBounds(100, 100, 700, 600);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));

		JPanel writeNorth = new JPanel();
		contentPanel.add(writeNorth, BorderLayout.NORTH);
		writeNorth.setLayout(new BorderLayout(0, 0));

		JLabel lblTitle = new JLabel("제목: ");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setPreferredSize(new Dimension(50, 30));
		writeNorth.add(lblTitle, BorderLayout.WEST);

		txtTitle = new JTextField();
		txtTitle.setColumns(50);
		writeNorth.add(txtTitle, BorderLayout.CENTER);

		JPanel writeCenter = new JPanel();
		contentPanel.add(writeCenter, BorderLayout.CENTER);
		writeCenter.setLayout(new BorderLayout(0, 0));

		JLabel lblContent = new JLabel("내용: ");
		lblContent.setPreferredSize(new Dimension(50, 0));
		lblContent.setHorizontalAlignment(SwingConstants.CENTER);
		writeCenter.add(lblContent, BorderLayout.WEST);

		txtContent = new JTextArea();
		writeCenter.add(txtContent, BorderLayout.CENTER);

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		JButton okButton = new JButton("OK");
		okButton.setActionCommand("OK");
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);
		okButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				BoardBean bBean = new BoardBean();
				bBean.setMemberNo(mBean.getMemberNo());
				bBean.setTitle(txtTitle.getText());
				bBean.setContents(txtContent.getText());

				int result = bCRUD.insertBoard(bBean);

				if (result > 0) {
					JOptionPane.showMessageDialog(null, "게시글이 작성되었습니다.");
					BoardWriteModal.this.dispose();

					mainBoard.showTable(0);
				} else {
					JOptionPane.showMessageDialog(null, "게시글 작성 실패!");
				}

			}

		});

		JButton cancelButton = new JButton("Cancel");
		cancelButton.setActionCommand("Cancel");
		buttonPane.add(cancelButton);

	}// method

	public void clearInputs() {
		txtTitle.setText("");
		txtContent.setText("");
	}
}// class
