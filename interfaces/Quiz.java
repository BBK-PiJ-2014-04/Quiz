package interfaces;

import java.util.Dictionary;

import server.CustomTypes.Status;

public interface Quiz {
	/**
	 * Gets the id of the Quiz
	 * 
	 * @return ID
	 */
	public int getQuizID();
	/**
	 * Add a question to the list
	 * 
	 * @param newQuestion
	 */
	public void addQuestion(Question newQuestion);
	/**
	 * Remove a question from the list by giving the question ID.
	 * 
	 * @param id
	 * @return true if the question has been successfully removed, false if it couldn't been found
	 */
	public boolean removeQuestion(int id);
	/**
	 * Gets all the questions that have been previously added to the Quiz List
	 * @return 
	 */
	public Dictionary<?, ?> getQuestionList();
	/**
	 * Gets the question of the given id.
	 * 
	 * @param id
	 * @return The question whose id has been passed
	 */
	public Question getQuestion(int id);
	/**
	 * Gets the name of the Quiz
	 * 
	 * @return String
	 */
	public String getQuizName();
	/**
	 * Sets the name of the Quiz
	 * @param name
	 */
	public void setQuizName(String name);
	/**
	 * Sets an initial message to show when a player starts to play
	 * 
	 * @param Text
	 */
	public void setInitialMessage(String text);
	/**
	 * Gets the initial message that has been set up
	 * 
	 * @return The initial message
	 */
	public String getInitialMessage();
	/**
	 * Gets the id of the User who created the Quiz
	 * 
	 * @return
	 */
	public int getCreatorID();
	/**
	 * Sets the Quiz Status
	 * 
	 * @param status 
	 */
	public void setQuizStatus(Status status);
	/**
	 * Gets the Quiz Status
	 * 
	 * @return
	 */
	public Status getQuizStatus();
	
}
