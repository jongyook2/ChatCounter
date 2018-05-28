package edu.handong.csee.java.chatcounter;
import java.io.File;
import java.io.FilenameFilter;

/**
 * ChatFileFilter Class interface FilenameFilter Class
 * if string ends with specific string, it returns bool expression.
 * @author Farmboy
 *
 */
public class ChatFileFilter implements FilenameFilter {
	private final String e;
	/**
	 * Constructor 
	 * @param e
	 */
	public ChatFileFilter(String e) {
		this.e=e;
	}
	/** 
	 * boolean method if name ends with e ->return true if not ->return false
	 * @param dir, name
	 */
	public boolean accept(File dir,String name) {

		return name.endsWith(e);}
}
