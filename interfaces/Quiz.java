package interfaces;

public interface Quiz {

	public void addQuestion(Question newQuestion);
	public void removeQuestion(int id);
	public void getQuizCreator();
	public void getQuestionList();
	public void setInitialMessage(String Text);
	public void getInitialMessage();
}
