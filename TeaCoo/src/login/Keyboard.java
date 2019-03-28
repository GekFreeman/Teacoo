package login;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JWindow;
import javax.swing.border.Border;

	public class Keyboard extends JWindow implements MouseListener,MouseMotionListener{
		
		private final int width = 300;
		private final int height = 120;
		private LoginMain kbFrame = null;
		
	    @SuppressWarnings("unused")  
	    private boolean flg = false;  
	    @SuppressWarnings("unused")  
	    private boolean flgs = false;  
	    private int i=1;  
	    private Color fcolor = new Color(230,198,125);  
	    private Color color=Color.white;  
	    private JLabel t0,t1,t2,t3,t4,t5,t6,t7,t8,t9,Backspace;  
	    private JLabel Q,W,E,R,T,Y,U,I,O,P;  
	    private JLabel Caps,A,S,D,F,G,H,J,K,L;  
	    private JLabel Z,X,C,V,BS,N,M,FI1,FI2;
	      
	    public static String superstr;  
		
		//构造函数
		public Keyboard(LoginMain loginFrame, int x, int y) {
			
			getContentPane().setBackground(new Color(201, 100, 102));
			this.setLocation(x,y);
			keyboardUI();
			this.kbFrame = loginFrame;
		}
		
		public void keyboardUI() {
			
			   getContentPane().setLayout(null);
			   this.setSize(new Dimension(width,height));
			   
			   Border B = BorderFactory.createEtchedBorder();  
		          
			    FI1 = new JLabel(",");  
			    FI1.setForeground(new Color(255, 255, 255));
		        FI1.setHorizontalAlignment(JLabel.CENTER);  
		        FI1.setBorder(B);  
		        FI1.addMouseListener(this);  
		        FI1.setBounds(new Rectangle(227, 62, 25, 25));  
		        this.getContentPane().add(FI1);  
		          
		        FI2 = new JLabel(".");  
		        FI2.setForeground(new Color(255, 255, 255));
		        FI2.setHorizontalAlignment(JLabel.CENTER);  
		        FI2.setBorder(B);  
		        FI2.addMouseListener(this);  
		        FI2.setBounds(new Rectangle(252, 62, 25, 25));  
		        this.getContentPane().add(FI2);  
		        
		        t1 = new JLabel("1");  
		        t1.setForeground(new Color(255, 255, 255));
		        t1.setHorizontalAlignment(JLabel.CENTER);  
		        t1.setBorder(B);  
		        t1.addMouseListener( this);  
		        t1.setBounds(new Rectangle(2, 2, 25, 25));  
		        this.getContentPane().add(t1);  
		          
		        t2 = new JLabel("2");  
		        t2.setForeground(new Color(255, 255, 255));
		        t2.setHorizontalAlignment(JLabel.CENTER);  
		        t2.setBorder(B);  
		        t2.addMouseListener( this);  
		        t2.setBounds(new Rectangle(32, 2, 25, 25));  
		        this.getContentPane().add(t2);  
		          
		        t3 = new JLabel("3");  
		        t3.setForeground(new Color(255, 255, 255));
		        t3.setHorizontalAlignment(JLabel.CENTER);  
		        t3.setBorder(B);  
		        t3.addMouseListener( this);  
		        t3.setBounds(new Rectangle(62, 2, 25, 25));  
		        this.getContentPane().add(t3);  
		          
		        t4 = new JLabel("4");  
		        t4.setForeground(new Color(255, 255, 255));
		        t4.setHorizontalAlignment(JLabel.CENTER);  
		        t4.setBorder(B);  
		        t4.addMouseListener(this);  
		        t4.setBounds(new Rectangle(92, 2, 25, 25));  
		        this.getContentPane().add(t4);  
		          
		        t5 = new JLabel("5");  
		        t5.setForeground(new Color(255, 255, 255));
		        t5.setHorizontalAlignment(JLabel.CENTER);  
		        t5.setBorder(B);  
		        t5.addMouseListener(this);  
		        t5.setBounds(new Rectangle(122, 2, 25, 25));  
		        this.getContentPane().add(t5);  
		          
		        t6 = new JLabel("6");  
		        t6.setForeground(new Color(255, 255, 255));
		        t6.setHorizontalAlignment(JLabel.CENTER);  
		        t6.setBorder(B);  
		        t6.addMouseListener(this);  
		        t6.setBounds(new Rectangle(152, 2, 25, 25));  
		        this.getContentPane().add(t6);  
		          
		        t7 = new JLabel("7");  
		        t7.setForeground(new Color(255, 255, 255));
		        t7.setHorizontalAlignment(JLabel.CENTER);  
		        t7.setBorder(B);  
		        t7.addMouseListener(this);  
		        t7.setBounds(new Rectangle(182, 2, 25, 25));  
		        this.getContentPane().add(t7);  
		          
		        t8 = new JLabel("8");  
		        t8.setForeground(new Color(255, 255, 255));
		        t8.setHorizontalAlignment(JLabel.CENTER);  
		        t8.setBorder(B);  
		        t8.addMouseListener(this);  
		        t8.setBounds(new Rectangle(212, 2, 25, 25));  
		        this.getContentPane().add(t8);  
		          
		        t9 = new JLabel("9");  
		        t9.setForeground(new Color(255, 255, 255));
		        t9.setHorizontalAlignment(JLabel.CENTER);  
		        t9.setBorder(B);  
		        t9.addMouseListener(this);  
		        t9.setBounds(new Rectangle(242, 2, 25, 25));  
		        this.getContentPane().add(t9);  
		          
		        t0 = new JLabel("0");  
		        t0.setForeground(new Color(255, 255, 255));
		        t0.setHorizontalAlignment(JLabel.CENTER);  
		        t0.setBorder(B);  
		        t0.addMouseListener(this);  
		        t0.setBounds(new Rectangle(272, 2, 25, 25));  
		        this.getContentPane().add(t0);  
		          

		          
		        Backspace = new JLabel("del");  
		        Backspace.setForeground(new Color(255, 255, 255));
		        Backspace.setHorizontalAlignment(JLabel.CENTER);  
		        Backspace.setBorder(B);  
		        Backspace.addMouseListener(this);  
		        Backspace.setBounds(new Rectangle(252, 32, 45, 25));  
		        this.getContentPane().add(Backspace);  
		          
		        Q = new JLabel("q");  
		        Q.setForeground(new Color(255, 255, 255));
		        Q.setHorizontalAlignment(JLabel.CENTER);  
		        Q.setBorder(B);  
		        Q.addMouseListener(this);  
		        Q.setBounds(new Rectangle(2, 32, 25, 25));  
		        this.getContentPane().add(Q);  
		          
		        W = new JLabel("w");  
		        W.setForeground(new Color(255, 255, 255));
		        W.setHorizontalAlignment(JLabel.CENTER);  
		        W.setBorder(B);  
		        W.addMouseListener(this);  
		        W.setBounds(new Rectangle(27, 32, 25, 25));  
		        this.getContentPane().add(W);  
		          
		        E = new JLabel("e");  
		        E.setForeground(new Color(255, 255, 255));
		        E.setHorizontalAlignment(JLabel.CENTER);  
		        E.setBorder(B);  
		        E.addMouseListener(this);  
		        E.setBounds(new Rectangle(52, 32, 25, 25));  
		        this.getContentPane().add(E);  
		          
		        R = new JLabel("r");  
		        R.setForeground(new Color(255, 255, 255));
		        R.setHorizontalAlignment(JLabel.CENTER);  
		        R.setBorder(B);  
		        R.addMouseListener(this);  
		        R.setBounds(new Rectangle(77, 32, 25, 25));  
		        this.getContentPane().add(R);  
		          
		        T = new JLabel("t");  
		        T.setForeground(new Color(255, 255, 255));
		        T.setHorizontalAlignment(JLabel.CENTER);  
		        T.setBorder(B);  
		        T.addMouseListener(this);  
		        T.setBounds(new Rectangle(102, 32, 25, 25));  
		        this.getContentPane().add(T);  
		          
		        Y = new JLabel("y");  
		        Y.setForeground(new Color(255, 255, 255));
		        Y.setHorizontalAlignment(JLabel.CENTER);  
		        Y.setBorder(B);  
		        Y.addMouseListener(this);  
		        Y.setBounds(new Rectangle(127, 32, 25, 25));  
		        this.getContentPane().add(Y);  
		          
		        U = new JLabel("u");  
		        U.setForeground(new Color(255, 255, 255));
		        U.setHorizontalAlignment(JLabel.CENTER);  
		        U.setBorder(B);  
		        U.addMouseListener(this);  
		        U.setBounds(new Rectangle(152, 32, 25, 25));  
		        this.getContentPane().add(U);  
		          
		        I = new JLabel("i");  
		        I.setForeground(new Color(255, 255, 255));
		        I.setHorizontalAlignment(JLabel.CENTER);  
		        I.setBorder(B);  
		        I.addMouseListener(this);  
		        I.setBounds(new Rectangle(177, 32, 25, 25));  
		        this.getContentPane().add(I);  
		          
		        O = new JLabel("o");  
		        O.setForeground(new Color(255, 255, 255));
		        O.setHorizontalAlignment(JLabel.CENTER);  
		        O.setBorder(B);  
		        O.addMouseListener(this);  
		        O.setBounds(new Rectangle(202, 32, 25, 25));  
		        this.getContentPane().add(O);  
		          
		        P = new JLabel("p");  
		        P.setForeground(new Color(255, 255, 255));
		        P.setHorizontalAlignment(JLabel.CENTER);  
		        P.setBorder(B);  
		        P.addMouseListener(this);  
		        P.setBounds(new Rectangle(227, 32, 25, 25));  
		        this.getContentPane().add(P);  
		          
		  
		        Caps = new JLabel("Caps");  
		        Caps.setForeground(new Color(255, 255, 255));
		        Caps.setHorizontalAlignment(JLabel.CENTER);  
		        Caps.setBorder(B);  
		        Caps.setBounds(new Rectangle(182, 92, 115, 25));  
		        Caps.addMouseListener(this);  
		        this.getContentPane().add(Caps);  
		          
		          
		        A = new JLabel("a");  
		        A.setForeground(new Color(255, 255, 255));
		        A.setHorizontalAlignment(JLabel.CENTER);  
		        A.setBorder(B);  
		        A.addMouseListener(this);  
		        A.setBounds(new Rectangle(2, 62, 25, 25));  
		        this.getContentPane().add(A);  
		          
		          
		        S = new JLabel("s");  
		        S.setForeground(new Color(255, 255, 255));
		        S.setHorizontalAlignment(JLabel.CENTER);  
		        S.setBorder(B);  
		        S.addMouseListener(this);  
		        S.setBounds(new Rectangle(27, 62, 25, 25));  
		        this.getContentPane().add(S);  
		          
		        D = new JLabel("d");  
		        D.setForeground(new Color(255, 255, 255));
		        D.setHorizontalAlignment(JLabel.CENTER);  
		        D.setBorder(B);  
		        D.addMouseListener(this);  
		        D.setBounds(new Rectangle(52, 62, 25, 25));  
		        this.getContentPane().add(D);  
		          
		        F = new JLabel("f");  
		        F.setForeground(new Color(255, 255, 255));
		        F.setHorizontalAlignment(JLabel.CENTER);  
		        F.setBorder(B);  
		        F.addMouseListener( this);  
		        F.setBounds(new Rectangle(77, 62, 25, 25));  
		        this.getContentPane().add(F);  
		          
		        G = new JLabel("g");  
		        G.setForeground(new Color(255, 255, 255));
		        G.setHorizontalAlignment(JLabel.CENTER);  
		        G.setBorder(B);  
		        G.addMouseListener( this);  
		        G.setBounds(new Rectangle(102, 62, 25, 25));  
		        this.getContentPane().add(G);  
		          
		        H = new JLabel("h");  
		        H.setForeground(new Color(255, 255, 255));
		        H.setHorizontalAlignment(JLabel.CENTER);  
		        H.setBorder(B);  
		        H.addMouseListener(this);  
		        H.setBounds(new Rectangle(127, 62, 25, 25));  
		        this.getContentPane().add(H);  
		          
		        J = new JLabel("j");  
		        J.setForeground(new Color(255, 255, 255));
		        J.setHorizontalAlignment(JLabel.CENTER);  
		        J.setBorder(B);  
		        J.addMouseListener( this);  
		        J.setBounds(new Rectangle(152, 62, 25, 25));  
		        this.getContentPane().add(J);  
		          
		        K = new JLabel("k");  
		        K.setForeground(new Color(255, 255, 255));
		        K.setHorizontalAlignment(JLabel.CENTER);  
		        K.setBorder(B);  
		        K.addMouseListener(this);  
		        K.setBounds(new Rectangle(177, 62, 25, 25));  
		        this.getContentPane().add(K);  
		          
		        L = new JLabel("l");  
		        L.setForeground(new Color(255, 255, 255));
		        L.setHorizontalAlignment(JLabel.CENTER);  
		        L.setBorder(B);  
		        L.addMouseListener(this);  
		        L.setBounds(new Rectangle(202, 62, 25, 25));  
		        this.getContentPane().add(L);  
		          
		        Z = new JLabel("z");  
		        Z.setForeground(new Color(255, 255, 255));
		        Z.setHorizontalAlignment(JLabel.CENTER);  
		        Z.setBorder(B);  
		        Z.addMouseListener(this);  
		        Z.setBounds(new Rectangle(2, 92, 25, 25));  
		        this.getContentPane().add(Z);  
		          
		        X = new JLabel("x");  
		        X.setForeground(new Color(255, 255, 255));
		        X.setHorizontalAlignment(JLabel.CENTER);  
		        X.setBorder(B);  
		        X.addMouseListener( this);  
		        X.setBounds(new Rectangle(27, 92, 25, 25));  
		        this.getContentPane().add(X);  
		          
		        C = new JLabel("c");  
		        C.setForeground(new Color(255, 255, 255));
		        C.setHorizontalAlignment(JLabel.CENTER);  
		        C.setBorder(B);  
		        C.addMouseListener( this);  
		        C.setBounds(new Rectangle(52, 92, 25, 25));  
		        this.getContentPane().add(C);  
		          
		        V = new JLabel("v");  
		        V.setForeground(new Color(255, 255, 255));
		        V.setHorizontalAlignment(JLabel.CENTER);  
		        V.setBorder(B);  
		        V.addMouseListener( this);  
		        V.setBounds(new Rectangle(77, 92, 25, 25));  
		        this.getContentPane().add(V);  
		          
		        BS = new JLabel("b");  
		        BS.setForeground(new Color(255, 255, 255));
		        BS.setHorizontalAlignment(JLabel.CENTER);  
		        BS.setBorder(B);  
		        BS.addMouseListener( this);  
		        BS.setBounds(new Rectangle(102, 92, 25, 25));  
		        this.getContentPane().add(BS);  
		          
		        N = new JLabel("n");  
		        N.setForeground(new Color(255, 255, 255));
		        N.setHorizontalAlignment(JLabel.CENTER);  
		        N.setBorder(B);  
		        N.addMouseListener( this);  
		        N.setBounds(new Rectangle(127, 92, 25, 25));  
		        this.getContentPane().add(N);  
		          
		        M = new JLabel("m");  
		        M.setForeground(new Color(255, 255, 255));
		        M.setHorizontalAlignment(JLabel.CENTER);  
		        M.setBorder(B);  
		        M.addMouseListener( this);  
		        M.setBounds(new Rectangle(152, 92, 25, 25));  
		        this.getContentPane().add(M);  
		        
		        this.addMouseMotionListener(this);
		 	   this.addMouseListener(this);
		 	   this.setVisible(true);
			   
		}
		
		public void actionPerformed(ActionEvent e) {  
			  
	    }  
	    public void mouseClicked(MouseEvent e){}  
	    
	    public void mouseEntered(MouseEvent e) {  
	        //鼠标进入到组件上时调用
	        //设置颜色  

	        if(e.getSource()==FI1){FI1.setForeground(fcolor);}  
	        if(e.getSource()==FI2){FI2.setForeground(fcolor);} 
	        if(e.getSource()==Z){Z.setForeground(fcolor);}  
	        if(e.getSource()==X){X.setForeground(fcolor);}  
	        if(e.getSource()==C){C.setForeground(fcolor);}  
	        if(e.getSource()==V){V.setForeground(fcolor);}  
	        if(e.getSource()==BS){BS.setForeground(fcolor);}  
	        if(e.getSource()==N){N.setForeground(fcolor);}  
	        if(e.getSource()==M){M.setForeground(fcolor);}  
	        if(e.getSource()==W){W.setForeground(fcolor);}  
	        if(e.getSource()==E){E.setForeground(fcolor);}  
	        if(e.getSource()==R){R.setForeground(fcolor);}  
	        if(e.getSource()==T){T.setForeground(fcolor);}  
	        if(e.getSource()==Y){Y.setForeground(fcolor);}  
	        if(e.getSource()==U){U.setForeground(fcolor);}  
	        if(e.getSource()==I){I.setForeground(fcolor);}  
	        if(e.getSource()==O){O.setForeground(fcolor);}  
	        if(e.getSource()==P){P.setForeground(fcolor);}  
	        if(e.getSource()==A){A.setForeground(fcolor);}  
	        if(e.getSource()==S){S.setForeground(fcolor);}  
	        if(e.getSource()==D){D.setForeground(fcolor);}  
	        if(e.getSource()==F){F.setForeground(fcolor);}  
	        if(e.getSource()==G){G.setForeground(fcolor);}  
	        if(e.getSource()==H){H.setForeground(fcolor);}  
	        if(e.getSource()==J){J.setForeground(fcolor);}  
	        if(e.getSource()==K){K.setForeground(fcolor);}  
	        if(e.getSource()==L){L.setForeground(fcolor);}  
	        if(e.getSource()==Caps){Caps.setForeground(fcolor);}  
	        if(e.getSource()==t0){t0.setForeground(fcolor);}  
	        if(e.getSource()==t1){t1.setForeground(fcolor);}  
	        if(e.getSource()==t2){t2.setForeground(fcolor);}  
	        if(e.getSource()==t3){t3.setForeground(fcolor);}  
	        if(e.getSource()==t4){t4.setForeground(fcolor);}  
	        if(e.getSource()==t5){t5.setForeground(fcolor);}  
	        if(e.getSource()==t6){t6.setForeground(fcolor);}  
	        if(e.getSource()==t7){t7.setForeground(fcolor);}  
	        if(e.getSource()==t8){t8.setForeground(fcolor);}  
	        if(e.getSource()==t9){t9.setForeground(fcolor);}  
	        if(e.getSource()==Backspace){Backspace.setForeground(fcolor);}  
	        if(e.getSource()==Q){Q.setForeground(fcolor);}  
	        if(e.getSource()==W){W.setForeground(fcolor);}  
	    }  
	    public void mouseExited(MouseEvent e) {  
	        //鼠标离开组件时调用
	        //恢复颜色  
	        if(e.getSource()==FI1){FI1.setForeground(color);}  
	        if(e.getSource()==FI2){FI2.setForeground(color);}  
	        if(e.getSource()==Z){Z.setForeground(color);}  
	        if(e.getSource()==X){X.setForeground(color);}  
	        if(e.getSource()==C){C.setForeground(color);}  
	        if(e.getSource()==V){V.setForeground(color);}  
	        if(e.getSource()==BS){BS.setForeground(color);}  
	        if(e.getSource()==N){N.setForeground(color);}  
	        if(e.getSource()==M){M.setForeground(color);}  
	        if(e.getSource()==W){W.setForeground(color);}  
	        if(e.getSource()==E){E.setForeground(color);}  
	        if(e.getSource()==R){R.setForeground(color);}  
	        if(e.getSource()==T){T.setForeground(color);}  
	        if(e.getSource()==Y){Y.setForeground(color);}  
	        if(e.getSource()==U){U.setForeground(color);}  
	        if(e.getSource()==I){I.setForeground(color);}  
	        if(e.getSource()==O){O.setForeground(color);}  
	        if(e.getSource()==P){P.setForeground(color);}  
	        if(e.getSource()==A){A.setForeground(color);}  
	        if(e.getSource()==S){S.setForeground(color);}  
	        if(e.getSource()==D){D.setForeground(color);}  
	        if(e.getSource()==F){F.setForeground(color);}  
	        if(e.getSource()==G){G.setForeground(color);}  
	        if(e.getSource()==H){H.setForeground(color);}  
	        if(e.getSource()==J){J.setForeground(color);}  
	        if(e.getSource()==K){K.setForeground(color);}  
	        if(e.getSource()==L){L.setForeground(color);}  
	        if(e.getSource()==Caps){Caps.setForeground(color);}  
	        if(e.getSource()==t0){t0.setForeground(color);}  
	        if(e.getSource()==t1){t1.setForeground(color);}  
	        if(e.getSource()==t2){t2.setForeground(color);}  
	        if(e.getSource()==t3){t3.setForeground(color);}  
	        if(e.getSource()==t4){t4.setForeground(color);}  
	        if(e.getSource()==t5){t5.setForeground(color);}  
	        if(e.getSource()==t6){t6.setForeground(color);}  
	        if(e.getSource()==t7){t7.setForeground(color);}  
	        if(e.getSource()==t8){t8.setForeground(color);}  
	        if(e.getSource()==t9){t9.setForeground(color);}  
	        if(e.getSource()==Backspace){Backspace.setForeground(color);}  
	        if(e.getSource()==Q){Q.setForeground(color);}  
	        if(e.getSource()==W){W.setForeground(color);}  
	    }  
	    @SuppressWarnings("static-access")  
	    public void mousePressed(MouseEvent e) {// 鼠标按键在组件上按下时调用
	          
	        //监听大小写并且切
	        if(e.getSource()==Caps){  
	            if(i==1){  
	                flg = true;  
	                W.setText("W");E.setText("E");R.setText("R");T.setText("T");Y.setText("Y");  
	                U.setText("U");I.setText("I");O.setText("O");P.setText("P");A.setText("A");  
	                S.setText("S");D.setText("D");F.setText("F");G.setText("G");H.setText("H");  
	                J.setText("J");K.setText("K");L.setText("L");Z.setText("Z");X.setText("X");  
	                C.setText("C");V.setText("V");BS.setText("B");N.setText("N");M.setText("M");  
	                Q.setText("Q");FI1.setText(",");FI2.setText(".");  
	            i=2;  
	            }else{  
	                flg = false;  
	                W.setText("w");E.setText("e");R.setText("r");T.setText("t");Y.setText("y");  
	                U.setText("u");I.setText("i");O.setText("o");P.setText("p");A.setText("a");  
	                S.setText("s");D.setText("d");F.setText("f");G.setText("g");H.setText("h");  
	                J.setText("j");K.setText("k");L.setText("l");Z.setText("z");X.setText("x");  
	                C.setText("c");V.setText("v");BS.setText("b");N.setText("n");M.setText("m");  
	                Q.setText("q"); FI1.setText(",");FI2.setText(".");
	            i=1;  
	            }  
	        }  
	        /** 
	         * 监听数字与符号并且相互切
	         * */  
	//
	        /** 
	         * 设置按键的字母字? 
	         * */  
	        if(e.getSource()==Q){String s = getstring();LoginMain.passwordField.setText(s+Q.getText());}  
	        if(e.getSource()==W){String s = getstring();LoginMain.passwordField.setText(s+W.getText());}  
	        if(e.getSource()==E){String s = getstring();LoginMain.passwordField.setText(s+E.getText());}  
	        if(e.getSource()==R){String s = getstring();LoginMain.passwordField.setText(s+R.getText());}  
	        if(e.getSource()==T){String s = getstring();LoginMain.passwordField.setText(s+T.getText());}  
	        if(e.getSource()==Y){String s = getstring();LoginMain.passwordField.setText(s+Y.getText());}  
	        if(e.getSource()==U){String s = getstring();LoginMain.passwordField.setText(s+U.getText());}  
	        if(e.getSource()==I){String s = getstring();LoginMain.passwordField.setText(s+I.getText());}  
	        if(e.getSource()==O){String s = getstring();LoginMain.passwordField.setText(s+O.getText());}  
	        if(e.getSource()==P){String s = getstring();LoginMain.passwordField.setText(s+P.getText());}  
	        if(e.getSource()==A){String s = getstring();LoginMain.passwordField.setText(s+A.getText());}  
	        if(e.getSource()==S){String s = getstring();LoginMain.passwordField.setText(s+S.getText());}  
	        if(e.getSource()==D){String s = getstring();LoginMain.passwordField.setText(s+D.getText());}  
	        if(e.getSource()==F){String s = getstring();LoginMain.passwordField.setText(s+F.getText());}  
	        if(e.getSource()==G){String s = getstring();LoginMain.passwordField.setText(s+G.getText());}  
	        if(e.getSource()==H){String s = getstring();LoginMain.passwordField.setText(s+H.getText());}  
	        if(e.getSource()==J){String s = getstring();LoginMain.passwordField.setText(s+J.getText());}  
	        if(e.getSource()==K){String s = getstring();LoginMain.passwordField.setText(s+K.getText());}  
	        if(e.getSource()==L){String s = getstring();LoginMain.passwordField.setText(s+L.getText());}  
	        if(e.getSource()==Z){String s = getstring();LoginMain.passwordField.setText(s+Z.getText());}  
	        if(e.getSource()==X){String s = getstring();LoginMain.passwordField.setText(s+X.getText());}  
	        if(e.getSource()==C){String s = getstring();LoginMain.passwordField.setText(s+C.getText());}  
	        if(e.getSource()==V){String s = getstring();LoginMain.passwordField.setText(s+V.getText());}  
	        if(e.getSource()==BS){String s = getstring();LoginMain.passwordField.setText(s+BS.getText());}  
	        if(e.getSource()==N){String s = getstring();LoginMain.passwordField.setText(s+N.getText());}  
	        if(e.getSource()==M){String s = getstring();LoginMain.passwordField.setText(s+M.getText());}  
	        /** 
	         * 设置按键的符
	         * */  
	        if(e.getSource()==t0){String s = getstring();LoginMain.passwordField.setText(s+t0.getText());}  
	        if(e.getSource()==t1){String s = getstring();LoginMain.passwordField.setText(s+t1.getText());}  
	        if(e.getSource()==t2){String s = getstring();LoginMain.passwordField.setText(s+t2.getText());}  
	        if(e.getSource()==t3){String s = getstring();LoginMain.passwordField.setText(s+t3.getText());}  
	        if(e.getSource()==t4){String s = getstring();LoginMain.passwordField.setText(s+t4.getText());}  
	        if(e.getSource()==t5){String s = getstring();LoginMain.passwordField.setText(s+t5.getText());}  
	        if(e.getSource()==t6){String s = getstring();LoginMain.passwordField.setText(s+t6.getText());}  
	        if(e.getSource()==t7){String s = getstring();LoginMain.passwordField.setText(s+t7.getText());}  
	        if(e.getSource()==t8){String s = getstring();LoginMain.passwordField.setText(s+t8.getText());}  
	        if(e.getSource()==t9){String s = getstring();LoginMain.passwordField.setText(s+t9.getText());} 
	        if(e.getSource()==FI1){String s = getstring();LoginMain.passwordField.setText(s+FI1.getText());}  
	        if(e.getSource()==FI2){String s = getstring();LoginMain.passwordField.setText(s+FI2.getText());}  
	        /** 
	         * 逐个清除字符 
	         * */  
	        if(e.getSource()==Backspace){  
	            String s = getstring();  
	            if(s.length()>0){  
	            System.out.println(s);  
	            s = s.substring(0, s.length()-1);  
	            LoginMain.passwordField.setText(s);  
	            }  
	        }  
	       // System.out.println(getstring());  
	    }  
	    public void mouseReleased(MouseEvent e) {  
	        //鼠标按钮在组件上释放时调用    
	    }  
	    /** 
	     * <br>返回password的字符串 
	     * <br>return superstr; 
	     * */  
	    @SuppressWarnings("static-access")  
	    public String getstring(){  
	        superstr = String.valueOf(LoginMain.passwordField.getPassword());  
	        if(superstr.equals("")||superstr.equals(null)){  
	            superstr = "";  
	        }  
	        return superstr;  
	    }  
			
		
		@Override
		public void mouseDragged(MouseEvent e) {
		}
		@Override
		public void mouseMoved(MouseEvent e) {
			// TODO Auto-generated method stub
		}
	}


