package com.student.com.tanvir.remoteCommunication;

import com.student.com.tanvir.main.*;
import com.student.com.tanvir.util.*;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.io.IOException;

/**
 * @author tanvirhasan
 *  
 *  abstract base class for server and client
 *  thus no option to create an instance except inheritance
 */
public abstract class ServerClientBaseClass {
	protected DataInputStream inputStream;
	protected DataOutputStream outputStream;
	protected View view;
	protected Player player;

	protected ServerClientBaseClass(Player player){
		this.player     = player;
		view            = new View();
	}

	protected void setInputStream(DataInputStream in){ //will be used in dependency Injection
		inputStream = in;
	}

	protected void setOutputStream(DataOutputStream out){ //will be used in dependency Injection
		outputStream = out;
	}

	/**
	 * communication method between server and client
	 *  
	 * @param socket
	 * @throws IOException
	 */
	protected void communicate(Socket socket) throws IOException {  //communication method is the same for both(server and client), thus no need to implement twice.
		while (true) {
			String receivedletter = player.getPostBox().popLetter(inputStream);
			if (receivedletter.equals(Configuration.getSharedInstance().getEndCommand())) { // received ending signal
				if (!socket.isClosed()) // return an exiting signal if the socket is still open, so that both(server and client) sockets can be closed safely.
					player.sendLetter(Configuration.getSharedInstance().getEndCommand(), outputStream);
				break;
			}
			view.displayReceivedmessage(receivedletter, player);
			String letter = player.WriteLetter(receivedletter);
			player.sendLetter(letter, outputStream); // send reply
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				view.displayErrorMessage(e);
			}
		}

	}
}