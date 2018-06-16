package edu.handong.csee.java.chatcounter;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * MessageFilter Class sums csv files and txt files and removes duplicate messages 
 * contains sumCSVTXT methods
 * @author Farmboy
 *
 */
public class MessageFilter {
	/**
	 * sumCSVTXT method sums two formats file(by each reader) and remove duplicate messages
	
	 * @param csvData
	 * @param txtData
	 * @return
	 * @throws IOException
	 * @throws ParseException
	 */
	public List<List<String>> sumCSVTXT(List<List<String>> csvData, List<List<String>> txtData) throws IOException, ParseException{

		//List<List<String>> notRedundantData =new ArrayList<List<String>>();
		List<List<String>> sumData = new ArrayList<List<String>>(csvData);
		sumData.addAll(txtData);

		Set<List<String>> hs = new HashSet<List<String>>();
		hs.addAll(sumData);
		sumData.clear();
		sumData.addAll(hs);

		PrintWriter out = new PrintWriter("C:\\Users\\Farmboy\\Desktop\\HW\\java\\checkRD.txt");
		out.println(sumData);
		return sumData;
	}
}
