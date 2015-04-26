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
		if(text != null && text != "") {
			this.answerText = text;
		}
		else {
			throw new IllegalArgumentException("The text of an answer can't be null or empty");
		}
	}
	
	@Override
	public int getQuestionID() {
		return relatedQuestionID;
	}
	
	@Override
	public int getID() {
		return answerID;
	}
	@Override
	public String getText() {
		return answerText;
	}
	@Override
	public boolean isRight() {
		return isRight;
	}

	@Override
	public void changeText(String text) {
		if(text != null && text != "") {
			this.answerText = text;
		}
		else {
			throw new IllegalArgumentException("The text of an answer can't be null or empty");
		}
	}

	@Override
	public void changeTrueValue(boolean trueValue) {
		this.isRight = trueValue;
	}
}
