package register;

import java.io.File;

import javax.swing.filechooser.FileFilter;


public class PortraitFilter extends FileFilter{
	String ext;
	public PortraitFilter(String ext){
		this.ext=ext;
	}
	public boolean accept(File file)
	{
		if(file.isDirectory()){
			return true;
		}
		
		String fileName=file.getName();
		int index=fileName.lastIndexOf('.');
		if(index>0 && index<fileName.length()-1){
			String extension=fileName.substring(index+1).toLowerCase();
			if(extension.equals(ext))
				return true;
			
		}
		return false;
	}
	
	public String getDescription()
	{
		if(ext.equals("jpg")||ext.equals("bmp") ||ext.equals("jpeg")||ext.equals("gif")||ext.equals("png"))
			return "Í¼ÏñÎÄ¼ş£¨*.jpg;*.bmp;*.jpeg;*gif;*png£©";
		return "";
	}
	

}