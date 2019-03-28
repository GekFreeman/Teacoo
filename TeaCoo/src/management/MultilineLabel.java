package management;
import javax.swing.JTextArea;
import javax.swing.LookAndFeel;

public class MultilineLabel extends JTextArea {
	
	private static final long serialVersionUID = 1L;
	public MultilineLabel() {
		 super();
		 }
	 public void updateUI() {
		 super.updateUI();  
		 setLineWrap(true); // ����Ϊ�Զ�����
		 setWrapStyleWord(true);
		 setHighlighter(null);
		 setEditable(false);
		 LookAndFeel.installBorder(this, "Label.border");// ����Ϊlabel�ı߿���ɫ������
		 LookAndFeel.installColorsAndFont(this, "Label.background", "Label.foreground", "Century Gothic");
		 
	 }
 }
