package edu.handong.csee.java.chatcounter;
import java.io.File;
import java.io.FilenameFilter;

public class ChatFileFilter implements FilenameFilter {
	
	public boolean accept(File dir,String name) {
		return name.toUpperCase().endsWith(".CSV");
	}

}
