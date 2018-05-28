package edu.handong.csee.java.chatcounter;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

/**
 * DataWriter Class saves result to csv file
 * contains convertToCSV
 * @author Farmboy
 *
 */
public class DataWriter {
	/**
	 * convertToCSV method save result of userIDset and frequency to csv file 
	 * @param file
	 * @param savePath
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void convertToCSV(HashMap<String,Integer> file, String savePath) throws FileNotFoundException, IOException {

		Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(savePath), "EUC-KR"));
		Iterator<Entry<String, Integer>> it = file.entrySet().iterator();
		out.write("kakao_id, count\r\n");
		while(it.hasNext()){
			HashMap.Entry<String,Integer> set = it.next();
			out.write(set.getKey()+", "+set.getValue()+"\r\n");
		}
		out.close();
	}
}
