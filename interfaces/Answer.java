/**
 * An Answer, that can either be right or wrong, to a question of the Quiz
 * 
 * It has an AnswerID, a RelatedQuestionID, a text and a boolean Right/Wrong
 */
package interfaces;

/**
 * @author SalvatoreCardali
 *
 */
public interface Answer {
	/**
	 * Gets the id of the Answer
	 * 
	 * @return ID
	 */
	public int getID();
	/**
	 * Get the text of the Answer
	 * 
	 * @return
	 */
	public String getText();
	/**
	 * Gets if this is the correct answer
	 * 
	 * @return
	 */
	public boolean isRight();
}
