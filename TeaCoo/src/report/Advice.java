package report;

import javax.swing.JPanel;
import javax.swing.JTextPane;

public class Advice extends JPanel {

	//�Ѷ���ؽ���
	private boolean a;//�洢�Ƿ��������80%
	private int Diff;//�洢�轨���Ѷ�
	
	//������ؽ���
	private double[] b=new double[3];
	
	//ʱ����ؽ���
	private int numstoday;
	private int avenum;
	
	//�����ı�
	String adv;
	
	JTextPane textPane;
	/**
	 * Create the panel.
	 * @wbp.parser.constructor
	 */
	public Advice(boolean a,int Diff,double Type1,double Type2,double Type3,int numstoday,int avenum) {
		this.a=a;
		this.Diff=Diff;
		setLayout(null);
		this.b[0]=Type1;
		this.b[1]=Type2;
		this.b[2]=Type3;
		this.numstoday=numstoday;
		this.avenum=avenum;
		
		
	}

	public String advicetext() {
		
		//����ȷ�ʵ���80%���Ѷȼ���
		if (a) {
			adv="Difficulty:Your examination questions of Difficulty-"+Diff+" is still weak,you'd better pracice more.\n";
		}
		//������80%��DiffΪ����͵��Ѷ�
		else {
			adv="You are good at all Difficulties of questions,but you are weak at Difficulty-"+Diff+" by contrast,you have to practice more to make you better.\n";
		}
		int type=0;
		double temp;
		for (int i=0;i<2;i++) {
			for (int j=i;j<2;j++) {
				if (b[j]<b[j+1]) {
					temp=b[j];
					b[j]=b[j+1];
					b[j+1]=temp;
					type=j+1;
				}
			}
		}
		//���ͽ���
		adv=adv+"\n\nType:You need to practice more questions of Type"+type+".\n";
		
		//ÿ�������¼����
		if (numstoday<avenum) {
			adv=adv+"\n\nOthers:Pratice makes perfect,and you need to pratice more today.";
		}
		else adv=adv+"\n\nOthers:You are better than yours of yesterday,keep going!";
		return adv;
		
	}
}
