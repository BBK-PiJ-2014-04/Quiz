package server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

import server.CustomTypes.Status;
import interfaces.Question;
import interfaces.Quiz;

public class QuizImpl extends UnicastRemoteObject implements Quiz {
	private int QuizID;
	private List<Question> questionsList;
	private String quizName;
	private String initialMessage;
	private int quizCreatorID;
	private Status quizStatus;
	
	public QuizImpl(int quizCreatorID, int quizID) throws RemoteException {
		this.quizCreatorID = quizCreatorID;
		this.quizStatus = Status.Opened;
		this.QuizID = quizID;
		this.questionsList = new ArrayList<Question>();
	}
	
	@Override
	public int getQuizID() throws RemoteException {
		return QuizID;
	}

	@Override
	public void addQuestion(Question newQuestion) throws RemoteException {
		questionsList.add(newQuestion);
	}

	@Override
	public boolean removeQuestion(int id) throws RemoteException {
		Question questionToRemove = getQuestion(id);
		if(questionToRemove != null) {
			questionsList.remove(questionToRemove);
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public List<Question> getQuestionList() throws RemoteException {
		return questionsList;
	}

	@Override
	public Question getQuestion(int id) throws RemoteException {
		for(int i=0; i < questionsList.size(); i++) {
			Question current = questionsList.get(i);
			if(current.getQuestionId() == id) {
				return current;
			}
		}
		return null;
	}

	@Override
	public String getQuizName() throws RemoteException {
		return quizName;
	}
	
	@Override
	public int getCreatorID() throws RemoteException {
		return quizCreatorID;
	}

	@Override
	public void setQuizName(String name) throws RemoteException {
		if(name != null && name != "") {
			this.quizName = name;
		}
		else {
			throw new IllegalArgumentException("Invalid name passed");
		}
	}

	@Override
	public void setInitialMessage(String text) throws RemoteException {
		if(text != null && text != "") {
			this.initialMessage = text;
		}
		else {
			throw new IllegalArgumentException("Invalid message passed");
		}

	}

	@Override
	public String getInitialMessage() throws RemoteException {
		return initialMessage;
	}
	
	@Override
	public Status getQuizStatus() throws RemoteException {
		return this.quizStatus;
	}

	@Override
	public void setQuizStatus(Status status) throws RemoteException {
		this.quizStatus = status;
	}
}
