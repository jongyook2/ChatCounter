package edu.handong.csee.java.chatcounter;

import java.util.List;

public class ThreadTXT implements Runnable {
	String txt;
	List<List<String>> arrayTXT;
	DataReaderForTXT dataReaderForTXT= new DataReaderForTXT();
	public ThreadTXT(String txt, List<List<String>> arrayTXT) {
		this.txt=txt;
		this.arrayTXT=arrayTXT;
		
	}
	@Override
	public void run() {
		try {
			System.out.println(Thread.currentThread().getName()+"이 시작되었습니다.");
			System.out.println("read "+txt);
			dataReaderForTXT.saveTXT(txt,arrayTXT);
			
		
		
		System.out.println(Thread.currentThread().getName()+"가 종료되었습니다");
}catch (Exception e) {}
}
}
