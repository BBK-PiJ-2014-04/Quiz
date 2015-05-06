package client;

import interfaces.Quiz;

import java.rmi.RemoteException;

public class CreatorProgram {

	public static void main(String[] args) {
		CreatorClientImpl clientUser = new CreatorClientImpl();
		try {
			System.out.println("");
			System.out.println("Hello! Welcome to the Quiz Creator System");
			System.out.println("");
			System.out.println("A list of Quiz will be printed, with their ID specified on the left. To select them, simply type the number.");
			System.out.println("You can also use the keyword 'new' to create a new User/Quiz or the keyword 'exit' to quit the program");
			System.out.println("Please note that the keyword 'exit' can be used almost in any part of the program, even if not specified");
			System.out.println("");
			System.out.println("Once you select one Quiz, the Quiz can be closed or modified (Question/Answers)");
			System.out.println("Once you close one Quiz, the scores will be sent and no one will be able to play it anymore");
			System.out.println("");
			System.out.println("Have fun!");
			System.out.println("");
			while(true) {
				Quiz myQuiz = clientUser.getQuiz();
				clientUser.modifyQuiz(myQuiz);
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
