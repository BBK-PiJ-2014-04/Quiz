package server;

import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

import server.CustomTypes.Status;
import interfaces.Question;
import interfaces.Quiz;

public class QuizImpl implements Quiz {
	private int QuizID;
	private List<Question> questionsList;
	private String quizName;
	private String initialMessage;
	private int quizCreatorID;
	private Status quizStatus;
	
	public QuizImpl(int quizCreatorID) {
		this.quizCreatorID = quizCreatorID;
		this.quizStatus = Status.Opened;
	}
	
	@Override
	public int getQuizID() {
		return QuizID;
	}

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
	public String getQuizName() {
		return quizName;
	}
	
	@Override
	public int getCreatorID() {
		return quizCreatorID;
	}

	@Override
	public void setQuizName(String name) {
		if(name != null && name != "") {
			this.quizName = name;
		}
		else {
			throw new IllegalArgumentException("Invalid name passed");
		}
	}

	@Override
	public void setInitialMessage(String text) {
		if(text != null && text != "") {
			this.initialMessage = text;
		}
		else {
			throw new IllegalArgumentException("Invalid message passed");
		}

	}

	@Override
	public String getInitialMessage() {
		return initialMessage;
	}

	@Override
	public void setQuizStatus(Status status) {
		this.quizStatus = status;
	}
}
