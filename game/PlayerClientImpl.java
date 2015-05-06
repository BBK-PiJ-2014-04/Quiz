package game;

import interfaces.Player;
import interfaces.PlayingServer;
import interfaces.Quiz;
import interfaces.User;

import java.rmi.RemoteException;
import java.util.List;
import java.util.Scanner;

import server.CustomTypes.Status;

public class PlayerClientImpl implements PlayerClient {
	
	private PlayingServer myQuizServer;
	public Scanner scanner = new Scanner( System.in );
	
	@Override
	public User getPlayer() throws RemoteException {
		List<User> userList = myQuizServer.getListOfUsers();
		String input;
		if(userList.size() == 0) {
			System.out.println("There are no users registered. Please create your user.");
			return createNewUser();
		}
		else {
			System.out.println("Here's the List of Registered Users:");
			for(User current : userList) {
				System.out.printf("%d ) %s", current.getId(),current.getName());
				System.out.println("");
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
					return getPlayer();
				}
			}
		}
	}

	@Override
	public Quiz getNotPlayedQuiz(User player) throws RemoteException {
		String input;
		List<Quiz> quizList = myQuizServer.getListOfQuiz((Player)player);
		if(quizList.size() == 0) {
			System.out.println("There are no quiz for you! Email the Quiz creator for him to create new amazing Quiz!");
			exitSystem();
			return null;
		}
		else {
			System.out.println("Here's the List of the Quiz you can play:");
			for(Quiz current : quizList) {
					System.out.printf("%d ) %s", current.getQuizID(),current.getQuizName());
					System.out.println("");
			}
			System.out.print("Please select one: ");
			input = scanner.nextLine();
			if(input.equals("exit")) {
				exitSystem();
				return null;
			}
			else {
				try {
					return myQuizServer.getQuiz(Integer.parseInt(input));
				}
				catch(IllegalArgumentException e) {
					System.out.println("Please insert either a number to select a quiz or the word 'exit' to Quit"); //TODO change it on the Creator Class too
					return getNotPlayedQuiz(player);
				}
			}
		}
	}

	@Override
	public int answeringQuestion(Quiz quiz) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	/**
	 * Creates a new user
	 * 
	 * @return the created User
	 * @throws RemoteException
	 */
	private User createNewUser() throws RemoteException {
		String input;
		while(true) {
			System.out.print("Please insert your username: ");
			input = scanner.nextLine();
			if(input != "") { 
				User createdUser = myQuizServer.createUser(input);
				if(createdUser != null) {
					return createdUser;
				}
			}
			System.out.println("The inserted username is not valid (either in use or empty)");
			System.out.println("");
		}
	}
	
	/**
	 * Quits the execution of the program
	 */
	public void exitSystem() {
		System.out.print("Thank you for using our system, farewell!");
		System.exit(0);
	}

}
