package edu.handong.csee.java.chatcounter;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ChatMessageCounter {

	public HashMap<String,Integer> countChat() throws IOException, ParseException{
		MessageFilter messageFilter =new MessageFilter();
		List<List<String>> sumData=messageFilter.sumCSVTXT();
		HashMap<String, Integer> countID = new HashMap<String, Integer>();
		String idSet[] = new String[sumData.size()];
		for(int i=0;i<sumData.size();i++) {
			idSet[i]=(sumData.get(i).get(0));
		}
		List idSetList = Arrays.asList(idSet);
		Set<String> idSetReal = new HashSet<String>(idSetList);
		for(String s: idSetReal){
			countID.put(s, Collections.frequency(idSetList,s));
			System.out.println(s + " " +Collections.frequency(idSetList,s));

		}



		return countID;
	}
}
