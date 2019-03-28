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
File f = new File("C:\\XETHseth workspace\\TeaCoo\\voice\\2.wav"); //引号里面的是音乐文件所在的绝对鹿筋
cb = f.toURL(); 
AudioClip aau; 
aau = Applet.newAudioClip(cb); 
aau.play();//循环播放 aau.play() 单曲 aau.stop()停止播放 

} catch (MalformedURLException e) { 
e.printStackTrace(); 
} 
} 
}