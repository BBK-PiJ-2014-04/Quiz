package game;

import interfaces.Answer;
import interfaces.CreateServer;
import interfaces.Player;
import interfaces.PlayingServer;
import interfaces.Question;
import interfaces.Quiz;
import interfaces.User;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import java.util.Scanner;

import server.CustomTypes;

public class PlayerClientImpl implements PlayerClient {
	
	private PlayingServer myQuizServer;
	public Scanner scanner = new Scanner( System.in );
	public User actualPlayer;
	
	public PlayerClientImpl() {
		System.setProperty("java.security.policy","file:./server.policy");
		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}
		try {
			String name = "QuizServer";
			Registry registry = LocateRegistry.getRegistry(1099);
			this.myQuizServer = (PlayingServer) registry.lookup(name);
			actualPlayer = getPlayer();		
		} catch (RemoteException | NotBoundException e) {
			e.printStackTrace();
		}
	}
	
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
	public int answeringQuestion(Quiz quiz, Player player) throws RemoteException {
		String input;
		int finalscore = 0;
		for(Question current : quiz.getQuestionList()) {
			System.out.println(current.getQuestionText());
			int index = 0;
			boolean[] answersGrid = new boolean[current.getAnswers().size()];
			for(Answer answer : current.getAnswers()) {
				answersGrid[index++] = answer.isRight();
				System.out.printf("%d) %s",index,answer.getText());
			}
			System.out.print("Please select your answer: ");
			while(true) {
				input = scanner.nextLine();
				if(input.equals("exit")) {
					exitSystem();
					return 0;
				}
				else {
					if(CustomTypes.isInteger(input,10)) {
						if(Integer.parseInt(input) <= answersGrid.length) {
							myQuizServer.updateScore(quiz, player, ((answersGrid[Integer.parseInt(input)]) ? 1 : 0));
							finalscore+=((answersGrid[Integer.parseInt(input)]) ? 1 : 0);
							if(answersGrid[Integer.parseInt(input)]) {
								System.out.println("Nice job! Your answer is correct! Let's keep on rolling with the next question");
							}
							else {
								System.out.println("Bugger! Your answer is not correct! Let's try to improve with the following question");
							}
							break;
						}
						else {
							System.out.println("Invalid output, please try again. Select your answer: ");
						}
					}
					else {
						System.out.println("Invalid output, please try again. Select your answer: ");
					}
				}
			}
			System.out.println("");
		}
		return finalscore;
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
