package com.student.com.tanvir.util;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * @author tanvirhasan
 *
 * Generic API for sending and receiving data from/to a remote end 
 * @param <T> is the type of the data that has to be send to the other end
 * 
 */
public class RWOperationWithStream<T> {

	public  String readData(DataInputStream input) {
		String msg = "";
		try {
			msg = input.readUTF();

		} catch (IOException e) {
			new View().displayErrorMessage(e);
		}finally {
			return msg;
		}
	}
	public  void writeData(DataOutputStream output, T msg) {
		try {
			output.writeUTF(msg.toString());
			output.flush();

		} catch (IOException e) {
			new View().displayErrorMessage(e);
		}

	}

}
