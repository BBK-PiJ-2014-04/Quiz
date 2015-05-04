package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Dictionary;
import java.util.List;

import server.CustomTypes.Status;

public interface Quiz extends Remote {
	/**
	 * Gets the id of the Quiz
	 * 
	 * @return ID
	 */
	public int getQuizID() throws RemoteException;
	/**
	 * Add a question to the list
	 * 
	 * @param newQuestion
	 */
	public void addQuestion(Question newQuestion) throws RemoteException;
	/**
	 * Remove a question from the list by giving the question ID.
	 * 
	 * @param id
	 * @return true if the question has been successfully removed, false if it couldn't been found
	 */
	public boolean removeQuestion(int id) throws RemoteException;
	/**
	 * Gets all the questions that have been previously added to the Quiz List
	 * @return list of all the question
	 */
	public List<Question> getQuestionList() throws RemoteException;
	/**
	 * Gets the question of the given id.
	 * 
	 * @param id
	 * @return The question whose id has been passed
	 */
	public Question getQuestion(int id) throws RemoteException;
	/**
	 * Gets the name of the Quiz
	 * 
	 * @return String
	 */
	public String getQuizName() throws RemoteException;
	/**
	 * Sets the name of the Quiz
	 * @param name
	 */
	public void setQuizName(String name) throws RemoteException;
	/**
	 * Sets an initial message to show when a player starts to play
	 * 
	 * @param Text
	 */
	public void setInitialMessage(String text) throws RemoteException;
	/**
	 * Gets the initial message that has been set up
	 * 
	 * @return The initial message
	 */
	public String getInitialMessage() throws RemoteException;
	/**
	 * Gets the id of the User who created the Quiz
	 * 
	 * @return
	 */
	public int getCreatorID() throws RemoteException;
	/**
	 * Sets the Quiz Status
	 * 
	 * @param status 
	 */
	public void setQuizStatus(Status status) throws RemoteException;
	/**
	 * Gets the Quiz Status
	 * 
	 * @return
	 */
	public Status getQuizStatus() throws RemoteException;
	
}
