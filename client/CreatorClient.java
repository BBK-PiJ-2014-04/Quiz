package client;

import java.rmi.RemoteException;

import interfaces.Answer;
import interfaces.User;

public interface CreatorClient {
	
	public User getCreator() throws RemoteException;
	public int createNewQuiz() throws RemoteException;
	public void insertNewAnswer(Answer myAnswer, int idQuiz) throws RemoteException;
	
}
