package report;

import javax.swing.JPanel;
import javax.swing.JTextPane;

public class Advice extends JPanel {

	//难度相关建议
	private boolean a;//存储是否均超过了80%
	private int Diff;//存储需建议难度
	
	//题型相关建议
	private double[] b=new double[3];
	
	//时间相关建议
	private int numstoday;
	private int avenum;
	
	//建议文本
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
		
		//有正确率低于80%的难度级别
		if (a) {
			adv="Difficulty:Your examination questions of Difficulty-"+Diff+" is still weak,you'd better pracice more.\n";
		}
		//均高于80%，Diff为率最低的难度
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
		//题型建议
		adv=adv+"\n\nType:You need to practice more questions of Type"+type+".\n";
		
		//每日练题记录建议
		if (numstoday<avenum) {
			adv=adv+"\n\nOthers:Pratice makes perfect,and you need to pratice more today.";
		}
		else adv=adv+"\n\nOthers:You are better than yours of yesterday,keep going!";
		return adv;
		
	}
}
