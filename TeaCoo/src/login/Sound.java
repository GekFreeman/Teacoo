package login;

import java.applet.AudioClip; 
import javax.swing.*;

import java.io.*; 
import java.applet.Applet; 
import java.net.MalformedURLException; 
import java.net.URL; 

public class Sound extends JLabel{ 

public Sound(){ 
super(); 
try { 
URL cb; 
File f = new File("C:\\XETHseth workspace\\TeaCoo\\voice\\2.wav"); //����������������ļ����ڵľ���¹��
cb = f.toURL(); 
AudioClip aau; 
aau = Applet.newAudioClip(cb); 
aau.play();//ѭ������ aau.play() ���� aau.stop()ֹͣ���� 

} catch (MalformedURLException e) { 
e.printStackTrace(); 
} 
} 
}