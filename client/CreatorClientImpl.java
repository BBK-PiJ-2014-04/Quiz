package client;

import interfaces.Answer;
import interfaces.Quiz;
import interfaces.User;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Scanner;

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
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		
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
	public Quiz getQuiz() {
		List<Quiz> quizList = myQuizServer.getListOfQuiz(actualUser.getId());
		if(quizList.size() == 0) {
			System.out.println("There are no quiz for this user. Please create a new quiz.");
			
		}
		else {
			
		}
	}

	@Override
	public void insertNewAnswer(int quizID) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insertNewQuestion(int quizID) throws RemoteException {
		// TODO Auto-generated method stub
		
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
			
		}
	}

}
