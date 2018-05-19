package edu.handong.csee.java.chatcounter;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

public class DataWriter {
	public void converToTXT(HashMap<String,Integer> file) throws FileNotFoundException, IOException {
		
		FileWriter idCountFile = new FileWriter("C:\\Users\\Farmboy\\Desktop\\HW\\java\\idCount.txt");
		BufferedWriter out = new BufferedWriter(idCountFile);
		Iterator<Entry<String, Integer>> it = file.entrySet().iterator();
		out.write("kakao_id, count\r\n");
		while(it.hasNext()){
			HashMap.Entry<String,Integer> set = it.next();
			out.write(set.getKey()+", "+set.getValue()+"\r\n");
		}
		out.close();
	}
}
