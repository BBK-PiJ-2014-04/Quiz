package server;

import interfaces.Answer;

public class AnswerImpl implements Answer {
	private int answerID;
	private int relatedQuestionID;
	private String answerText;
	private boolean isRight;
	
	public AnswerImpl(int answerID,String text, int questionID, boolean isRight) {
		this.answerID = answerID;
		this.relatedQuestionID = questionID;
		this.isRight = isRight;
	}
	
	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public String getText() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean isRight() {
		// TODO Auto-generated method stub
		return false;
	}
}
