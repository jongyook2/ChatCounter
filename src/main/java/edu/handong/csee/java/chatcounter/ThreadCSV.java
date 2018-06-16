package edu.handong.csee.java.chatcounter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

/**
 * ThreadCSV class interface Runnable class 
 * when thread starts, run method runs saveCSV method of DataReaderForCSV class
 * @author Farmboy
 *
 */
public class ThreadCSV implements Runnable{
	String csv;
	List<List<String>> arrayCSV;
	DataReaderForCSV dataReaderForCSV = new DataReaderForCSV();
	public ThreadCSV(String csv, List<List<String>> arrayCSV) {
		this.csv=csv;
		this.arrayCSV=arrayCSV;
	}

	/** 
	 * run saveCSV method of DataReaderForCSV class and print thread 
	 */
	public void run() {
		try {
			System.out.println(Thread.currentThread().getName()+" start.");
			System.out.println("read "+csv);
			dataReaderForCSV.saveCSV(csv,arrayCSV);
			System.out.println(Thread.currentThread().getName()+" end");
		}catch (Exception e) {}
	}
}
