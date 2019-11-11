package com.student.com.tanvir.util;

/**
 * @author tanvirhasan
 * 
 *	All the hard-coded values are in the Configuration class so that it becomes easy to modify.
 */
public class Configuration {

	private final String serverCommand;
	private final String clientCommand;
	private final String endCommand;

	private String firstLetter;	
	private String address;
	private int port;

	private static Configuration sharedInstance = new Configuration();

	private Configuration() {
		endCommand 		= "##@#^$";
		firstLetter 	= "Hello ";
		serverCommand 	= "server";
		clientCommand 	= "client";
		address 		= "localhost";
		port			= 3252;
	}

	public String getFirstLetter() {
		return firstLetter;
	}

	public void setFirstLetter(String firstLetter) {
		this.firstLetter = firstLetter;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public static Configuration getSharedInstance() {
		return sharedInstance;
	}

	public String getServerCommand() {
		return serverCommand;
	}

	public String getClientCommand() {
		return clientCommand;
	}

	public String getEndCommand() {
		return endCommand;
	}
}