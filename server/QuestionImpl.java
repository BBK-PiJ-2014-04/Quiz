package server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import interfaces.Answer;
import interfaces.Question;

public class QuestionImpl extends UnicastRemoteObject implements Question {
	private int questionID;
	private String text;
	private List<Answer> answersList;
	
	public QuestionImpl() throws RemoteException {
		this.answersList = new ArrayList<Answer>();
	}
	
	@Override
	public int getQuestionId() throws RemoteException {
		return questionID;
	}
	
	@Override
	public void setQuestionText(String text) throws RemoteException {
		this.text = text;
	}

	@Override
	public String getQuestionText() throws RemoteException {
		return text;
	}

	@Override
	public void addAnswer(String text, boolean correct) throws RemoteException {
		answersList.add(new AnswerImpl(answersList.size(),text,this.questionID,correct));
	}

	@Override
	public void modAnswer(int answerID, String text, boolean correct) throws RemoteException {
		Answer answerToModify = this.getAnswerFromList(answerID);
		answerToModify.changeText(text);
		answerToModify.changeTrueValue(correct);
	}
	
	@Override
	public boolean checkAnswerFormat() throws RemoteException {
		boolean hasOnlyOneTrue = false;
		if(answersList.size() < 2) {
			return false;
		}
		else {
			for(int i=0; i < answersList.size(); i++) {
				Answer current = answersList.get(i);
				if(current.isRight()) {
					if(!hasOnlyOneTrue) {
						hasOnlyOneTrue = true;
					}
					else {
						return false;
					}
				}
			}
		}
		return hasOnlyOneTrue;
	}


	@Override
	public void delAnswer(int answerID) throws RemoteException {
		Answer answerToRemove = this.getAnswerFromList(answerID);
		answersList.remove(answerToRemove);
		
	}

	@Override
	public List<Answer> getAnswers() throws RemoteException {
		return this.answersList;
	}
	
	@Override
	public Answer getAnswerFromList(int id) throws RemoteException {
		for(int i=0; i < answersList.size(); i++) {
			Answer current = answersList.get(i);
			if(current.getID() == id) {
				return current;
			}
		}
		throw new IllegalArgumentException("Invalid ID passed");
	}

}
