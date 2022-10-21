import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Scanner;

public class BoatRace { // Main Game
	
	private static String winnerName;
	private static int winnerScore;
	private static Game game;
	private static Score score = new Score();
	
	public static void main(String[] args) throws IOException {
		
		Scanner input = new Scanner(System.in);
		System.out.print("/$$$$$$$                       /$$           /$$$$$$$                      /$$                    \r\n"
				+ "| $$__  $$                     | $$          | $$__  $$                    |__/                    \r\n"
				+ "| $$  \\ $$  /$$$$$$  /$$$$$$  /$$$$$$        | $$  \\ $$  /$$$$$$   /$$$$$$$ /$$ /$$$$$$$   /$$$$$$ \r\n"
				+ "| $$$$$$$  /$$__  $$|____  $$|_  $$_/        | $$$$$$$/ |____  $$ /$$_____/| $$| $$__  $$ /$$__  $$\r\n"
				+ "| $$__  $$| $$  \\ $$ /$$$$$$$  | $$          | $$__  $$  /$$$$$$$| $$      | $$| $$  \\ $$| $$  \\ $$\r\n"
				+ "| $$  \\ $$| $$  | $$/$$__  $$  | $$ /$$      | $$  \\ $$ /$$__  $$| $$      | $$| $$  | $$| $$  | $$\r\n"
				+ "| $$$$$$$/|  $$$$$$/  $$$$$$$  |  $$$$/      | $$  | $$|  $$$$$$$|  $$$$$$$| $$| $$  | $$|  $$$$$$$\r\n"
				+ "|_______/  \\______/ \\_______/   \\___/        |__/  |__/ \\_______/ \\_______/|__/|__/  |__/ \\____  $$\r\n"
				+ "                                                                                          /$$  \\ $$\r\n"
				+ "                                                                                         |  $$$$$$/\r\n"
				+ "                                                                                          \\______/ ");
		System.out.println("\n    __|__ |___| |\\\r\n"
				+ "    |o__| |___| | \\\r\n"
				+ "    |___| |___| |o \\\r\n"
				+ "   _|___| |___| |__o\\\r\n"
				+ "  /...\\_____|___|____\\_/\r\n"
				+ "  \\   o * o * * o o  /\r\n"
				+ "~~~~~~~~~~~~~~~~~~~~~~~~~~\n\n");
		
		// Main menu
		System.out.println("Press Enter to Start Game!");
		System.out.println("Press Any to Shut Down!");
		System.out.print("Command: ");
		String start = input.nextLine();
		
		// Start Game menu
		if(start.equals("")) {
			startGame();
		}
		else {
			System.out.println("\n\nGame End.");
		}
	}
	
	// Check name is numeric
	public static boolean isNumeric(String str) {
		int n;
		
		try {
			n = Integer.parseInt(str);
			return true;
		} catch (NumberFormatException e) {
		}
		return false;
	}
	
	// Select Game mode (Death or Friendly)
	public static void selectGameMode(String name1, String name2) {
		Scanner input = new Scanner(System.in);
		boolean repeat = true;
		while (repeat) {
			try {
				System.out.println("Enter 1 for Death");
				System.out.println("Enter 2 for Friendly");
				System.out.print("Command: ");
				int gm = input.nextInt();
				if (gm == 1) {
					repeat = false;
					game = new Game(gm, name1, name2);
					break;
				}
				if (gm == 2) {
					repeat = false;
					game = new Game(gm, name1, name2);
					break;
				}
				else {
					System.out.println("\nIncorrect Command!");
					System.out.println("Please enter the right command !\n");
				}
			} catch (InputMismatchException me) {
				System.out.println("\nIncorrect command!");
				System.out.println("Please try again!\n");
				input.next();
			}
		}
		
		
	}
	
	// Enter name for player 1 and player 2
	public static String enterName(int playerNum) {
		String name = "";
		Scanner input = new Scanner(System.in);
		while (name.equals("")) {
			System.out.printf("\nEnter Player %d Name: ", playerNum);
			name = input.nextLine();
			while (isNumeric(name)) {
				System.out.println("\nPlayer name cannot be an integer!");
				System.out.printf("\nEnter Player %d Name: ", playerNum);
				name = input.nextLine();
			}
			if (name.equals("")) {
				System.out.printf("Please Enter Player %d Name!\n", playerNum);
			}
		}
		String firstname = name.split(" ")[0];
		return firstname;
	}
	
	// New game
	public static void newGame() throws IOException {
		Scanner input = new Scanner(System.in);
		boolean repeat = true;
		
		while (repeat) {
			try {
				System.out.println("New Game or Quit (1/2)");
				System.out.print("Command: ");
				int op = input.nextInt();
				
				if (op == 1) {
					repeat = false;
					System.out.println("\n\n========================================");
					System.out.println("                NEW GAME");
					System.out.println("========================================\n");
					startGame();
				}
				else if (op == 2) {
					System.out.println("\n\nThank you for Playing!");
					System.out.println("Game End\n\n");
					repeat = false;
					System.exit(0);
				}
				else {
					System.out.println("\nIncorrect Command !\n");
					System.out.println("Please enter the right command !\n");
				}
			} catch (InputMismatchException e) {
				System.out.println("\nIncorrect command!");
				System.out.println("Please try again!\n");
				input.next();
			}
		}
	}
	
	// Start game
	public static void startGame() throws IOException {
		score.displayScore();  
		String name1 = enterName(1);
		String name2 = enterName(2);
		System.out.print("\n\n");
		
		selectGameMode(name1, name2); 
		winnerScore = game.getWinnerScore();
		winnerName = game.getWinnerName();
		score.addScore(winnerName, winnerScore);
		newGame();
	}	
	

}
