package edu.handong.csee.java.chatcounter;

import java.io.IOException;
import java.text.ParseException;

public class Main {
	public static void main(String[] arg) throws IOException, ParseException {
		DataReaderForCSV dataReaderForCSV = new DataReaderForCSV();
		dataReaderForCSV.saveCSV();
		DataReaderForTXT dataReaderForTXT = new DataReaderForTXT();
		dataReaderForTXT.saveTXT();
		ChatMessageCounter chatMessageCounter = new ChatMessageCounter();
		MessageFilter messageFilter = new MessageFilter();
		messageFilter.sumCSVTXT();		
		DataWriter dataWriter =new DataWriter();
		dataWriter.converToTXT(chatMessageCounter.countChat());
	}
}
