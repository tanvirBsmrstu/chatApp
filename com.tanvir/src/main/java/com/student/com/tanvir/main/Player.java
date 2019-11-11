package com.student.com.tanvir.main;

import com.student.com.tanvir.util.*;

import java.io.DataOutputStream;

/**
 * @author tanvirhasan
 *
 */
public class Player implements Runnable{

	/**
	 * postbox is responsible for message sending,receiving and reading operations
	 */
	private  Postbox postBox;

	/**
	 * view is responsible for display related operations 
	 */
	private View view;
	private int delivaryCounter;
	private int maxSendLimit ;
	private Player receiver;
	private String name;

	public Player(String name){
		postBox = new Postbox();
		view = new View();
		this.name = name;
		delivaryCounter = 0;
		maxSendLimit 	= 10;
	}


	/**
	 *transferring messages in the same java process but different thread
	 */
	public void run() {
		while(true) {
			if (postBox.isLetterAvailable()) {
				String receivedLetter = postBox.popLetter();
				if (receivedLetter.equals(Configuration.getSharedInstance().getEndCommand())) {
					sendLetter(Configuration.getSharedInstance().getEndCommand());
					break;
				}
				view.displayReceivedmessage(receivedLetter,this);
				String replyLetter = getReplyLetter(receivedLetter);
				sendLetter(replyLetter);
			}
			try {
				Thread.sleep(500); 
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				view.displayErrorMessage(e);
			}
		}
		view.display("closing "+name);

	}

	public String WriteLetter(String content) {
		return getReplyLetter(content);
	}

	/**
	 * construct the reply message
	 * @param receivedLetter
	 * @return constructed the reply message
	 */
	private String getReplyLetter(String receivedLetter) {
		return receivedLetter + delivaryCounter;
	}

	/**
	 * 
	 * send a letter to the receiver end, and an exiting signal when the message sending limit is crossed.
	 * @param letter
	 */
	private void sendLetter(String letter) {
		if(isLimitExceed()) letter = Configuration.getSharedInstance().getEndCommand();
		else view.displaySentMessage(letter, this);
		receiver.getPostBox().postLetter(letter);
		delivaryCounter++;
	}

	/**
	 * 
	 * an overloaded method, send a letter through the output stream when both(sender and receiver) are not in the same Java process
	 * @param letter
	 * @param out
	 */
	public void sendLetter(String letter, DataOutputStream out) {
		if(isLimitExceed()) letter = Configuration.getSharedInstance().getEndCommand();
		else view.displaySentMessage(letter, this);
		getPostBox().sendLetter(letter, out);;
		delivaryCounter++;
	}

	/**
	 * link up both objects(sender and receiver) and send the first message
	 * @param receiver
	 * @param message
	 */
	public void initiate(Player receiver, String message) {
		this.setReceiver(receiver);
		receiver.setReceiver(this);
		sendLetter(message); // sending first message
	}

	private boolean isLimitExceed() {
		return (delivaryCounter >= maxSendLimit);
	}

	public void setReceiver(Player p) {
		this.receiver = p;
	}
	public Postbox getPostBox() {
		return postBox;
	}
	public String getName(){
		return name;
	}
}
