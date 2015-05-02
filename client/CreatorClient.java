package client;

import java.rmi.RemoteException;

import interfaces.Answer;
import interfaces.User;
/**
 * Interface for the 'graphic' interface of the Creator Client
 * 
 * @author SalvatoreCardali
 *
 */
public interface CreatorClient {
	
	/**
	 * Process to retrieve the user or to create a new one
	 * 
	 * @return the User that creates Quiz
	 * @throws RemoteException
	 */
	public User getCreator() throws RemoteException;
	/**
	 * Process to retrieve the quiz or to create a new one
	 * 
	 * @return the Quiz ID
	 * @throws RemoteException
	 */
	public Quiz getQuiz() throws RemoteException;
	/**
	 * Process to insert a new answer
	 * 
	 * @param quizId
	 * @throws RemoteException
	 */
	public void insertNewAnswer(int quizID) throws RemoteException;
	/**
	 * Process to insert a new Question
	 * 
	 * @param quizID
	 * @throws RemoteException
	 */
	public void insertNewQuestion(int quizID) throws RemoteException;
	
}
