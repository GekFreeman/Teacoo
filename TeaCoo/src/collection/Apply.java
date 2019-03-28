package collection;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class Apply extends JPanel {// �ڸ�����ʵ�ֶ������������йص���ĵ��úͼ���

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

		/* ���Ͻǵķ��ذ�ť�����Ʒ��ص�star��wrong��applicationѡ����� */
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
		 * ʵ����DetailInfo��ͬʱ����Ӧ�ı���ͼƬ�Ž�ȥ��������������panel��С��λ�ã���Ϊwrong��star��
		 * application������һ��DetailInfo�࣬�����Ա���ͼ��ͬ
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
	 * ���ڸý����ǵײ�Ĵ��panel���䣬�����Сpanel���л���Ϊ��ʵ��ÿ������󷵻أ�table�е������ܼ�ʱ���£�
	 * ���Խ��������б��ʵ�����������йصļ��������������Ϊһ�����������غ��ٴε��ã�table�е����ݱ������µ���
	 */
	public void initApplyList() {

		applyList = new ApplyList(userID);
		/* ��table���һ�еİ�ť���м������ɵ�ǰ�еĵ�һ�У������ţ���Ϊ�����ݿ�Apply���в�ѯ������ */
		applyList.btndetail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					int r = MYTable.table.getSelectedRow();// ��������ǰѡ�����һ��
					/* ����MYTable��getText������ͨ�����������ID�� �����ݿ��в�ѯ�����������ϸ�� */
					MYTable.getText(MYTable.table.getValueAt(r, 0).toString());
					/* ����ѯ������Ϣ�ֱ����DetailInfo�ж�Ӧ������ */
					applyDetail.ALabel.setText(MYTable.oA);
					applyDetail.BLabel.setText(MYTable.oB);
					applyDetail.CLabel.setText(MYTable.oC);
					applyDetail.DLabel.setText(MYTable.oD);
					applyDetail.stemArea.setText(MYTable.main);
					applyDetail.hnawerLabel.setText(MYTable.answer);
					applyDetail.anlysArea.setText(MYTable.analysis);
					/* ʵ���б������panel���л� */
					container.remove(applyList);
					container.add(applyDetail);
					container.revalidate();
					container.repaint();

				} catch (Exception ex) {
					System.out.println("Exception: " + ex.getMessage());
				}
			}
		});

		initAddNew();// ÿ��list���º�ҲҪ����ʵ���������������࣬�������ٴ����
		applyDetail.btnReturn.addActionListener(lis);
		applyList.btnAdd.addActionListener(lis);

	}

	/*
	 * ͬ��Ϊ�����ڲ�������һ��������£����ܼ�����AddNew��ť������µ����룬��ʵ�����ͼ���AddNewApp��Ĳ���
	 * Ҳ��Ϊһ������������,������ʾ����Ҳ������������õ���ͬ���ڴ�ʵ����
	 */
	public void initAddNew() {

		addNew = new AddNewApp(userID);
		dia = new DialogPop();
		tip = new TipsPop();

		/* ��AddNewApp���е����ע����� */
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

		/* �ֱ�ԶԻ���������ʾ�����İ�ť���ע����� */
		dia.btnY.addActionListener(lis);
		tip.btnOK.addActionListener(lis);

	}

	/* ������panel�ı���ͼ */
	public void paintComponent(Graphics g) {
		ImageIcon BG = new ImageIcon(Toolkit.getDefaultToolkit().getImage(
				"image/collection/applicationBG.png"));
		Image img = BG.getImage();
		g.drawImage(img, 0, 0, BG.getIconWidth(), BG.getIconHeight(),
				BG.getImageObserver());
	}

	/* �����м����ܶ࣬��һ���ڲ����װ */
	public class MyListener implements ActionListener, DocumentListener {

		/* ����JTextArea��JTextField�����¼����м�������DocumentListener */
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

		/* ����û���õ��ķ��� */
		public void removeUpdate(DocumentEvent e) {

		}

		public void changedUpdate(DocumentEvent e) {

		}

		/* �����Ǽ�������ť */
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
				/* �����жϣ������ɡ�ѡ��𰸣���һΪ���ߣ�����ͬ���ύ�����룬������ʾҪ���������� */
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

						/* �����������Ϊ�գ������ύ���룬����Щ���ݴ���Dialog��ʵ�����Ķ����У�������������ݽ����ݿ� */

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
				/* �����ð�ť�ǽ�������ղ�����ж����ύ������빫����⣬��������ʾ����û��ѡ�񵯴���ͬ�����ж���Ŀ��Ϣ�������� */
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

			} else if (obj == addNew.comBoxType) {// ��������������ѡ�����Ŀ���ͣ�ת�������ݿ��ж�Ӧ�洢������

				if (addNew.comBoxType.getModel().getSelectedItem().toString()
						.equals("principle of management"))
					addNew.type = "type1";
				else if (addNew.comBoxType.getModel().getSelectedItem()
						.toString().equals("financial management"))
					addNew.type = "type2";
				else
					addNew.type = "type3";

			} else if (obj == applyList.btnAdd) {// ���������

				initAddNew();// ����ʵ�����������
				container.remove(applyList);
				container.add(addNew);
				container.revalidate();
				container.repaint();

			} else if (obj == dia.btnY) {// ����ڵ����е����yes��ť��֮���ύ��ť�������ٵ���������ظ��ύͬһ������

				addNew.btnSubmit.setEnabled(false);

			} else if (obj == tip.btnOK) {// ͬ���������ղؿ����ظ�����ͬһ���⡣��ʾ����������������³��֣�
											// ������ʾ�����жϣ�ֻ������ʾ�ɹ���������ղؿ�ʱ�Ų����ٴε����
				if (tip.showLabel
						.getText()
						.equals("Your question has been inserted into your star collection !"))
					addNew.btnSave.setEnabled(false);

			}
		}
	}
}
