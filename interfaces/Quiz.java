package interfaces;

public interface Quiz {

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
	 */
	public void getQuestionList();
	/**
	 * Gets the question of the given id.
	 * 
	 * @param id
	 * @return The question whose id has been passed
	 */
	public Question getQuestion(int id);
	/**
	 * Gets the name of the Quiz
	 */
	public void getQuizName();
	/**
	 * Sets the name of the Quiz
	 */
	public void setQuizName();
	/**
	 * Sets an initial message to show when a player starts to play
	 * 
	 * @param Text
	 */
	public void setInitialMessage(String Text);
	/**
	 * Gets the initial message that has been set up
	 * 
	 * @return The initial message
	 */
	public String getInitialMessage();
}
