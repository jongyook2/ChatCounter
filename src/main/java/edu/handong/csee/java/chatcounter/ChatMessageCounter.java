package edu.handong.csee.java.chatcounter;

import java.io.IOException;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * ChatMessageCounter Class has two method(countChat, sortIDSet)
 * By using User_IDs and frequency, counts how many they chat(countChat)
 * sortIDset sort the list based on values
 * @author Farmboy
 *
 */
public class ChatMessageCounter {

	/**
	 * This method count chat frequency and return sortIDset(countID,false) 
	 * countID is list of User Id and frequency  
	 * @param data
	 * @return
	 * @throws IOException
	 * @throws ParseException
	 */
	public HashMap<String,Integer> countChat(List<List<String>> data) throws IOException, ParseException{
		MessageFilter messageFilter =new MessageFilter();
		List<List<String>> sumData=data;
		HashMap<String, Integer> countID = new HashMap<String, Integer>();
		String idSet[] = new String[sumData.size()];
		for(int i=0;i<sumData.size();i++) {
			idSet[i]=(sumData.get(i).get(0));
		}
		List idSetList = Arrays.asList(idSet);
		Set<String> idSetReal = new HashSet<String>(idSetList);
		for(String s: idSetReal){
			countID.put(s, Collections.frequency(idSetList,s));
			//System.out.println(s + " " +Collections.frequency(idSetList,s));
		}

		return sortIDSet(countID,false);
	}
	/**
	 * This method sorts countIDSet based on values
	 * return sortedMap
	 * @param unsortMap
	 * @param order
	 * @return
	 */
	public HashMap<String, Integer> sortIDSet(Map<String, Integer> unsortMap, final boolean order)
	{
		List<Entry<String, Integer>> list = new LinkedList<Entry<String, Integer>>(unsortMap.entrySet());
		Collections.sort(list, new Comparator<Entry<String, Integer>>()
		{
			public int compare(Entry<String, Integer> compareFirst,
					Entry<String, Integer> compareSecond)
			{
				if (order)
				{
					return compareFirst.getValue().compareTo(compareSecond.getValue());
				}
				else
				{
					return compareSecond.getValue().compareTo(compareFirst.getValue());
				}
			}
		});

		HashMap<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
		for (Entry<String, Integer> entry : list)
		{
			sortedMap.put(entry.getKey(), entry.getValue());

		}

		return sortedMap;
	}
}
