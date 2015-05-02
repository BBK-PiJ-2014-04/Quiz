package client;

import interfaces.Answer;
import interfaces.User;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Scanner;

import server.QuizServer;

public class CreatorClientImpl implements CreatorClient {
	private QuizServer myQuizServer;
	private static Scanner scanner = new Scanner( System.in );
	private String input = "";
	
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
			return null;
		}
		else {
			System.out.println("Here's the List of Registered Users:");
		}
		for(User current : userList) {
			System.out.printf("%d ) %s", current.getId(),current.getName());
		}
		System.out.print("Please select your user (Write 'new' to create a new one): ");
		input = scanner.nextLine();
		if(input.equals("new")) {
			System.out.print("Please insert your username: ");
			input = scanner.nextLine();
			return myQuizServer.createUser(input);
		}
		else {
			try {
				return Integer.parseInt(input);
			}
			catch(Exception e) {
				System.out.println("Please insert either a number to select a user or the word 'new' to create a new user");
			}
			finally {
				getCreator();
			}
		}
	}

	@Override
	public int createNewQuiz() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void insertNewAnswer(int quizID) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insertNewQuestion(int quizID) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

}
