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
import java.util.Map;

public class QuizServer extends UnicastRemoteObject implements CreateServer {

	private List<User> usersList;
	private List<Quiz> quizList;
	HashMap<Player, Integer> usersScore
	
	
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Results> closeQuiz(int id) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Quiz> getListOfQuiz(int userID) throws RemoteException {
		return quizList;
	}

}
