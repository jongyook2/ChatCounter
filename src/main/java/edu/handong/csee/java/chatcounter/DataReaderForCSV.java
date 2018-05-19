package edu.handong.csee.java.chatcounter;

import java.io.BufferedReader;
import java.io.File;
import java.io.Reader;
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
import org.apache.commons.csv.*;

public class DataReaderForCSV {
	List<List<String>> arrayCSV = new ArrayList<List<String>>();
	List<List<String>> setArrayCSV = new ArrayList<List<String>>();
	ArrayList <String> listCSVPath =new ArrayList <String>();
	DataReader dataReader = new DataReader();
	DateFormat date1 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	DateFormat date2 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	public List<List<String>> saveCSV() throws IOException, ParseException {
		listCSVPath= dataReader.readCSV();
		int i=0;
		ArrayList <String> result =new ArrayList<String>();	
		try {
			List<String> tempList = new ArrayList <String>();
			for(String csv:listCSVPath) {
				Reader csvReader = Files.newBufferedReader(Paths.get(csv));
				CSVParser csvParser = new CSVParser(csvReader, CSVFormat.DEFAULT);

				for (CSVRecord csvRecord : csvParser) {
					String date=csvRecord.get(0);
					String user=csvRecord.get(1);
					String message=csvRecord.get(2);
					message=message.trim();
					message=message.replace("\n", " ");
					if(!date.contains("Date")) {
						Date dateOrigin1=date1.parse(date);
						date=date2.format(dateOrigin1);
					}
					String[] tempArrayNew= {user,date,message};
					tempList=Arrays.asList(tempArrayNew);
					result.add(csvRecord.get(0).concat(" ").concat(csvRecord.get(1)).concat(" ").concat(csvRecord.get(2)));
					//System.out.println(tempList);
					arrayCSV.add(tempList);
				}

			}
		

//			for(i=0;i<result.size();i++)
//				System.out.println(arrayCSV.get(i));
//
//			for(List<String> str: arrayCSV) {
//				if(!setArrayCSV.contains(str)) {
//					setArrayCSV.add(str);
//				}
//			}	
			PrintWriter out = new PrintWriter("C:\\Users\\Farmboy\\Desktop\\HW\\java\\checkCSV.txt");
			out.println(arrayCSV);
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}
		return arrayCSV;
	}
}



		
		
		
		
		
		
		
	
	
	

