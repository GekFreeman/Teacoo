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
		 setLineWrap(true); // 设置为自动换行
		 setWrapStyleWord(true);
		 setHighlighter(null);
		 setEditable(false);
		 LookAndFeel.installBorder(this, "Label.border");// 设置为label的边框，颜色和字体
		 LookAndFeel.installColorsAndFont(this, "Label.background", "Label.foreground", "Century Gothic");
		 
	 }
 }
