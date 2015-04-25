package interfaces;

import game.CustomTypes.Status;

public interface Game {
	
	/**
	 * Gets the quiz chosen from the list on the initial screen.
	 * 
	 * @param id
	 * @return the chosen Quiz
	 */
	public Quiz getQuiz(int id);
	/**
	 * 
	 * 
	 * @return
	 */
	public Question getNextQuestion();
	public int getScoreForQuestion(int QuestionId);
	public int getScore();
	public Status getStatus();
}
