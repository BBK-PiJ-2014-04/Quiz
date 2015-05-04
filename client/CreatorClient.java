package client;

import java.rmi.RemoteException;

import interfaces.Answer;
import interfaces.Question;
import interfaces.Quiz;
import interfaces.User;
/**
 * Interface for the 'graphic' interface of the Creator Client
 * 
 * @author SalvatoreCardali
 *
 */
public interface CreatorClient {
	
	/**
	 * Process to retrieve the user or to create a new one
	 * 
	 * @return the User that creates Quiz
	 * @throws RemoteException
	 */
	public User getCreator() throws RemoteException;
	/**
	 * Process to retrieve the quiz or to create a new one
	 * 
	 * @return the Quiz ID
	 * @throws RemoteException
	 */
	public Quiz getQuiz() throws RemoteException;
	/**
	 * Process to insert a new answer
	 * 
	 * @param questionID
	 * @param quizID
	 * @throws RemoteException
	 */
	public void insertNewAnswer(Question question) throws RemoteException;
	/**
	 * Process to insert a new Question
	 * 
	 * @param quizID
	 * @throws RemoteException
	 */
	public void insertNewQuestion(int quizID) throws RemoteException;
	/**
	 * Process to Modify the (Text, Message, questions, answers)
	 * 
	 * @param question
	 * @throws RemoteException 
	 */
	public void modifyQuiz(Quiz quiz) throws RemoteException;
	/**
	 * Process to modify a Question
	 * 
	 * @param quizID
	 * @throws RemoteException
	 */
	public void modifyQuestion(Question question, boolean skipFirstPart)
			throws RemoteException;
	/**
	 * Process to modify an Answer
	 * 
	 * @param question
	 * @param answer
	 */
	public void modifyAnswer(Answer answer, Question question);
	
	
}
