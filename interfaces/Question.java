/**
 * A Question which can have multiple answer related. Every question has a text and an ID. 
 */
package interfaces;

import java.util.Dictionary;
import java.util.List;
import java.util.Map;

/**
 * @author SalvatoreCardali
 *
 */
public interface Question {
	
	/**
	 * Setting the text of the question,  what the user is going to see
	 * 
	 * @param text
	 */
	public void setQuestionText(String text);
	
	/**
	 * Getting the text of the question
	 * 
	 * @return a String containing the text of the question
	 */
	public String getQuestionText();
	
	/**
	 * Adding an answer with its text and whether is the correct answer or not
	 * 
	 * @param text
	 * @param correct
	 * @return the AnswerID
	 */
	public void addAnswer(String text, boolean correct);
	/**
	 * Editing an answer with its text and whether is the correct answer or not
	 * 
	 * @param answerID
	 * @param text
	 * @param correct
	 */
	public void modAnswer(int answerID, String text, boolean correct);
	/**
	 * Deleting an answer from the list
	 * 
	 * @param text
	 * @param correct
	 */
	public void delAnswer(int answerID);
	/**
	 * Checking whether there're at least 2 answer for the question and at least one is set as correct
	 * 
	 * @return Whether or not the Question has an acceptable format
	 */
	public boolean checkAnswerFormat();
	/**
	 * Getting a list of all the Answers previously inserted
	 * 
	 * @return List of Answers
	 */
	public Dictionary getAnswers();
	
}
