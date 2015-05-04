/**
 * An Answer, that can either be right or wrong, to a question of the Quiz
 * 
 * It has an AnswerID, a RelatedQuestionID, a text and a boolean Right/Wrong
 */
package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author SalvatoreCardali
 *
 */
public interface Answer extends Remote {
	/**
	 * Gets the id of the Answer
	 * 
	 * @return ID
	 * @throws RemoteException 
	 */
	public int getID() throws RemoteException;
	/**
	 * Gets the id of the Question which the answer is related to
	 * 
	 * @return ID
	 * @throws RemoteException 
	 */
	public int getQuestionID() throws RemoteException;
	/**
	 * Get the text of the Answer
	 * 
	 * @return
	 * @throws RemoteException 
	 */
	public String getText() throws RemoteException;
	/**
	 * Change the text of the Answer
	 * @throws RemoteException 
	 */
	public void changeText(String text) throws RemoteException;
	/**
	 * Change the true value of the answer(whether is the right or wrong answer)
	 * @throws RemoteException 
	 */
	public void changeTrueValue(boolean trueValue) throws RemoteException;
	/**
	 * Gets if this is the correct answer
	 * 
	 * @return
	 * @throws RemoteException 
	 */
	public boolean isRight() throws RemoteException;
}
