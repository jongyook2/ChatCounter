package edu.handong.csee.java.chatcounter;
import java.io.File;
import java.util.ArrayList;




public class DataReader {
	public static ArrayList<String> readCSV() {
		String path=("C:/Users/Farmboy/Desktop/HW/java/chatdata");
		File dirFile=new File(path);
		File []fileList = dirFile.listFiles();
		ArrayList<String> saveCSVPath = new ArrayList<String>();
		ChatFileFilter chatFileFilter = new ChatFileFilter();
		//int i=0;
		for(File tempFile:fileList) {
			if(chatFileFilter.accept(tempFile, tempFile.getName())) {
				String str=tempFile.getPath();
				String str2=str.replaceAll("\\\\", "/");
				saveCSVPath.add(str2);
				//System.out.println(saveCSVPath.get(0));
				//i++;
				//System.out.println(tempFile.getPath()+"이다.");
				
			}
		}
		
		return saveCSVPath;
	}
	public static ArrayList<String> readTXT() {
		String path=("C:/Users/Farmboy/Desktop/HW/java/chatdata");
		File dirFile=new File(path);
		File []fileList = dirFile.listFiles();
		ArrayList<String> saveTXTPath = new ArrayList<String>();
		ChatFileFilter chatFileFilter = new ChatFileFilter();
		//int i=0;
		for(File tempFile:fileList) {
			if(!chatFileFilter.accept(tempFile, tempFile.getName())) {
				String str=tempFile.getPath();
				String str2=str.replaceAll("\\\\", "/");
				saveTXTPath.add(str2);
				//System.out.println(saveCSVPath.get(0));
				//i++;
				//System.out.println(tempFile.getPath()+"이다.");
				
			}
		}
		
		return saveTXTPath;
	}
}

	/*}
	public static void main(String[] agrs) {
		readCSV();
	}
	*/

