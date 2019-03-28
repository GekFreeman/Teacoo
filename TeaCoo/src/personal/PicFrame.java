package personal;
import javax.swing.JLabel;

import java.awt.event.*;

import javax.swing.*;

import java.awt.*;
/*
 * author:Tammy Pi
 * function:显示头像的Frame
 */
public class PicFrame extends JWindow implements MouseListener,MouseMotionListener{

	//10个头像，40*40大小；2行，每行5列
	private final int width = 460;
	private final int height = 190;
	private JLabel[] jlList = new JLabel[50];
	private int mouseX = 0;
	private int mouseY = 0;
	private editInfo picFrame;
	
	//构造函数
	public PicFrame(editInfo register1, int x, int y) {
	
		this.setLocation(x,y);
		initComponent();
		this.picFrame =register1;
	}
	//初始化界面
	public void initComponent() {
		
	   this.setLayout(null);
	   this.setIconImage(null);
	   this.setSize(new Dimension(width,height));
	   
	   for(int i=0;i<10;i++) {
		   
		   jlList[i] = new JLabel();
		   jlList[i].setName("image/profile/pf"+(i+1)+".jpg");
		   jlList[i].setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("image/profile/pf_"+(i+1)+".jpg")));
		   
		   jlList[i].setBounds(new Rectangle((80*(i%5)+10+10*(i%5)),(10+90*(i/5)),80,80));
		   jlList[i].setBorder(BorderFactory.createLineBorder(Color.GRAY));
		   this.add(jlList[i]);
		   
		   this.jlList[i].addMouseListener(this);
	   }
	   
	   this.addMouseMotionListener(this);
	   this.addMouseListener(this);
	   this.setVisible(true);
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
	  if(e.getSource() == this){
		  
		  mouseX = e.getX();
		  mouseY = e.getY();
	  }else {
		  
		  this.picFrame.setPic(((JLabel)e.getSource()).getName());
		  dispose();
	  }
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		
	   if(e.getSource() != this){
		   
			JLabel jlTemp = (JLabel) e.getSource();
			jlTemp.setBorder(BorderFactory.createLineBorder(Color.RED,2));
			
	   }
	}
	@Override
	public void mouseExited(MouseEvent e) {
		
	   if(e.getSource() != this){
		   
			JLabel jlTemp = (JLabel) e.getSource();
			jlTemp.setBorder(BorderFactory.createLineBorder(Color.GRAY));
	   }
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		
		this.setLocation(this.getX()+(e.getX()-mouseX),this.getY()+(e.getY()-mouseY));
	}
	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
		
	public void windowLostFocus(WindowEvent e){
			picFrame.presentframe.dispose();	
	}
	
}
