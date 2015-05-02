package client;

import interfaces.Answer;
import interfaces.User;

public interface CreatorClient {
	
	public User getCreator();
	public int createNewQuiz();
	public void insertNewAnswer(Answer myAnswer, int idQuiz);
	
}
