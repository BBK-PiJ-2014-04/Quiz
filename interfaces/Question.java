/**
 * 
 */
package interfaces;

import java.util.List;

/**
 * @author SalvatoreCardali
 *
 */
public interface Question {
	public int QuestionID = -1;
	public String Text = "";
	
	public void setQuestionText(String text);
	public String getQuestionText();
	public int addAnswer(String text, boolean correct);
	public int modAnswer(int answerID, String text, boolean correct);
	public int delAnswer(int answerID);
	public boolean AnswerFormat();
	public List<String> getAnswers();
	
	

}
