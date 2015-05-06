package client;

import interfaces.Answer;
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

import server.CustomTypes;
import server.CustomTypes.Status;
import server.QuestionImpl;

public class CreatorClientImpl implements CreatorClient {
	private CreateServer myQuizServer;
	public Scanner scanner = new Scanner( System.in );
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
			input = scanner.nextLine().toLowerCase();
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
				if(current.getQuizStatus().equals(Status.Opened)) {
					System.out.printf("%d ) %s", current.getQuizID(),current.getQuizName());
					System.out.println("");
				}
			}
			System.out.print("Please select one of your Quiz (Write 'new' to create a new one): ");
			input = scanner.nextLine().toLowerCase();
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
					System.out.println("Please insert either a number to select a Quiz, the word 'new' to create a new user, the word 'exit' to Quit");
					return getQuiz();
				}
			}
		}
	}

	@Override
	public void insertNewAnswer(Question question) throws RemoteException {
		System.out.printf("Insert the Answer text for the Question '%s':", question.getQuestionText());
		String answerText = scanner.nextLine();
		System.out.printf("Is it the correct answer for the Question '%s' (Y/N):", question.getQuestionText());
		while(true) {
			String corrAnswer = scanner.nextLine().toUpperCase();
			if(corrAnswer.equals("Y")) {
				question.addAnswer(answerText, true);
				break;
			}
			else if(corrAnswer.equals("N")) {
				question.addAnswer(answerText, false);
				break;
			}
			else {
				System.out.println("Please insert either 'Y' or 'N':");
			}
		}
		System.out.println("Answer successfully inserted");
	}
	
	@Override
	public void modifyAnswer(Answer answer, Question question) throws RemoteException {
		System.out.printf("Insert the new Answer text for the Question '%s':", question.getQuestionText());
		String answerText = scanner.nextLine();
		boolean isCorrect = false;
		System.out.printf("After the change, is it the correct answer for the Question '%s' (Y/N):", question.getQuestionText());
		while(true) {
			String corrAnswer = scanner.nextLine().toUpperCase();
			if(corrAnswer.equals("Y")) {
				isCorrect = true;
				break;
			}
			else if(corrAnswer.equals("N")) {
				isCorrect = false;
				break;
			}
			else {
				System.out.println("Please insert either 'Y' or 'N':");
			}
		}
		answer.changeText(answerText);
		answer.changeTrueValue(isCorrect);
		System.out.println("Answer successfully modified");
	}

	@Override
	public Question insertNewQuestion() throws RemoteException {
		Question nextQuestion = new QuestionImpl();
		System.out.print("Insert the Question text:");
		String questionText = scanner.nextLine();
		nextQuestion.setQuestionText(questionText);
		while(true) {
			insertNewAnswer(nextQuestion);
			System.out.print("Is the Answer inserting done? (Y/N):");
			String answerDone;
			do {
				answerDone = scanner.nextLine().toUpperCase();
				if(!(answerDone.equals("Y") || answerDone.equals("N"))) {
					System.out.print("Please insert either 'Y' or 'N':");
				}
			} while(!(answerDone.equals("Y") || answerDone.equals("N")));
			if(answerDone.equals("Y")) {
				System.out.println("The Question has been successfully inserted");
				return nextQuestion;
			}
		}
		
	}
	
	/**
	 * Quits the execution of the program
	 */
	public void exitSystem() {
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
	 * Creates a new Quiz
	 * 
	 * @return the new Quiz
	 * @throws RemoteException
	 */
	private Quiz createNewQuiz() throws RemoteException {
		Quiz newQuiz = myQuizServer.createQuiz(actualUser.getId());
		quizInitialSettings(newQuiz);
		while(true) {
			newQuiz.addQuestion(insertNewQuestion());
			System.out.print("Is the Question inserting done? (Y/N):");
			String questionDone;
			do {
				questionDone = scanner.nextLine().toUpperCase();
				if(!(questionDone.equals("Y") || questionDone.equals("N"))) {
					System.out.println("Please insert either 'Y' or 'N':");
				}
			} while(!(questionDone.equals("Y") || questionDone.equals("N")));
			if(questionDone.equals("Y")) {
				System.out.println("The Quiz has been successfully inserted");
				return newQuiz;
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
		if(newQuiz.getQuizName() != null) {
			System.out.println("Current Quiz Name: " + newQuiz.getQuizName());
		}
		if(newQuiz.getInitialMessage() != null) {
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
		modifyQuizGuideLines();
		String input = scanner.nextLine().toLowerCase();
		if(input.equals("mod quiz")) {
			quizInitialSettings(quiz);
		}
		else if(input.equals("info")) {
			modifyQuiz(quiz);
		}
		else if(input.equals("main menu")) {
			return;
		}
		else if(input.equals("close")) {
			quiz.setQuizStatus(Status.Completed);
		}
		else if(input.equals("mod questions")) {
			for(Question current: quiz.getQuestionList()) {
				System.out.printf("Do you want to modify the question: %s? (Any answer other than 'Y' or 'Yes', will be assumed as no):", current.getQuestionText());
				String questionModify = scanner.nextLine().toUpperCase();
				if(questionModify.equals("YES") || questionModify.equals("Y")) {
					modifyQuestion(current, false);
				}
			}
		}
		else {
			System.out.println("Please read the guidelines carefully, and try again!");
			modifyQuiz(quiz);
		}
		
	}


	@Override
	public void modifyQuestion(Question question, boolean skipFirstPart) throws RemoteException {
		if(!skipFirstPart) {
			System.out.println("Modify the Question text (leave it empty to skip):");
			String questionText = scanner.nextLine();
			if(!questionText.equals("")) {
				question.setQuestionText(questionText);
			}
		}
		modifyGuideLines();
		System.out.println("Here's the list of Answers for the question: " + question.getQuestionText());
		for (Answer current : question.getAnswers() ) {
			System.out.printf("%d ) %s %s", current.getID(),current.getText(),(current.isRight()) ? "correct" : "wrong");
			System.out.println("");
		}
		System.out.println("Use the id as your parameter n to perform the operation (f.i. del 0 will delete the answer with id 0)");
		System.out.println("");
		System.out.println("Please insert your operation:");
		input = scanner.nextLine().toLowerCase();
		if(input.equals("new")) {
			insertNewAnswer(question);
		}
		else if(input.equals("info")) {
			modifyQuestion(question, true);
		}
		else if(input.equals("main menu")) {
			return;
		}
		else if(input.startsWith("mod ") && CustomTypes.isInteger(input.split(" ")[1],10)) {		
			modifyAnswer(question.getAnswerFromList(Integer.parseInt(input.split(" ")[1])), question);
		}
		else if(input.startsWith("del ") && CustomTypes.isInteger(input.split(" ")[1],10)) {	
			question.delAnswer(Integer.parseInt(input.split(" ")[1]));
			System.out.println("Answer succesfully Deleted");
		}
		else {
			System.out.println("Please read the guidelines carefully, and try again!");
			modifyQuestion(question, true);
		}
	}
	/**
	 * Prints the guidelines to modify the Quiz
	 */
	private void modifyQuizGuideLines() {
		System.out.println("");
		System.out.println("You now have five possible operations: close, mod quiz, mod questions, info, main menu");
		System.out.println("The formats of the operations are the following:");
		System.out.println("* close, will close the quiz and make the scores available");
		System.out.println("* mod quiz, will allow you to modify the Quiz name and message");
		System.out.println("* mod questions, will allow you to change questions and answers");
		System.out.println("* info, will show these guidelines again");
		System.out.println("* main menu, will go back to the main menu (list of Quiz)");
		System.out.println("Please insert your operation:");
	}
	/**
	 * Prints the guidelines to modify Questions and answers
	 */
	private void modifyGuideLines() {
		System.out.println("");
		System.out.println("You now have four possible operations: del, new, mod, info, main menu");
		System.out.println("The formats of the operations are the following:");
		System.out.println("* del n, where n is the id of the answer, will delete the answer");
		System.out.println("* new, will allow you to add answers to the question");
		System.out.println("* mod n, where n is the id of the answer, will allow you to modify the answer");
		System.out.println("* info, will show these guidelines again");
		System.out.println("* main menu, will go back to the main menu (list of Quiz)");
		System.out.println("");
	}
	
}
