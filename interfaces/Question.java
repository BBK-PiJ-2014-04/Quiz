/**
 * A Question which can have multiple answer related. Every question has a text and an ID. 
 */
package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * @author SalvatoreCardali
 *
 */
public interface Question extends Remote {
	
	/**
	 * Getting the id of the question
	 * 
	 * @return id
	 */
	public int getQuestionId() throws RemoteException;
	/**
	 * Setting the text of the question,  what the user is going to see
	 * 
	 * @param text
	 */
	public void setQuestionText(String text) throws RemoteException;
	
	/**
	 * Getting the text of the question
	 * 
	 * @return a String containing the text of the question
	 */
	public String getQuestionText() throws RemoteException;
	
	/**
	 * Adding an answer with its text and whether is the correct answer or not
	 * 
	 * @param text
	 * @param correct
	 * @return the AnswerID
	 */
	public void addAnswer(String text, boolean correct) throws RemoteException;
	/**
	 * Editing an answer with its text and whether is the correct answer or not
	 * 
	 * @param answerID
	 * @param text
	 * @param correct
	 */
	public void modAnswer(int answerID, String text, boolean correct) throws RemoteException;
	/**
	 * Deleting an answer from the list
	 * 
	 * @param text
	 * @param correct
	 */
	public void delAnswer(int answerID) throws RemoteException;
	/**
	 * Checking whether there're at least 2 answer for the question and at least one is set as correct
	 * 
	 * @return Whether or not the Question has an acceptable format
	 */
	public boolean checkAnswerFormat() throws RemoteException;
	/**
	 * Getting a list of all the Answers previously inserted
	 * 
	 * @return List of Answers
	 */
	public List<Answer> getAnswers() throws RemoteException;
	/**
	 * Get a single answer from the Answers List
	 * 
	 * @param id
	 * @return the answer with the passed id
	 */
	Answer getAnswerFromList(int id) throws RemoteException;
	
}
