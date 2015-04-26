package server;

import java.util.List;

import interfaces.Answer;
import interfaces.Question;

public class QuestionImpl implements Question {
	private int questionID;
	private String text;
	private List<Answer> answersList;
	
	@Override
	public void setQuestionText(String text) {
		this.text = text;
	}

	@Override
	public String getQuestionText() {
		return text;
	}

	@Override
	public void addAnswer(String text, boolean correct) {
		answersList.add(new AnswerImpl(answersList.size(),text,this.questionID,correct));
	}

	@Override
	public void modAnswer(int answerID, String text, boolean correct) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delAnswer(int answerID) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean answersFormat() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<String> getAnswers() {
		// TODO Auto-generated method stub
		return null;
	}

}
