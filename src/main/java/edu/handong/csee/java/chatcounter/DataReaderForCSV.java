package edu.handong.csee.java.chatcounter;

import java.io.Reader;
import java.io.FileNotFoundException;
import java.io.IOException;
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

/**
 * DataReaderForCSV Class parses to earn desired formats from csv files
 * contains saveCSV method 
 * @author Farmboy
 *
 */
public class DataReaderForCSV {
	List<List<String>> arrayCSV = new ArrayList<List<String>>();
	List<List<String>> setArrayCSV = new ArrayList<List<String>>();
	ArrayList <String> listCSVPath =new ArrayList <String>();
	DataReader dataReader = new DataReader();
	DateFormat date1 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	DateFormat date2 = new SimpleDateFormat("yyyy-MM-dd HH:mm");

	/**
	 * saveCSV methods parses csv file to date, user, message and returns arrayCSV
	 * @param path
	 * @return
	 * @throws IOException
	 * @throws ParseException
	 */
	public List<List<String>> saveCSV(String path) throws IOException, ParseException {
		listCSVPath= dataReader.readCSV(path);

		try {
			List<String> tempList = new ArrayList <String>();
			for(String csv:listCSVPath) {
				Reader csvReader = Files.newBufferedReader(Paths.get(csv));
				CSVParser csvParser = new CSVParser(csvReader, CSVFormat.DEFAULT);
				String tempD, tempM, tempU, temp;
				tempD=""; tempM=""; tempU=""; temp="";
				for (CSVRecord csvRecord : csvParser) {
					if(csvRecord.get(0).contains("Date")) {continue;}
					String date=csvRecord.get(0);
					//System.out.println(date);
					String user=csvRecord.get(1);
					String message=csvRecord.get(2);
					message=message.trim();
					message=message.replace("\n", " ");
					tempU=user;
					temp=message;
					if(!tempD.equals(date) && tempU.equals(user) && tempM.equals(message)) {
	
						message=message.concat(date);
						System.out.println(message);
					}
					tempD=date;
					System.out.println(date);
					if(!date.contains("Date")) {
						Date dateOrigin1=date1.parse(date);
						date=date2.format(dateOrigin1);
					}
					String[] tempArrayNew= {user,date,message};
					tempList=Arrays.asList(tempArrayNew);
					arrayCSV.add(tempList);
					tempM=temp;
					
				}
			}

		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}
		return arrayCSV;
	}
}














