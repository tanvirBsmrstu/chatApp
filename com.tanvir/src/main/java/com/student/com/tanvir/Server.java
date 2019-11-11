package com.student.com.tanvir;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


/**
 * @author tanvirhasan
 *
 * Handle server related operations
 */
public class Server extends ServerClientBaseClass{
	ServerSocket sSocket;
	
	Server(){
		super(new Player("Player(server)"));
	}

	public void serverStartListening() throws IOException {

		 sSocket = new ServerSocket(Configuration.getSharedInstance().getPort());
		
		view.serverIsWaitingForConnection();
		Socket clientSocket = sSocket.accept(); //connecting to server 
		view.connectedMessage();
		
		setInputStream(new DataInputStream(clientSocket.getInputStream())); // dependency Injection
		setOutputStream( new DataOutputStream(clientSocket.getOutputStream())); // dependency Injection
		
		initiate(clientSocket);

		view.clientIsClosing();
		clientSocket.close();
		view.serverIsClosing();
		sSocket.close();	
	}


	/**
	 * start messaging with client socket
	 * @param client
	 * @throws IOException
	 */
	private void initiate(Socket client) throws IOException{
		player.sendLetter(Configuration.getSharedInstance().getFirstLetter(), outputStream); // sending first letter
		communicate(client); // method implemented in the base class, starts message transfer
	}

	@Override
	protected void finalize() throws Throwable {
		if (!sSocket.isClosed()) sSocket.close(); /// to release port safely
	}
	

}
