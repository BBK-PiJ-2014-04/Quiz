package interfaces;

import java.rmi.Remote;
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
	 */
	public List<User> getListOfUsers();
	/**
	 * Create a new user
	 * 
	 * @param name
	 * @return a boolean to define if the operation was successful
	 */
	public boolean createUser(String name);
	/**
	 * Select the active user
	 * 
	 * @param id
	 * @return
	 */
	public User setUser(int id);
	/**
	 * Creation of a new Quiz
	 * 
	 * @return the Quiz object created
	 */
	public Quiz createQuiz();
	/**
	 * Gets the list of Quiz created
	 * 
	 * @return List Of Quiz
	 */
	public List<Quiz> getListOfQuiz();
	/**
	 * Changes the status of the quiz to Completed and gives back a Grid with the players that attempted it and their respective results
	 * 
	 * @param id
	 * @return A Map with the Players and the scores
	 */
	public HashMap<Player,Integer> closeQuiz(int id);
	
	
}
