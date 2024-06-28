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

import com.mysql.cj.util.StringUtils;

import db.BoardBean;
import db.BoardCRUD;
import db.MemberBean;

public class BoardWriteModal extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtTitle;

	private BoardCRUD bCRUD = new BoardCRUD();
	private BoardBean bBean;

	/**
	 * Create the dialog.
	 */
	public BoardWriteModal(JPanel pnlBoard, JPanel pnlPaging, MemberBean mBean, BoardMain main) {

		System.out.println("[CALL] BoardWriteModal 생성자");

		setBounds(100, 100, 600, 650);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));

		// 화면 구성
		/******************** 제목 ********************/
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

		/******************** 내용 ********************/

		JPanel pnlCenter = new JPanel();
		contentPanel.add(pnlCenter, BorderLayout.CENTER);
		pnlCenter.setLayout(new BorderLayout(0, 0));

		JLabel lblContent = new JLabel("내용 : ");
		lblContent.setPreferredSize(new Dimension(60, 0));
		lblContent.setHorizontalAlignment(JLabel.CENTER);
		pnlCenter.add(lblContent, BorderLayout.WEST);

		JTextArea txtContent = new JTextArea();
		pnlCenter.add(txtContent, BorderLayout.CENTER);

		/******************** 버튼 ********************/
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		JButton btnOk = new JButton("확인");
		buttonPane.add(btnOk);
		getRootPane().setDefaultButton(btnOk);

		JButton btnCancel = new JButton("취소");
		buttonPane.add(btnCancel);

		/******************** 기능 ********************/

		// 확인
		btnOk.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				bBean = new BoardBean();
				String title = txtTitle.getText();
				String contents = txtContent.getText();

				if (StringUtils.isNullOrEmpty(title)) {
					JOptionPane.showMessageDialog(null, "제목을 입력해주세요!");
					txtTitle.setFocusable(true);
					return;
				} else if (StringUtils.isNullOrEmpty(contents)) {
					JOptionPane.showMessageDialog(null, "내용을 입력해주세요!");
					txtContent.setFocusable(true);
					return;
				} else {
					bBean.setTitle(title);
					bBean.setContents(contents);
				}
				bBean.setMember_no(mBean.getMember_no());

				System.out.println("[DATA] bBean : " + bBean);

				int result = bCRUD.insertBoard(bBean);
				System.out.println("[RESULT] board insert 결과 : " + result);
				if (result > 0) {

					JOptionPane.showMessageDialog(null, "게시글이 작성 되었습니다.");
					BoardWriteModal.this.dispose();
					main.showTable(pnlBoard, pnlPaging, null);

				} else {
					JOptionPane.showMessageDialog(null, "게시글이 작성 실패되었습니다.");
				}
			}
		});

		// 취소
		btnCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("[CLICKED] 취소 버튼");
				BoardWriteModal.this.dispose();
			}
		});
	}
}
