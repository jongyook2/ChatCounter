package edu.handong.csee.java.chatcounter;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class DataReaderForTXT {
	List<List<String>> arrayTXT = new ArrayList<List<String>>();
	List<List<String>> setArrayTXT = new ArrayList<List<String>>();
	ArrayList <String> listTXTPath =new ArrayList <String>();
	DataReader dataReader = new DataReader();
	static String date;
	public List<List<String>> saveTXT() throws IOException, ParseException {
		listTXTPath= dataReader.readTXT();
		int i=0;
		BufferedReader csvReader=null;
		String line="";
		DateFormat date1 = new SimpleDateFormat("yyyy년 M월 d일");
		DateFormat date2 = new SimpleDateFormat("yyyy-MM-dd");
		DateFormat date3 = new SimpleDateFormat("a hh:mm");
		DateFormat date4 = new SimpleDateFormat("HH:mm");

		try {
			for(String csv:listTXTPath) {
				csvReader =Files.newBufferedReader(Paths.get(csv));

				while((line=csvReader.readLine())!=null){
					List<String> tempList = new ArrayList <String>();
					
					if(line.contains("---------------")) {
						String[] tempArray = line.split("---------------", 3);
						date=tempArray[1];
						date=date.trim();
						date=date.substring(0,12);
						Date dateOrigin=date1.parse(date);
						date=date2.format(dateOrigin);
					}

					else if(line.contains("오전")) {
						String[] tempArrayNew=line.split("]",3);
						tempArrayNew[0]=tempArrayNew[0].replace("[","");
						tempArrayNew[1]=tempArrayNew[1].replace("[","");
						tempArrayNew[1]=tempArrayNew[1].trim();
						tempArrayNew[2]=tempArrayNew[2].trim();
						Date dateOrigin1=date3.parse(tempArrayNew[1]);
						tempArrayNew[1]=date4.format(dateOrigin1);
						tempArrayNew[1]=date.concat(" ").concat(tempArrayNew[1]);
						tempList=Arrays.asList(tempArrayNew);
						arrayTXT.add(tempList);

					}
					else if(line.contains("오후")) {
						String[] tempArrayNew=line.split("]",3);
						tempArrayNew[0]=tempArrayNew[0].replace("[","");
						tempArrayNew[1]=tempArrayNew[1].replace("[","");
						tempArrayNew[1]=tempArrayNew[1].trim();
						tempArrayNew[2]=tempArrayNew[2].trim();
						Date dateOrigin1=date3.parse(tempArrayNew[1]);
						tempArrayNew[1]=date4.format(dateOrigin1);
						tempArrayNew[1]=date.concat(" ").concat(tempArrayNew[1]);
						tempList=Arrays.asList(tempArrayNew);
						arrayTXT.add(tempList);

					}
				}
			}

			for(List<String> str: arrayTXT) {
				System.out.println(str);
			}
//			for(List<String> str: arrayTXT) {
//				if(!setArrayTXT.contains(str)) {
//					setArrayTXT.add(str);
//				}
//			}	
			PrintWriter out = new PrintWriter("C:\\Users\\Farmboy\\Desktop\\HW\\java\\checkTXT.txt");
			out.println(arrayTXT);
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}finally {
			try {
				if(csvReader !=null) {
					csvReader.close();
				}
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
		return arrayTXT;
	}		
}			
