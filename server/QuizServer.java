package server;

import interfaces.CreateServer;
import interfaces.Player;
import interfaces.Quiz;
import interfaces.User;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.List;

public class QuizServer extends UnicastRemoteObject implements CreateServer {

	private List<User> usersList;
	private List<Quiz> quizList;
	
	
	protected QuizServer() throws RemoteException {
		super();
	}

	@Override
	public List<User> getListOfUsers() throws RemoteException {
		return usersList;
	}

	@Override
	public void createUser(String name) throws RemoteException {
		for(int i=0; i < usersList.size(); i++) {
		   User current = usersList.get(i);
		   if(current.getName().equals(name)) {
			   throw new IllegalArgumentException("There's already a user with that name");
		   }
		}
	}

	@Override
	public Quiz createQuiz(int userID) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HashMap<Player, Integer> closeQuiz(int id) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Quiz> getListOfQuiz(int userID) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

}
