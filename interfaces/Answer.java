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
	public int AnswerID = -1;
	public int RelatedQuestionID = -1;
	public String AnswerTest = "";
	public boolean IsRight = false;
	
}
