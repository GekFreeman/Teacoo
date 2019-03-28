package practice;


public class Questions {
	//底层的题目类，用于存储每道题的信息
	public String QID;//题目编号
	public String MainQuestion;//题干
	public String OptionA;//选项A
	public String OptionB;//选项B
	public String OptionC;//选项C
	public String OptionD;//选项D
	public char userAnswer;//用户所选的选项
	public char Answer;//答案
	public String Qtype;//题目类型
	public String Analysis;//解析
	public String level;//题目难度
	public boolean collected;//是否被收藏
	
	//display方法，用于调试
	public void display(){
		System.out.println("题干是：");
		System.out.println(MainQuestion);
		System.out.println("题型是：");
		System.out.println(Qtype);
		System.out.println("选项A是：");
		System.out.println(OptionA);
		System.out.println("选项B是：");
		System.out.println(OptionB);
		System.out.println("选项C是：");
		System.out.println(OptionC);
		System.out.println("选项D是：");
		System.out.println(OptionD);
		System.out.println("用户选的是：");
		System.out.println(userAnswer);
		System.out.println("正确答案：");
		System.out.println(Answer);
		System.out.println("解析：");
		System.out.println(Analysis);
		if (collected==false){
			System.out.println("还没收藏呢");
		}
		System.out.println("--------------------------------------------------------------------");	
	}

}
