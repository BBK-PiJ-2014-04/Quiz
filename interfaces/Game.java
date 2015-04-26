package interfaces;

import server.CustomTypes.Status;

public interface Game {
	
	/**
	 * Gets the quiz chosen from the list on the initial screen.
	 * 
	 * @param id
	 * @return the chosen Quiz
	 */
	public Quiz getQuiz(int id);
	/**
	 * Gets the next question of the quiz
	 * 
	 * @return Question
	 */
	public Question getNextQuestion();
	/**
	 * Evaluates the answer and checks whether is correct or not and changes the score accordingly
	 * 
	 * @param QuestionId
	 * @return an int with the score for the question
	 */
	public int getScoreForQuestion(int QuestionId);
	/**
	 * Gets the generic score so far for the quiz
	 * 
	 * @return
	 */
	public int getScore();
	/**
	 * Gets the status of the quiz 
	 * 
	 * @return Status 
	 */
	public Status getStatus();
}
