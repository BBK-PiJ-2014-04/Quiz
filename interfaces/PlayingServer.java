package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface PlayingServer extends Remote {
	
	/**
	 * Gets the quiz chosen from the list on the initial screen.
	 * 
	 * @param id
	 * @return the chosen Quiz
	 */
	public Quiz getQuiz(int id) throws RemoteException;
	/**
	 * Gets the list of all the Quiz created that the player hasn't played
	 * 
	 * @return List Of Quiz
	 * @throws RemoteException 
	 */
	public List<Quiz> getListOfQuiz(User player) throws RemoteException;
	/**
	 * Evaluates the answer and checks whether is correct or not and changes the score accordingly
	 * 
	 * @param Answer (the given answer)
	 * @return an int with the score for the question (1 if it's correct, 0 if it's wrong)
	 */
	public int getScoreForQuestion(Answer answer) throws RemoteException;
	/**
	 * Gets the generic score so far for the quiz
	 * 
	 * @param quiz
	 * @param user
	 * @return The score of the quiz thus far
	 * @throws RemoteException
	 */
	public int getScore(Quiz quiz,User user) throws RemoteException;
	/**
	 * Sets and update the generic score for the quiz
	 * 
	 * @param quiz
	 * @param user
	 * @param scoreUpdate
	 * @throws RemoteException
	 */
	public void updateScore(Quiz quiz,User user, int scoreUpdate) throws RemoteException;
	/**
	 * Get the list of users (simulation of a login system, without the password..trustful login :))
	 * 
	 * @return a List of all the registered user
	 * @throws RemoteException 
	 */
	public List<User> getListOfUsers() throws RemoteException;
	/**
	 * Retrieves the user with the given ID
	 * 
	 * @param userID
	 * @return the selected user
	 * @throws RemoteException
	 */
	public User getUser(int userID) throws RemoteException;
	/**
	 * Create a new user
	 * 
	 * @param name
	 * @return the id of the User created
	 * @throws RemoteException 
	 */
	public User createUser(String name) throws RemoteException;
	
}
