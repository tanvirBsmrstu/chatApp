package com.student.com.tanvir;




/**
 * @author tanvirhasan
 * Display related all methods are in the view
 */
public class View {
	
	public void display(String message) {
		System.out.println(message);
	}
	public void displayErrorMessage(Exception e) {
		System.out.println(e.toString());
	}
	
	public void serverIsClosing() {
		System.out.println("Server is closing");
	}
	
	public void clientIsClosing() {
		System.out.println("Client is closing");
	}
	
	public void serverIsWaitingForConnection() {
		System.out.println("Waiting for connection.....");
	}
	
	public void connectedMessage() {
		System.out.println("Connected");
	}
	
	public void solution5IsStarting() {
		System.out.println("solution 5 is starting");
	}
	public void solution7IsStarting() {
		System.out.println("solution 7 is starting");
	}
	
	public void wrongCommandLineArgs() {
		System.out.println("wrong commandLine argments");
	}
	public void displayReceivedmessage(String msg, Player p) {
		String s = "received by " + p.getName();
		System.out.println(String.format("%-28s<< %s" , s, msg ));
	}
	public void displaySentMessage(String msg,Player p) {
		String s = "sent by " + p.getName();
		System.out.println(String.format("%-28s>> %s" , s, msg ));
	}
	public void displayLaunchingWithDefaultConfiguration() {
		System.out.println("Launching with default configuration..");
	}

}
