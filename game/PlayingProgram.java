package game;

import interfaces.Quiz;

import java.rmi.RemoteException;

public class PlayingProgram {

	public static void main(String[] args) {
		PlayerClientImpl clientUser = new PlayerClientImpl();
		int finalscore = 0;
		try {
			System.out.println("");
			System.out.println("Hello! Welcome to the Quiz Playing System");
			System.out.println("");
			System.out.println("A list of Quiz will be printed, with their ID specified on the left. To select them, simply type the number.");
			System.out.println("Please note that the keyword 'exit' can be used almost in any part of the program, even if not specified");
			System.out.println("If you quit a Quiz before finishing it, your progresses and your score will be saved to the point you've reached.");
			System.out.println("");
			System.out.println("Once you select one Quiz, the Quiz will start and the questions will be printed with all the possible answers");
			System.out.println("Have fun!");
			System.out.println("");
			while(true) {
				Quiz myQuiz = clientUser.getNotPlayedQuiz(clientUser.actualPlayer);
				finalscore = clientUser.answeringQuestion(myQuiz, clientUser.actualPlayer);
				System.out.printf("Your score for the quiz %s was %d. Hope you had fun!",myQuiz.getQuizName(),finalscore);
				System.out.println("");
				System.out.println("Do you want to exit the program? (Any answer other than 'Y' or 'Yes', will be assumed as no):");
				String toScanExit = clientUser.scanner.nextLine().toUpperCase();
				if(toScanExit.equals("Y") || toScanExit.equals("YES")) {
					clientUser.exitSystem();
				}
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

}
