package swingui;

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
import javax.swing.border.EmptyBorder;

import db.BoardBean;
import db.BoardCRUD;

public class BoardWriteDetail extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtTitle;

	private BoardCRUD bCRUD = new BoardCRUD();

	/**
	 * Create the dialog.
	 */
	public BoardWriteDetail(BoardBean bean, BoardMain main, JPanel pnlBoard, JPanel pnlPaging) {

		System.out.println("[CALL] BoardWriteDetail 생성자");

		setBounds(100, 100, 600, 650);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));

		JPanel pnlTop = new JPanel();
		contentPanel.add(pnlTop, BorderLayout.NORTH);
		pnlTop.setLayout(new BorderLayout(0, 0));

		JLabel lblTitle = new JLabel("제목 : ");
		lblTitle.setPreferredSize(new Dimension(60, 30));
		lblTitle.setHorizontalAlignment(JLabel.CENTER);
		pnlTop.add(lblTitle, BorderLayout.WEST);

		txtTitle = new JTextField();
		pnlTop.add(txtTitle, BorderLayout.CENTER);
		txtTitle.setColumns(10);

		JPanel pnlCenter = new JPanel();
		contentPanel.add(pnlCenter, BorderLayout.CENTER);
		pnlCenter.setLayout(new BorderLayout(0, 0));

		JLabel lblContent = new JLabel("내용 : ");
		lblContent.setPreferredSize(new Dimension(60, 0));
		lblContent.setHorizontalAlignment(JLabel.CENTER);
		pnlCenter.add(lblContent, BorderLayout.WEST);

		JTextArea txtContent = new JTextArea();
		txtContent.setTabSize(0);
		pnlCenter.add(txtContent, BorderLayout.CENTER);

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		JButton btnUpdate = new JButton("수정");
		btnUpdate.setActionCommand("OK");
		buttonPane.add(btnUpdate);
		getRootPane().setDefaultButton(btnUpdate);

		JButton btnDelete = new JButton("삭제");
		btnDelete.setActionCommand("OK");
		buttonPane.add(btnDelete);
		getRootPane().setDefaultButton(btnDelete);

		JButton btnCancel = new JButton("취소");
		btnCancel.setActionCommand("Cancel");
		buttonPane.add(btnCancel);

		txtTitle.setText(bean.getTitle());
		txtContent.setText(bean.getContents());

		// 수정 버튼
		btnUpdate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int confirm = JOptionPane.showConfirmDialog(null, "수정하시겠습니까?", "수정 확인 알림", JOptionPane.YES_NO_OPTION);

				if (confirm == JOptionPane.YES_OPTION) {
					
					bean.setTitle(txtTitle.getText());
					bean.setContents(txtContent.getText());
					int result = bCRUD.updateBoard(bean);

					if (result > 0) {
						JOptionPane.showMessageDialog(null, "수정되었습니다!");
						main.showTable(pnlBoard, pnlPaging, null);
						BoardWriteDetail.this.dispose();
						
					} else {
						JOptionPane.showMessageDialog(null, "수정이 실패되었습니다!");
						BoardWriteDetail.this.dispose();
					}

				} else {
					return;
				}
			}
		});

		// 삭제 버튼
		btnDelete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int confirm = JOptionPane.showConfirmDialog(null, "삭제하시겠습니까?", "삭제 확인 알림", JOptionPane.YES_NO_OPTION);

				if (confirm == JOptionPane.YES_OPTION) {
					int result = bCRUD.deleteBoard(bean.getBoard_no());

					if (result > 0) {
						JOptionPane.showMessageDialog(null, "삭제되었습니다!");
						main.showTable(pnlBoard, pnlPaging, null);
						BoardWriteDetail.this.dispose();
					} else {
						JOptionPane.showMessageDialog(null, "삭제가 실패되었습니다!");
						BoardWriteDetail.this.dispose();
					}

				} else {
					return;
				}
			}
		});

		// 취소 버튼
		btnCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				BoardWriteDetail.this.dispose();
			}
		});

	}

}
