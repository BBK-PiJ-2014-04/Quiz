package interfaces;

import java.rmi.Remote;
import java.util.List;
/**
 * This interface will define the method that can be used by the Client who can create the quiz and set up the game.
 * 
 * @author SalvatoreCardal
 *
 */
public interface CreateServer extends Remote {
	public List<User> getListOfUsers();
	public User setUser(int id);
	public Quiz createQuiz();
	public 
}