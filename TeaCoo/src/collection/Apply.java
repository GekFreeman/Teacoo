package collection;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class Apply extends JPanel {// 在该类中实现对其它与申请有关的类的调用和监听

	private static final long serialVersionUID = 1L;
	String userID;
	public JFrame presentframe;
	public JPanel presentpanel;

	private ApplyList applyList;
	private DetailInfo applyDetail;
	private AddNewApp addNew;

	private JPanel container;
	private JButton btnBack;

	private DialogPop dia;
	private TipsPop tip;
	private MyListener lis = new MyListener();

	public Apply(String userID) {
		this.userID = userID;
		setVisible(true);
		setBounds(0, 0, 1000, 700);
		setLayout(null);

		container = new JPanel();
		container.setBounds(0, 0, 1000, 700);
		container.setOpaque(false);
		container.setLayout(null);
		add(container);

		/* 左上角的返回按钮，控制返回到star、wrong、application选择界面 */
		btnBack = new JButton();
		btnBack.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(
				"image/back.png")));
		btnBack.setRolloverIcon(new ImageIcon(Toolkit.getDefaultToolkit()
				.getImage("image/backO.png")));
		btnBack.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {
				CollectionMenu cMenu = new CollectionMenu(userID);
				presentpanel.setVisible(false);
				presentframe.getContentPane().add(cMenu);
				cMenu.presentframe = presentframe;
				cMenu.presentpanel = cMenu;
			}
		});
		btnBack.setBounds(0, 0, 121, 42);
		btnBack.setContentAreaFilled(false);
		btnBack.setBorderPainted(false);
		container.add(btnBack);

		/*
		 * 实例化DetailInfo的同时将对应的背景图片放进去，并在这里设置panel大小和位置，因为wrong、star、
		 * application都用这一个DetailInfo类，而各自背景图不同
		 */
		applyDetail = new DetailInfo(userID) {

			private static final long serialVersionUID = 1L;

			public void paintComponent(Graphics g) {
				ImageIcon BG = new ImageIcon(Toolkit.getDefaultToolkit()
						.getImage("image/collection/applicationdetail.png"));
				Image img = BG.getImage();
				g.drawImage(img, 0, 0, BG.getIconWidth(), BG.getIconHeight(),
						BG.getImageObserver());
			}
		};
		applyDetail.setBounds(318, 92, 600, 550);

		initApplyList();
		container.add(applyList);

	}

	/*
	 * 由于该界面是底层的大的panel不变，上面的小panel在切换，为了实现每次申请后返回，table中的数据能及时更新，
	 * 所以将对申请列表的实例化和与它有关的监听单独提出来作为一个方法，返回后再次调用，table中的数据便是最新的了
	 */
	public void initApplyList() {

		applyList = new ApplyList(userID);
		/* 对table最后一列的按钮进行监听，由当前行的第一列（申请编号）作为在数据库Apply表中查询的条件 */
		applyList.btndetail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					int r = MYTable.table.getSelectedRow();// 监听到当前选择的那一行
					/* 调用MYTable中getText方法，通过传入申请的ID， 从数据库中查询到该条申请的细节 */
					MYTable.getText(MYTable.table.getValueAt(r, 0).toString());
					/* 将查询到的信息分别插入DetailInfo中对应的区域 */
					applyDetail.ALabel.setText(MYTable.oA);
					applyDetail.BLabel.setText(MYTable.oB);
					applyDetail.CLabel.setText(MYTable.oC);
					applyDetail.DLabel.setText(MYTable.oD);
					applyDetail.stemArea.setText(MYTable.main);
					applyDetail.hnawerLabel.setText(MYTable.answer);
					applyDetail.anlysArea.setText(MYTable.analysis);
					/* 实现列表和详情panel的切换 */
					container.remove(applyList);
					container.add(applyDetail);
					container.revalidate();
					container.repaint();

				} catch (Exception ex) {
					System.out.println("Exception: " + ex.getMessage());
				}
			}
		});

		initAddNew();// 每次list更新后，也要重新实例化添加新申请的类，否则不能再次添加
		applyDetail.btnReturn.addActionListener(lis);
		applyList.btnAdd.addActionListener(lis);

	}

	/*
	 * 同理，为了能在不返回上一级的情况下，还能继续用AddNew按钮来添加新的申请，将实例化和监听AddNewApp类的部分
	 * 也作为一个方法。另外,两个提示弹窗也在这个界面中用到，同样在此实例化
	 */
	public void initAddNew() {

		addNew = new AddNewApp(userID);
		dia = new DialogPop();
		tip = new TipsPop();

		/* 对AddNewApp类中的组件注册监听 */
		addNew.stemTextA.getDocument().addDocumentListener(lis);
		addNew.aTextA.getDocument().addDocumentListener(lis);
		addNew.bTextA.getDocument().addDocumentListener(lis);
		addNew.cTextA.getDocument().addDocumentListener(lis);
		addNew.dTextA.getDocument().addDocumentListener(lis);
		addNew.anlysTextA.getDocument().addDocumentListener(lis);
		addNew.anwrTextF.getDocument().addDocumentListener(lis);
		addNew.btnSubmit.addActionListener(lis);
		addNew.btnSave.addActionListener(lis);
		addNew.btnReturn.addActionListener(lis);
		addNew.comBoxType.addActionListener(lis);

		/* 分别对对话弹窗和提示弹窗的按钮组件注册监听 */
		dia.btnY.addActionListener(lis);
		tip.btnOK.addActionListener(lis);

	}

	/* 下面大的panel的背景图 */
	public void paintComponent(Graphics g) {
		ImageIcon BG = new ImageIcon(Toolkit.getDefaultToolkit().getImage(
				"image/collection/applicationBG.png"));
		Image img = BG.getImage();
		g.drawImage(img, 0, 0, BG.getIconWidth(), BG.getIconHeight(),
				BG.getImageObserver());
	}

	/* 该类中监听很多，用一个内部类封装 */
	public class MyListener implements ActionListener, DocumentListener {

		/* 对向JTextArea和JTextField插入事件进行监听，用DocumentListener */
		public void insertUpdate(DocumentEvent e) {

			Object obj = e.getDocument();

			if (obj == addNew.anwrTextF.getDocument()) {
				addNew.anwr = addNew.anwrTextF.getText();
			} else if (obj == addNew.aTextA.getDocument()) {
				addNew.a = addNew.aTextA.getText();
			} else if (obj == addNew.bTextA.getDocument()) {
				addNew.b = addNew.bTextA.getText();
			} else if (obj == addNew.cTextA.getDocument()) {
				addNew.c = addNew.cTextA.getText();
			} else if (obj == addNew.dTextA.getDocument()) {
				addNew.d = addNew.dTextA.getText();
			} else if (obj == addNew.stemTextA.getDocument()) {
				addNew.stem = addNew.stemTextA.getText();
			} else if (obj == addNew.anlysTextA.getDocument()) {
				addNew.anlys = addNew.anlysTextA.getText();
			}
		}

		/* 两个没有用到的方法 */
		public void removeUpdate(DocumentEvent e) {

		}

		public void changedUpdate(DocumentEvent e) {

		}

		/* 以下是监听各按钮 */
		public void actionPerformed(ActionEvent e) {

			Object obj = e.getSource();

			if (obj == applyDetail.btnReturn) {

				container.remove(applyDetail);
				container.add(applyList);
				container.revalidate();
				container.repaint();

			} else if (obj == addNew.btnReturn) {

				container.remove(addNew);
				initApplyList();
				container.add(applyList);
				container.revalidate();
				container.repaint();

			} else if (obj == addNew.btnSubmit) {
				new InsertToDB();
				/* 进行判断，如果题干、选项、答案，有一为空者，则不能同意提交该申请，会有提示要求输入完整 */
				if (InsertToDB.findUserLevel(userID) ==0) {
					tip.showLabel
							.setText("You have NO AUTHORITY to change the TeaCoo !");
					tip.setVisible(true);
					addNew.btnSubmit.setEnabled(false);

				} else {
					if (addNew.stem == null || addNew.a == null
							|| addNew.b == null || addNew.c == null
							|| addNew.d == null || addNew.anwr == null) {

						tip.showLabel
								.setText("You haven't completed this application !");
						tip.setVisible(true);

					} else {

						/* 如果上述都不为空，可以提交申请，将这些数据传到Dialog类实例化的对象中，从那里插入数据进数据库 */

						dia.showLabel
								.setText("Sure you want to submit your application ?");

						dia.userID = userID;
						dia.stem = addNew.stem;
						dia.a = addNew.a;
						dia.b = addNew.b;
						dia.c = addNew.c;
						dia.d = addNew.d;
						dia.anwr = addNew.anwr;
						dia.type = addNew.type;
						dia.anlys = addNew.anlys;

						dia.setVisible(true);

					}
				}

			} else if (obj == addNew.btnSave) {
				/* 单击该按钮是将题存在收藏题库中而不提交申请进入公共题库，最后出现提示，但没有选择弹窗。同样会判断题目信息的完整性 */
				if (addNew.stem == null || addNew.a == null || addNew.b == null
						|| addNew.c == null || addNew.d == null) {

					tip.showLabel
							.setText("You haven't completed this application !");
					tip.setVisible(true);

				} else {
					new InsertToDB();
					InsertToDB.insert(userID, addNew.stem, addNew.a, addNew.b,
							addNew.c, addNew.d, addNew.anwr, addNew.type,
							addNew.anlys);

					tip.showLabel
							.setText("Your question has been inserted into your star collection !");
					tip.setVisible(true);
				}

			} else if (obj == addNew.comBoxType) {// 监听在下拉框中选择的题目类型，转换成数据库中对应存储的类型

				if (addNew.comBoxType.getModel().getSelectedItem().toString()
						.equals("principle of management"))
					addNew.type = "type1";
				else if (addNew.comBoxType.getModel().getSelectedItem()
						.toString().equals("financial management"))
					addNew.type = "type2";
				else
					addNew.type = "type3";

			} else if (obj == applyList.btnAdd) {// 添加新申请

				initAddNew();// 重新实例化申请界面
				container.remove(applyList);
				container.add(addNew);
				container.revalidate();
				container.repaint();

			} else if (obj == dia.btnY) {// 如果在弹窗中点击了yes按钮，之后提交按钮将不能再点击，避免重复提交同一个申请

				addNew.btnSubmit.setEnabled(false);

			} else if (obj == tip.btnOK) {// 同理，避免向收藏库中重复插入同一道题。提示弹窗会在两种情况下出现，
											// 进行提示内容判断，只有在提示成功插题进入收藏库时才不能再次点击。
				if (tip.showLabel
						.getText()
						.equals("Your question has been inserted into your star collection !"))
					addNew.btnSave.setEnabled(false);

			}
		}
	}
}
