package test;

import java.io.File;
import java.util.ArrayList;

public class DataReader {
	
	public static void main(String[] args) {
		DataReader dataReader = new DataReader();
		dataReader.getData(args[0]);
	}
	
	public File getDirectory(String strDir) {
		
		File myDirectory = new File(strDir);
		return myDirectory;
		
	}
	private File[] getListOfFilesFromDirectory(File dataDir) {
		for(File file:dataDir.listFiles()) {
			System.out.println(file.getAbsolutePath());
		}
		return dataDir.listFiles();
		
	}
	public ArrayList <String> readFiles(File[] files){
		ArrayList <String> messages= new ArrayList<String>();
		//some logic
		return messages;
		
	}
	public ArrayList<String> getData(String strDir){
		// (1) get directory
		File myDir = getDirectory(strDir);
		// (2)
		File[] files = getListOfFilesFromDirectory(myDir);
		
		ArrayList <String> messages= readFiles(files);
		
		return messages;
	}
}
