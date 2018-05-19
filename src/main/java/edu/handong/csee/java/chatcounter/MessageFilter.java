package edu.handong.csee.java.chatcounter;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MessageFilter {
	public List<List<String>> sumCSVTXT() throws IOException, ParseException{
		DataReaderForCSV dataReaderForCSV =new DataReaderForCSV();
		DataReaderForTXT dataReaderForTXT =new DataReaderForTXT();
		List<List<String>> csvData=dataReaderForCSV.saveCSV();
		List<List<String>> txtData=dataReaderForTXT.saveTXT();
		List<List<String>> notRedundantData =new ArrayList<List<String>>();
		List<List<String>> sumData = new ArrayList<List<String>>(csvData);
		sumData.addAll(txtData);

//		Set<List<String>> hs = new HashSet<List<String>>();
//		hs.addAll(sumData);
//		sumData.clear();
//		sumData.addAll(hs);

		PrintWriter out = new PrintWriter("C:\\Users\\Farmboy\\Desktop\\HW\\java\\checkRD.txt");
		out.println(sumData);
		return sumData;
	}
}
