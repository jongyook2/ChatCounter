package edu.handong.csee.java.chatcounter;

import java.util.List;

/**
 * ThreadTXT class interface Runnable class 
 * when thread starts, run method runs saveTXT method of DataReaderForTXT class
 * @author Farmboy
 *
 */
public class ThreadTXT implements Runnable {
	String txt;
	List<List<String>> arrayTXT;
	DataReaderForTXT dataReaderForTXT= new DataReaderForTXT();
	public ThreadTXT(String txt, List<List<String>> arrayTXT) {
		this.txt=txt;
		this.arrayTXT=arrayTXT;
	}

	/** 
	 * run saveTXT method of DataReaderForTXT class and print thread 
	 */
	public void run() {
		try {
			System.out.println(Thread.currentThread().getName()+" start.");
			System.out.println("read "+txt);
			dataReaderForTXT.saveTXT(txt,arrayTXT);
			System.out.println(Thread.currentThread().getName()+" end.");
		}catch (Exception e) {}
	}
}
