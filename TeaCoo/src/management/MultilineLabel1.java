package management;
import javax.swing.JTextArea;
import javax.swing.LookAndFeel;

public class MultilineLabel1 extends JTextArea {
	
	private static final long serialVersionUID = 1L;
	public MultilineLabel1() {
		 super();
		 }
	 public void updateUI() {
		 super.updateUI();  
		 setLineWrap(true); // ����Ϊ�Զ�����
		 setWrapStyleWord(true);
		 setHighlighter(null);
		 setEditable(true);
		 LookAndFeel.installBorder(this, "Label.border");// ����Ϊlabel�ı߿���ɫ������
		 LookAndFeel.installColorsAndFont(this, "Label.background", "Label.foreground", "Century Gothic");
		 
	 }
 }
