package edu.handong.csee.java.chatcounter;

/**
 * WrongPathException Class inherits IllegalArgumnetExcetpion
 * If input of output path is wrong, It throws Exception. 
 * @author Farmboy
 *
 */
public class WrongPathException extends IllegalArgumentException {
	/**
	 * Constructor 
	 */
	public WrongPathException() {
		super();
	}
	/**
	 * Constructor (to use error message)
	 * @param message
	 */
	public WrongPathException(String message) {
		super(message);
	}
}
