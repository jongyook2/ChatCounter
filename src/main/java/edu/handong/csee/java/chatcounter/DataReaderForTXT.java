package edu.handong.csee.java.chatcounter;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


/**
 *  DataReaderForTXT Class parses to earn desired formats from txt files
 * contains saveTXT method
 * @author Farmboy
 *
 */
public class DataReaderForTXT {
	List<List<String>> arrayTXT = new ArrayList<List<String>>();
	List<List<String>> setArrayTXT = new ArrayList<List<String>>();
	ArrayList <String> listTXTPath =new ArrayList <String>();
	DataReader dataReader = new DataReader();
	static String date;
	/**
	 * saveTXT methods parses txt file to date, user, message and returns arrayTXT
	 * @param path
	 * @return
	 * @throws IOException
	 * @throws ParseException
	 */
	public List<List<String>> saveTXT(String path) throws IOException, ParseException {
		listTXTPath= dataReader.readTXT(path);
		int i=0;
		BufferedReader txtReader=null;
		String line="";
		int count=0;
		int save=0;;
		String saveMessage="";
		DateFormat date1 = new SimpleDateFormat("yyyy년 M월 d일");
		DateFormat date2 = new SimpleDateFormat("yyyy-MM-dd");
		DateFormat date3 = new SimpleDateFormat("a hh:mm");
		DateFormat date4 = new SimpleDateFormat("HH:mm");


		try {
			for(String txt:listTXTPath) {

				txtReader = new BufferedReader(new InputStreamReader(new FileInputStream(txt),"UTF-8"));
				for(i=0; i<3; i++) {
					txtReader.readLine();
				}
				while((line=txtReader.readLine())!=null){
					List<String> tempList = new ArrayList <String>();
					line=line.replace("사진","Photo");
					if(line.contains("---------------")) {
						String[] tempArray = line.split("---------------", 3);
						date=tempArray[1];
						date=date.trim();
						date=date.substring(0,12);
						Date dateOrigin;
						dateOrigin = date1.parse(date);
						date=date2.format(dateOrigin);


					}
					else if(line.contains("오전")) {
						String[] tempArrayNew=line.split("]",3);
						tempArrayNew[0]=tempArrayNew[0].replace("[","");
						tempArrayNew[1]=tempArrayNew[1].replace("[","");
						tempArrayNew[1]=tempArrayNew[1].trim();
						tempArrayNew[2]=tempArrayNew[2].trim();
						Date dateOrigin1;
						dateOrigin1 = date3.parse(tempArrayNew[1]);
						tempArrayNew[1]=date4.format(dateOrigin1);
						tempArrayNew[1]=date.concat(" ").concat(tempArrayNew[1]);
						tempList=Arrays.asList(tempArrayNew);
						arrayTXT.add(tempList);
						count++;

					}
					else if(line.contains("오후")) {
						String[] tempArrayNew=line.split("]",3);
						tempArrayNew[0]=tempArrayNew[0].replace("[","");
						tempArrayNew[1]=tempArrayNew[1].replace("[","");
						tempArrayNew[1]=tempArrayNew[1].trim();
						tempArrayNew[2]=tempArrayNew[2].trim();
						Date dateOrigin1;
						dateOrigin1 = date3.parse(tempArrayNew[1]);
						tempArrayNew[1]=date4.format(dateOrigin1);
						tempArrayNew[1]=date.concat(" ").concat(tempArrayNew[1]);
						tempList=Arrays.asList(tempArrayNew);
						arrayTXT.add(tempList);
						count++;

					}
					else {
						save=count;
						saveMessage=line;
						String []saveTemp= new String[3];
						for(i=0;i<3;i++) {
							saveTemp[i]=arrayTXT.get(save-1).get(i);
						}
						saveTemp[2]=saveTemp[2].concat(" ").concat(saveMessage);
						arrayTXT.remove(save-1);
						tempList=Arrays.asList(saveTemp);
						arrayTXT.add(save-1,tempList);

					}
				}   
			}
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}finally {
			try {
				if(txtReader !=null) {
					txtReader.close();
				}
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
		return arrayTXT;
	}
}
