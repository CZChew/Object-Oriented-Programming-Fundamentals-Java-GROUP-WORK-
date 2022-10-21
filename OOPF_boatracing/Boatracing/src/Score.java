import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Score {  
	
	// To display the scores on screen/console
	public void displayScore() throws IOException {
		File scoreboard = new File("TopScore.txt");
		//Create text file 
		if (!scoreboard.exists()) {
			scoreboard.createNewFile();
		}
		
		// Read text file
		Scanner fileRead = new Scanner(new File("TopScore.txt"));
		String [] data = new String[2];
		ArrayList<String> winners = new ArrayList<>();
		ArrayList<Integer> highScores = new ArrayList<>();
		String winnerData, cacheWinnerData;
		int cacheScore;

		// Get every winner's data in text file
		while (fileRead.hasNextLine()){
			winnerData = fileRead.nextLine();
			data = winnerData.split(" ");
			winners.add(data[0]);
			highScores.add(Integer.parseInt(data[1]));
		}

		// Sort the winner's score in ascending order
		for (int x = 0; x < highScores.size(); x++){
			for (int y = x+1; y < highScores.size(); y++){
				if (highScores.get(x) > highScores.get(y)){
					cacheScore = highScores.get(x);
					highScores.set(x, highScores.get(y));
					highScores.set(y, cacheScore);

					cacheWinnerData = winners.get(x);
					winners.set(x, winners.get(y));
					winners.set(y, cacheWinnerData);
				}
			}
		}
		
		// Print out a list of winners' names and scores
		System.out.println("\nTop 5 Player Scores ");
        System.out.print("===========================\n");
		//Prints the scores that were sorted in ascending order
		if (highScores.size() == 0){
			System.out.println();
		}
		else{
			try{
				for (int i = 0; i < 5; i++){
					System.out.printf("%-10s|\t%-10d|\n",winners.get(i), highScores.get(i));
					System.out.println("===========================");
				}
			}
			catch (IndexOutOfBoundsException e){
			}
		}
	}
	
	// Add winners scores after a game ends
    public void addScore(String winnerName, int winnerScore) throws IOException {
    	
    	// Exit method if it's a tie game
    	if (winnerScore == 0) {
    		return;
    	}
		
		File scoreBoard = new File("TopScore.txt");
		
		if (!scoreBoard.exists()){
			scoreBoard.createNewFile();
		}
		Scanner fileRead = new Scanner(new File("TopScore.txt"));
		BufferedWriter writer = new BufferedWriter(new FileWriter(scoreBoard, true));


		String winner = (winnerName + " " + winnerScore);

		try{
			if (!fileRead.hasNextLine()){
				writer.write(winner);
				writer.close();
			}
			else{
				writer.newLine();
				writer.write(winner);
				writer.close();
			}
		}
		catch (IOException e){
			System.out.println("Error! Could not add score!");
		}
	}
	
}


