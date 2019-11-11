





package com.student.com.tanvir;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;


/**
 * @author tanvirhasan
 *
 * Handle client side, extended from ServerClientBaseClass
 */
public class Client extends ServerClientBaseClass{
	Socket socket;
	Client(){
		super(new Player("Player(client)"));
	}
	
	public  void connectToServer() throws UnknownHostException, IOException{	
		socket = new Socket(Configuration.getSharedInstance().getAddress(), Configuration.getSharedInstance().getPort());
		view.connectedMessage();
		
		setInputStream(new DataInputStream(socket.getInputStream())); // dependency Injection
		setOutputStream(new DataOutputStream(socket.getOutputStream())); // dependency Injection
		
		communicate(socket); //call super class method to communicate to the other end
		view.clientIsClosing();
		socket.close();
	}
	@Override
	protected void finalize() throws Throwable {
		if (!socket.isClosed()) socket.close(); /// to release port safely
	}
	
}
