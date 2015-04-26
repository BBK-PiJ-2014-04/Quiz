package server;

import java.util.Dictionary;
import java.util.Hashtable;
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
		Answer answerToModify = this.getAnswerFromList(answerID);
	}
	
	@Override
	public boolean checkAnswerFormat() {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public void delAnswer(int answerID) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Dictionary<Integer, String> getAnswers() {
		Dictionary<Integer,String> answersDictionary = new Hashtable<Integer, String>();
		for(int i=0; i < answersList.size(); i++) {
			Answer current = answersList.get(i);
			answersDictionary.put(current.getID(), current.getText());
		}
		return answersDictionary;
	}
	
	public Answer getAnswerFromList(int id) {
		for(int i=0; i < answersList.size(); i++) {
			Answer current = answersList.get(i);
			if(current.getID() == id) {
				return current;
			}
		}
		throw new IllegalArgumentException("Invalid ID passed");
	}
}
