package edu.handong.csee.java.chatcounter;

import org.apache.commons.cli.CommandLineParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

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
	}

	/**
	 * run method controls i, o, h options to take arguments and guide for Users
	 * @param args
	 * @throws ParseException
	 * @throws IOException
	 * @throws java.text.ParseException
	 */
	public void run(String[] args) throws ParseException, IOException, java.text.ParseException {
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

			DataReaderForCSV dataReaderForCSV = new DataReaderForCSV();
			DataReaderForTXT dataReaderForTXT = new DataReaderForTXT();
			ChatMessageCounter chatMessageCounter = new ChatMessageCounter();
			MessageFilter messageFilter = new MessageFilter();
			List<List<String>> saveCSVPath=dataReaderForCSV.saveCSV(path);
			List<List<String>> saveTXTPath=dataReaderForTXT.saveTXT(path);
			if(saveCSVPath.isEmpty() || saveTXTPath.isEmpty()) {
				System.out.println("NO FILES(csv & txt) PLEASE CHECK AND RETRY!\n");
				throw new FileNotFoundException("There are no CSV and TXT Files in Input Path\n"); 
			}
			List<List<String>> sumData=messageFilter.sumCSVTXT(path,saveCSVPath,saveTXTPath);		
			DataWriter dataWriter =new DataWriter();
			dataWriter.convertToCSV(chatMessageCounter.countChat(path,sumData),savePath);
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
