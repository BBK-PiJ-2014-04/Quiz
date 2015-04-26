package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.List;
/**
 * This interface will define the method that can be used by the Client who can create the quiz and set up the game.
 * 
 * @author SalvatoreCardali
 *
 */
public interface CreateServer extends Remote {
	/**
	 * Get the list of users (simulation of a login system, without the password..trustful login :))
	 * 
	 * @return a List of all the registered user
	 * @throws RemoteException 
	 */
	public List<User> getListOfUsers() throws RemoteException;
	/**
	 * Create a new user
	 * 
	 * @param name
	 * @return a boolean to define if the operation was successful
	 * @throws RemoteException 
	 */
	public void createUser(String name) throws RemoteException;
	/**
	 * Creation of a new Quiz
	 * 
	 * @param userID
	 * @return Created Quiz
	 * @throws RemoteException
	 */
	public Quiz createQuiz(int userID) throws RemoteException;
	/**
	 * Gets the list of Quiz created
	 * 
	 * @param userID
	 * @return List Of Quiz
	 * @throws RemoteException 
	 */
	public List<Quiz> getListOfQuiz(int userID) throws RemoteException;
	/**
	 * Changes the status of the quiz to Completed and gives back a Grid with the players that attempted it and their respective results
	 * 
	 * @param id
	 * @return A Map with the Players and the scores
	 * @throws RemoteException 
	 */
	public HashMap<Player,Integer> closeQuiz(int id) throws RemoteException;
	
	
}
