/**
 * 
 */
package interfaces;

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
	
	
	

}
