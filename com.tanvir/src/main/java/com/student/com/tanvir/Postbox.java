package com.student.com.tanvir;

import java.io.DataInputStream;
import java.io.DataOutputStream;


/**
 * @author tanvirhasan
 *Postbox class works like a real postbox
 *incoming letters will be stored here and outgoing letters will be send from here
 */
public class Postbox {
	
	private String letter; // works like an storage to store incoming letter
	RWOperationWithStream<String> rw ; //read,write operations related to streams
	
	Postbox(){
		letter = null;
		rw = new RWOperationWithStream<String>();
		
	}

	public void postLetter(String letter) {
		this.letter = letter;
	}
	/**
	 * Takes the letter out from the post box
	 * @return
	 */
	public String popLetter() {
		String msg = letter;
		letter = null;
		return msg;
	}
	
	/**
	 * An overloaded method, takes the letter out from the remote end
	 * @param in inputStream
	 * @return
	 */
	public String popLetter(DataInputStream in) {
		String msg = rw.readData(in);
		return msg;
	}
	
	/**
	 * checks whether the postBox is empty or there is a letter.
	 * @return
	 */
	public Boolean isLetterAvailable() {
		return !(letter == null);
	}
	
	/**
	 * it sends the letter to the receiver when both are not in the same java thread
	 * @param letter
	 * @param outputStream
	 */
	public void sendLetter(String letter, DataOutputStream out) {
		rw.writeData(out, letter);
	}
}
