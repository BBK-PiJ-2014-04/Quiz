package server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import interfaces.Answer;

public class AnswerImpl extends UnicastRemoteObject implements Answer {
	private int answerID;
	private int relatedQuestionID;
	private String answerText;
	private boolean isRight;
	
	public AnswerImpl(int answerID,String text, int questionID, boolean isRight) throws RemoteException {
		this.answerID = answerID;
		this.relatedQuestionID = questionID;
		this.isRight = isRight;
		if(text != null && text != "") {
			this.answerText = text;
		}
		else {
			throw new IllegalArgumentException("The text of an answer can't be null or empty");
		}
	}
	
	@Override
	public int getQuestionID() throws RemoteException {
		return relatedQuestionID;
	}
	
	@Override
	public int getID() throws RemoteException {
		return answerID;
	}
	@Override
	public String getText() throws RemoteException {
		return answerText;
	}
	@Override
	public boolean isRight() throws RemoteException {
		return isRight;
	}

	@Override
	public void changeText(String text) throws RemoteException {
		if(text != null && text != "") {
			this.answerText = text;
		}
		else {
			throw new IllegalArgumentException("The text of an answer can't be null or empty");
		}
	}

	@Override
	public void changeTrueValue(boolean trueValue) throws RemoteException {
		this.isRight = trueValue;
	}
}
