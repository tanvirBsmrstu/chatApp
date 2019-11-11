package com.student.com.tanvir.main;

import com.student.com.tanvir.remoteCommunication.*;
import com.student.com.tanvir.util.*;

import java.io.IOException;
import java.net.UnknownHostException;

public class Main 
{

	public static void main(String[] args) throws UnknownHostException, IOException, InterruptedException, NumberFormatException
	{
		if (args.length == 0) {
			new View().wrongCommandLineArgs();
			return;
		}
		int mode = Integer.parseInt(args[0]); // checks commandLine argument to choose between two different solutions.

		switch(mode){
		case 5: 
			new View().solution5IsStarting();
			solution5();
			break;

		case 7: 
			configure(args);
			new View().solution7IsStarting();
			solution7(args);
			break;

		default: new View().wrongCommandLineArgs();
		}
	}

	private static void solution5() throws InterruptedException {
		Player p1 = new Player("Player 1 ");
		Player p2 = new Player("Player 2 ");

		p1.initiate(p2, Configuration.getSharedInstance().getFirstLetter()); // initiate messaging
		Thread t1 = new Thread(p1);
		Thread t2 = new Thread(p2);
		t1.start();
		t2.start();
		t1.join();
		t2.join();
	}
	private static void solution7(String[] args) throws UnknownHostException, IOException{ 
		if ((args.length < 2 ) /* validate commandLine args */
				|| !(args[1].equalsIgnoreCase(Configuration.getSharedInstance().getServerCommand()) || args[1].equalsIgnoreCase(Configuration.getSharedInstance().getClientCommand()))){

			new View().wrongCommandLineArgs();
			return;
		}
		if (args[1].equalsIgnoreCase(Configuration.getSharedInstance().getServerCommand())) startServer();
		if(args[1].equalsIgnoreCase(Configuration.getSharedInstance().getClientCommand())) startClient();
	}

	private static void startServer() throws IOException {
		Server server = new Server();
		server.serverStartListening();
	}
	private static void startClient() throws UnknownHostException, IOException {
		Client client = new Client();
		client.connectToServer();
	}

	/**
	 *  configure Configuration(singleTon file) from commandLine parameters
	 * @param args
	 */
	private static void configure(String[] args) {

		if (args.length >= 4) { /// mode(5 or 7),(server/client),(hostAddress),(Port) //last two parameters are optional
			try {
				Configuration.getSharedInstance().setPort(Integer.parseInt(args[3]));
				Configuration.getSharedInstance().setAddress(args[2]);
			}catch(NumberFormatException e) {
				View view = new View();
				view.wrongCommandLineArgs();
				view.displayLaunchingWithDefaultConfiguration();
				// if problem occurs regarding to integer parsing,the configuration file keep as it is
			}
		}
		// else keep as the configuration is
	}

}

