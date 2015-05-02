package client;

import interfaces.Answer;
import interfaces.Question;
import interfaces.Quiz;
import interfaces.User;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Scanner;

import server.AnswerImpl;
import server.QuestionImpl;
import server.QuizImpl;
import server.QuizServer;

public class CreatorClientImpl implements CreatorClient {
	private QuizServer myQuizServer;
	private static Scanner scanner = new Scanner( System.in );
	private String input = "";
	private User actualUser;
	
	public CreatorClientImpl() {
		try {
			//System.out.println("tests");
			Remote service = Naming.lookup("//127.0.0.1:1099/QuizServer");
			myQuizServer = (QuizServer) service;
			actualUser = getCreator();
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		CreatorClientImpl clientUser = new CreatorClientImpl();
		try {
			Quiz myQuiz = clientUser.getQuiz();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public User getCreator() throws RemoteException{
		List<User> userList = myQuizServer.getListOfUsers();
		if(userList.size() == 0) {
			System.out.println("There are no users registered. Please create your user.");
			return createNewUser();
		}
		else {
			System.out.println("Here's the List of Registered Users:");
			for(User current : userList) {
				System.out.printf("%d ) %s", current.getId(),current.getName());
			}
			System.out.print("Please select your user (Write 'new' to create a new one): ");
			input = scanner.nextLine();
			if(input.equals("new")) {
				return createNewUser();
			}
			else if(input.equals("exit")) {
				exitSystem();
				return null;
			}
			else {
				try {
					return myQuizServer.getUser(Integer.parseInt(input));
				}
				catch(IllegalArgumentException e) {
					System.out.println("Please insert either a number to select a user, the word 'new' to create a new user, the word 'exit' to Quit");
					return getCreator();
				}
			}
		}
	}


	@Override
	public Quiz getQuiz() throws RemoteException {
		List<Quiz> quizList = myQuizServer.getListOfQuiz(actualUser.getId());
		if(quizList.size() == 0) {
			System.out.println("There are no quiz for this user. Please create a new quiz.");
			return createNewQuiz();
		}
		else {
			System.out.println("Here's the List of the Quiz you have previously created:");
			for(Quiz current : quizList) {
				System.out.printf("%d ) %s", current.getQuizID(),current.getQuizName());
			}
			System.out.print("Please select one of your Quiz (Write 'new' to create a new one): ");
			input = scanner.nextLine();
			if(input.equals("new")) {
				return createNewQuiz();
			}
			else if(input.equals("exit")) {
				exitSystem();
				return null;
			}
			else {
				try {
					return myQuizServer.getQuiz(Integer.parseInt(input));
				}
				catch(IllegalArgumentException e) {
					System.out.println("Please insert either a number to select a user, the word 'new' to create a new user, the word 'exit' to Quit");
					return getQuiz();
				}
			}
		}
	}

	@Override
	public void insertNewAnswer(Question question) {
		System.out.printf("Insert the Answer text for the Question '%s':", question.getQuestionText());
		String answerText = scanner.nextLine();
		System.out.printf("Is it the correct answer for the Question '%s' (Y/N):", question.getQuestionText());
		while(true) {
			if(scanner.nextLine().equals("Y")) {
				question.addAnswer(answerText, true);
				break;
			}
			else if(scanner.nextLine().equals("N")) {
				question.addAnswer(answerText, false);
				break;
			}
			else {
				System.out.println("Please insert either 'Y' or 'N'");
			}
		}
		System.out.println("Answer successfully inserted");
	}

	@Override
	public void insertNewQuestion(int quizID) throws RemoteException {
		Question nextQuestion = new QuestionImpl();
		System.out.println("Insert the Question text:");
		String questionText = scanner.nextLine();
		nextQuestion.setQuestionText(questionText);
		while(true) {
			insertNewAnswer(nextQuestion);
			System.out.print("Is the Answer inserting done? (Y/N)");
			while(true) {
				if(scanner.nextLine().equals("Y")) {
					System.out.println("The Quiz has been successfully created");
					break;
				}
				else if(!scanner.nextLine().equals("N")) {
					System.out.println("Please insert either 'Y' or 'N'");
				}
			}
		}
		
	}
	
	/**
	 * Quits the execution of the program
	 */
	private void exitSystem() {
		System.out.print("Thank you for using our system, farewell!");
		System.exit(0);
	}
	
	/**
	 * Creates a new user
	 * 
	 * @return the created User
	 * @throws RemoteException
	 */
	private User createNewUser() throws RemoteException {
		System.out.print("Please insert your username: ");
		input = scanner.nextLine();
		return myQuizServer.createUser(input);
	}
	
	/**
	 * Creates a new Quiz
	 * 
	 * @return the new Quiz
	 * @throws RemoteException
	 */
	private Quiz createNewQuiz() throws RemoteException {
		String quizName, quizInitialMessage;
		Quiz newQuiz = myQuizServer.createQuiz(actualUser.getId());
		System.out.print("Please insert a new Name for the Quiz:");
		quizName = scanner.nextLine();
		System.out.println("Thanks! Please insert now the message that will be displayed at the beginning of the Quiz for the players");
		quizInitialMessage = scanner.nextLine();
		newQuiz.setQuizName(quizName);
		newQuiz.setInitialMessage(quizInitialMessage);
		int QuizID = newQuiz.getQuizID();
		while(true) {
			insertNewQuestion(QuizID);
			System.out.print("Is the Question inserting done? (Y/N)");
			while(true) {
				if(scanner.nextLine().equals("Y")) {
					System.out.println("The Quiz has been successfully created");
					return newQuiz;
				}
				else if(!scanner.nextLine().equals("N")) {
					System.out.println("Please insert either 'Y' or 'N'");
				}
			}
		}
	}

}
