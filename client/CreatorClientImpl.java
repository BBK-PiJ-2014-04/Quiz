package client;

import interfaces.CreateServer;
import interfaces.Question;
import interfaces.Quiz;
import interfaces.User;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import java.util.Scanner;

import server.QuestionImpl;

public class CreatorClientImpl implements CreatorClient {
	private CreateServer myQuizServer;
	private static Scanner scanner = new Scanner( System.in );
	private String input = "";
	private User actualUser;
	
	public CreatorClientImpl() {
			System.setProperty("java.security.policy","file:./server.policy");
			if (System.getSecurityManager() == null) {
				System.setSecurityManager(new SecurityManager());
			}
			try {
				String name = "QuizServer";
				Registry registry = LocateRegistry.getRegistry(1099);
				this.myQuizServer = (CreateServer) registry.lookup(name);
				actualUser = getCreator();		
			} catch (RemoteException | NotBoundException e) {
				e.printStackTrace();
			}
	}
	
	public static void main(String[] args) {
		CreatorClientImpl clientUser = new CreatorClientImpl();
		try {
			System.out.println("Hello! Welcome to the Quiz Creator System");
			System.out.println("You will be asked to 'login' in order to access your list of Quiz.");
			System.out.println("A list of Users/Quiz will be printed, with their ID specified on the left. To select them, simply type the number.");
			System.out.println("You can also use the keyword 'new' to create a new User/Quiz or the keyword 'exit' to quit the program");
			System.out.println("Once you select one Quiz, the Quiz can be closed or modified (Question/Answers)");
			System.out.println("Once you close one Quiz, the scores will be sent and no one will be able to play it anymore");
			System.out.println("Have fun!");
			while(true) {
				Quiz myQuiz = clientUser.getQuiz();
				System.out.println("Do you want to exit the program? (Any answer other than 'Y' or 'Yes', will be assumed as no");
				if(scanner.nextLine().equals("Y") || scanner.nextLine().equals("Yes")) {
					exitSystem();
				}
			}
		} catch (RemoteException e) {
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
				System.out.println("");
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
			String corrAnswer = scanner.nextLine();
			if(corrAnswer.equals("Y")) {
				question.addAnswer(answerText, true);
				break;
			}
			else if(corrAnswer.equals("N")) {
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
			String answerDone;
			do {
				answerDone = scanner.nextLine();
				if(!(answerDone.equals("Y") || answerDone.equals("N"))) {
					System.out.println("Please insert either 'Y' or 'N':");
				}
			} while(!(answerDone.equals("Y") || answerDone.equals("N")));
			if(answerDone.equals("Y")) {
				System.out.println("The Quiz has been successfully created");
				break;
			}
		}
		
	}
	
	/**
	 * Quits the execution of the program
	 */
	private static void exitSystem() {
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
		quizInitialSettings(newQuiz);
		int QuizID = newQuiz.getQuizID();
		while(true) {
			insertNewQuestion(QuizID);
			System.out.print("Is the Question inserting done? (Y/N):");
			while(true) {
				if(scanner.nextLine().equals("Y")) {
					System.out.println("The Quiz has been successfully created");
					return newQuiz;
				}
				else if(!scanner.nextLine().equals("N")) {
					System.out.println("Please insert either 'Y' or 'N':");
				}
			}
		}
	}

	/**
	 * Sets the initial message and the name of the Quiz (For both creation and modification)
	 * 
	 * @param newQuiz
	 * @throws RemoteException
	 */
	private void quizInitialSettings(Quiz newQuiz) throws RemoteException {
		String quizName;
		String quizInitialMessage;
		if(newQuiz.getQuizName() != "") {
			System.out.println("Current Quiz Name: " + newQuiz.getQuizName());
		}
		if(newQuiz.getInitialMessage() != "") {
			System.out.println("Current Quiz Message: " + newQuiz.getInitialMessage());
		}
		System.out.print("Please insert a Name for the Quiz:");
		quizName = scanner.nextLine();
		System.out.println("Thanks! Please insert now the message that will be displayed at the beginning of the Quiz for the players");
		quizInitialMessage = scanner.nextLine();
		newQuiz.setQuizName(quizName);
		newQuiz.setInitialMessage(quizInitialMessage);
	}

	@Override
	public void modifyQuiz(Quiz quiz) throws RemoteException {
		quizInitialSettings(quiz);
	}

}
