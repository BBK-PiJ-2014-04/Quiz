package server;

import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

import interfaces.Answer;
import interfaces.Question;
import interfaces.Quiz;

public class QuizImpl implements Quiz {
	private List<Question> questionsList;
	private String quizName;

	@Override
	public void addQuestion(Question newQuestion) {
		questionsList.add(newQuestion);
	}

	@Override
	public boolean removeQuestion(int id) {
		Question questionToRemove = getQuestion(id);
		if(questionToRemove != null) {
			questionsList.remove(questionToRemove);
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public Dictionary<Integer, String> getQuestionList() {
		Dictionary<Integer,String> questionsDictionary = new Hashtable<Integer, String>();
		for(int i=0; i < questionsList.size(); i++) {
			Question current = questionsList.get(i);
			questionsDictionary.put(current.getQuestionId(), current.getQuestionText());
		}
		return questionsDictionary;
	}

	@Override
	public Question getQuestion(int id) {
		for(int i=0; i < questionsList.size(); i++) {
			Question current = questionsList.get(i);
			if(current.getQuestionId() == id) {
				return current;
			}
		}
		return null;
	}

	@Override
	public void getQuizName() {
		// TODO Auto-generated method stub

	}

	@Override
	public void setQuizName() {
		// TODO Auto-generated method stub

	}

	@Override
	public void setInitialMessage(String Text) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getInitialMessage() {
		// TODO Auto-generated method stub
		return null;
	}

}
