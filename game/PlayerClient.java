package game;

import java.rmi.RemoteException;
import java.util.List;

import interfaces.Quiz;
import interfaces.User;

/**
 * Interface for the 'graphic' interface of the Playing Client
 * 
 * @author SalvatoreCardali
 *
 */
public interface PlayerClient {
	
	/**
	 * Process to get the playing user
	 * 
	 * @return the user
	 * @throws RemoteException 
	 */
	public User getPlayer() throws RemoteException;
	/**
	 * Process to obtain a Quiz the player hasn't played yet and haven't been closed yet
	 * 
	 * @param player
	 * @return Quiz
	 */
	public Quiz getNotPlayedQuiz(User player);
	/**
	 * Process to show and answer the question, storing the score
	 * 
	 * @param quiz
	 * @return
	 */
	public int answeringQuestion(Quiz quiz);
	
}
