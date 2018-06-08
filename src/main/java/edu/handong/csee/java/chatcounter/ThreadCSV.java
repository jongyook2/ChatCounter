package edu.handong.csee.java.chatcounter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public class ThreadCSV implements Runnable{
	String csv;
	List<List<String>> arrayCSV;
	DataReaderForCSV dataReaderForCSV = new DataReaderForCSV();
	public ThreadCSV(String csv, List<List<String>> arrayCSV) {
		this.csv=csv;
		this.arrayCSV=arrayCSV;
		
	}
	@Override
	public void run() {
		try {
			System.out.println(Thread.currentThread().getName()+"이 시작되었습니다.");
			//System.out.println("read "+csv);
			dataReaderForCSV.saveCSV(csv,arrayCSV);
			
		
		
		
		System.out.println(Thread.currentThread().getName()+"가 종료되었습니다");
}catch (Exception e) {}
}
}
