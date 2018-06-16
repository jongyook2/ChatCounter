package edu.handong.csee.java.chatcounter;
import java.io.File;
import java.util.ArrayList;

/**
 * DataReader Class separates file formats(csv or txt) and save it to the list
 * @author Farmboy
 *
 */
public class DataReader {
	/**
	 * readCSV method takes only .csv files from path
	 * @param path
	 * @return
	 */
	public static ArrayList<String> readCSV(String path) {
		File dirFile=new File(path);
		File []fileList = dirFile.listFiles();
		ArrayList<String> saveCSVPath = new ArrayList<String>();
		ChatFileFilter chatFileFilter = new ChatFileFilter(".csv");
		for(File tempFile:fileList) {
			if(chatFileFilter.accept(tempFile, tempFile.getName())) {
				String str=tempFile.getPath();
				String str2=str.replaceAll("\\\\", "/");
				saveCSVPath.add(str2);
			}
		}

		return saveCSVPath;
	}
	/**
	 * readTXT method takes only txt files from path
	 * @param path
	 * @return
	 */
	public static ArrayList<String> readTXT(String path) {
		File dirFile=new File(path);
		File []fileList = dirFile.listFiles();
		ArrayList<String> saveTXTPath = new ArrayList<String>();
		ChatFileFilter chatFileFilter = new ChatFileFilter(".txt");

		for(File tempFile:fileList) {
			if(chatFileFilter.accept(tempFile, tempFile.getName())) {
				String str=tempFile.getPath();
				String str2=str.replaceAll("\\\\", "/");
				saveTXTPath.add(str2);
			}
		}

		return saveTXTPath;
	}
}


