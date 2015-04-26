package server;

import interfaces.CreateServer;
import interfaces.Player;
import interfaces.Question;
import interfaces.Quiz;
import interfaces.User;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import server.CustomTypes.Status;

public class QuizServer extends UnicastRemoteObject implements CreateServer {


	private static final long serialVersionUID = -6897311723118574615L;
	
	private List<User> usersList;
	private List<Quiz> quizList;
	private List<Results> resultsSheet;
	
	
	protected QuizServer() throws RemoteException {
		super();
	}

	@Override
	public List<User> getListOfUsers() throws RemoteException {
		return usersList;
	}

	@Override
	public int createUser(String name) throws RemoteException {
		int id = usersList.size();
		for(int i=0; i < usersList.size(); i++) {
		   User current = usersList.get(i);
		   if(current.getName().equals(name)) {
			   throw new IllegalArgumentException("There's already a user with that name");
		   }
		}
		usersList.add(new UserImpl(name,id));
		return id;
	}

	@Override
	public Quiz createQuiz(int userID) throws RemoteException {
		Quiz newQuiz = new QuizImpl(userID);
		quizList.add(newQuiz);
		return newQuiz;
	}

	@Override
	public List<Results> closeQuiz(int id) throws RemoteException {
		List<Results> finalResults = new ArrayList<Results>();
		for(int i=0; i < quizList.size(); i++) {
			Quiz current = quizList.get(i);
			if(current.getQuizID() == id) {
				current.setQuizStatus(Status.Completed);
			}
		}
		for(int i=0; i < resultsSheet.size(); i++) {
			Results current = resultsSheet.get(i);
			if(current.getQuizID() == id) {
				finalResults.add(current);
			}
		}
		return finalResults;
	}

	@Override
	public List<Quiz> getListOfQuiz(int userID) throws RemoteException {
		return quizList;
	}

}
