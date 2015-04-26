package server;

import interfaces.Answer;

public class AnswerImpl implements Answer {
	public int AnswerID;
	public int RelatedQuestionID;
	public String AnswerTest;
	public boolean IsRight;
	
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
