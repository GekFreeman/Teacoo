package practice;


public class Questions {
	//�ײ����Ŀ�࣬���ڴ洢ÿ�������Ϣ
	public String QID;//��Ŀ���
	public String MainQuestion;//���
	public String OptionA;//ѡ��A
	public String OptionB;//ѡ��B
	public String OptionC;//ѡ��C
	public String OptionD;//ѡ��D
	public char userAnswer;//�û���ѡ��ѡ��
	public char Answer;//��
	public String Qtype;//��Ŀ����
	public String Analysis;//����
	public String level;//��Ŀ�Ѷ�
	public boolean collected;//�Ƿ��ղ�
	
	//display���������ڵ���
	public void display(){
		System.out.println("����ǣ�");
		System.out.println(MainQuestion);
		System.out.println("�����ǣ�");
		System.out.println(Qtype);
		System.out.println("ѡ��A�ǣ�");
		System.out.println(OptionA);
		System.out.println("ѡ��B�ǣ�");
		System.out.println(OptionB);
		System.out.println("ѡ��C�ǣ�");
		System.out.println(OptionC);
		System.out.println("ѡ��D�ǣ�");
		System.out.println(OptionD);
		System.out.println("�û�ѡ���ǣ�");
		System.out.println(userAnswer);
		System.out.println("��ȷ�𰸣�");
		System.out.println(Answer);
		System.out.println("������");
		System.out.println(Analysis);
		if (collected==false){
			System.out.println("��û�ղ���");
		}
		System.out.println("--------------------------------------------------------------------");	
	}

}
