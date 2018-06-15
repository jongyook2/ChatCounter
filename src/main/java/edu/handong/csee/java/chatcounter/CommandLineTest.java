package edu.handong.csee.java.chatcounter;

import org.apache.commons.cli.CommandLineParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;


/**
 * CommandLineTest Class contains main method, run method, printHelp method
 * It takes arguments and return argument to method
 * @author Farmboy
 *
 */
public class CommandLineTest {
	String path;
	String savePath;
	String threadNum;
	boolean help;
	/**
	 * Main method runs CommandLineTest's run method.
	 * @param args
	 * @throws ParseException
	 * @throws IOException
	 * @throws java.text.ParseException
	 */
	public static void main(String[] args) throws ParseException, IOException, java.text.ParseException {
		CommandLineTest cml = new CommandLineTest();
		cml.run(args);
		System.out.println("main end.");
	}

	/**
	 * run method controls i, o, h options to take arguments and guide for Users
	 * @param args
	 * @throws ParseException
	 * @throws IOException
	 * @throws java.text.ParseException
	 */
	public void run(String[] args) throws ParseException, IOException, java.text.ParseException {
		//ArrayList<Thread> tTXT = new ArrayList<Thread>();
		ArrayList <String> listCSVPath =new ArrayList <String>();
		List<List<String>> arrayCSV = new ArrayList<List<String>>();
		ArrayList <String> listTXTPath =new ArrayList <String>();
		List<List<String>> arrayTXT = new ArrayList<List<String>>();
		
		Options options = new Options();
		options.addOption(Option.builder("i").longOpt("path")
				.desc("Set a path of a directory(ChatData)")
				.hasArg()
				.argName("Path name to input")
				.required()
				.build());

		options.addOption(Option.builder("o").longOpt("savePath")
				.desc("Set a path of a directory and filename(.format)")
				.hasArg()   
				.argName("Path name to output")
				.required() 
				.build());		
		
		options.addOption(Option.builder("c").longOpt("threadNumber")
				.desc("Set thread numbers")
				.hasArg()   
				.argName("thread numbers")
				.required() 
				.build());		

		options.addOption(Option.builder("h").longOpt("help")
				.desc("Help")
				.build());
		CommandLineParser parser =new DefaultParser();

		try {
			CommandLine cmd = parser.parse(options, args);

			//-i C:\Users\Farmboy\Desktop\HW\java\Chat-Java -o C:\Users\Farmboy\Desktop\HW\java\final.CSV
			path=cmd.getOptionValue("i");
			File d=new File(path); 
			if(!d.isDirectory()) {
				System.out.println("Wrong Input Path, Please check and retry\n");
				throw new WrongPathException("Input Path Is Wrong\n");
			}
			savePath=cmd.getOptionValue("o");
			d=new File(savePath);
			if(!d.getParentFile().isDirectory()) {
				System.out.println("Wrong Onput Path, Please check and retry\n");
				throw new WrongPathException("Onput Path Is Wrong\n");
			}

			help=cmd.hasOption("h");
			threadNum=cmd.getOptionValue("c");
			int num=Integer.parseInt(threadNum);
			ExecutorService executorService = Executors.newFixedThreadPool(num);
		
			DataReader dataReader = new DataReader();
			listCSVPath= dataReader.readCSV(path);
			
			for(String csv:listCSVPath) {
				Thread tCSV = new Thread(new ThreadCSV(csv,arrayCSV));
				//Runnable tCSV = new ThreadCSV(csv,arrayCSV);
				executorService.execute(tCSV);
				//Future future = executorService.submit(tCSV);
//				try {
//					future.get();
//					System.out.println("작업처리완료");
//				} catch(InterruptedException e) {
//					e.printStackTrace();
//				} catch (ExecutionException e) {
//					e.printStackTrace();
//				}
				//tCSV.join();
				//tCSV.sleep(100);
				//tCSV.
			}
			
			listTXTPath= dataReader.readTXT(path);
			for(String txt:listTXTPath) {
				Thread tTXT = new Thread(new ThreadTXT(txt,arrayTXT));
				//Runnable tTXT = new Runnable(ThreadTXT(txt,arrayTXT));
				executorService.execute(tTXT);
				//tTXT.join();
				//tTXT.sleep(100);
				//System.out.println(txt);
//				Future future = executorService.submit(tTXT);
//				try {
//					future.get();
//					System.out.println("작업처리완료");
//				} catch(InterruptedException e) {
//					e.printStackTrace();
//				} catch (ExecutionException e) {
//					e.printStackTrace();
//				}
			}
				executorService.shutdown();
			  while(!executorService.isTerminated()) {
				  //System.out.println("check" + ++i);
			  }
			
			ChatMessageCounter chatMessageCounter = new ChatMessageCounter();
			MessageFilter messageFilter = new MessageFilter();
			
			if(arrayCSV.isEmpty() && arrayTXT.isEmpty()) {
				System.out.println("NO FILES(csv & txt) PLEASE CHECK AND RETRY!\n");
				throw new FileNotFoundException("There are no CSV and TXT Files in Input Path\n"); 
			}
			
			List<List<String>> sumData=messageFilter.sumCSVTXT(arrayCSV, arrayTXT);		
			DataWriter dataWriter =new DataWriter();
			dataWriter.convertToCSV(chatMessageCounter.countChat(sumData),savePath);
			
			
			
			
			
			
			
//			DataReaderForCSV dataReaderForCSV = new DataReaderForCSV();
//			DataReaderForTXT dataReaderForTXT = new DataReaderForTXT();
//			
			
//			List<List<String>> saveCSVPath=dataReaderForCSV.saveCSV(path);
//			List<List<String>> saveTXTPath=dataReaderForTXT.saveTXT(path);
//			
//			
					
			//  for(int i=0; i<10; i++) {
		       //     Thread t = new Thread(new ThreadCSV(path,savePath));
		           // t.start();
		          
		     //       executorService.execute(t);
		            
		       //     threads.add(t);
			  //}
//			  for(int i=0; i<threads.size(); i++) {
//		            Thread t = threads.get(i);
//		            try {
//		                t.join();
//		            }catch(Exception e) {
//		            }
//		        }
			 
			  
		
			
		        System.out.println("Files is created at "+savePath);
		       
			
		}catch (Exception e) {
			System.out.println("ErroMessage: "+e.getMessage());
			printHelp(options);
		}		
	}			
	/**
	 * printHelp method helps how to use this program 
	 * @param options
	 */
	private void printHelp(Options options) {

		HelpFormatter formatter = new HelpFormatter();
		String header = "ChatCount Program";
		String footer ="\nPlease report issues at https://github.com/jongyook2/ChatCount";
		formatter.printHelp("-i your chatData path -o savePath -h help\n", header, options, footer, true);
	}
}
