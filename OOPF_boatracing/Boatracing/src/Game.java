import java.util.Scanner;
import java.util.Random;

public class Game { 
	Scanner input = new Scanner(System.in);
	private River r;
	private River r1;
	private River r2;
	private Boat b1;
	private Boat b2;
	private Current c;
	private Current c1;
	private Current c2;
	private Trap t;
	private Trap t1;
	private Trap t2;
	private int winnerScore;
	private String winnerName;
	private boolean gameEnd = false;
	
	// Constructor
	public Game(int gm, String p1, String p2) {
		// Friendly Game mode
		if (gm == 2) {
			b1 = new Boat(p1);
			b2 = new Boat(p2);
			c1 = new Current(2);
			c2 = new Current(2);
			t1 = new Trap(2);
			t2 = new Trap(2);
			r1 = new River(b1, c1, t1);
			r2 = new River(b2, c2, t2);
			
			System.out.print(r1);
			System.out.print(r2);
			
			// Total 5 rounds; 1 round = 20 columns; 100 columns
			while ((r1.getRow() < 7) && (r2.getRow() < 7)) {
				
				checkWinner(2);
				
				if (gameEnd) {
					return; // Exit method after game end
				}
				
				// Player's control
				playerMove(2, b1.getName());
				checkCurrentHit(2); // Double checking current and trap
				checkTrapHit(2);
				riverBoundary(2);
				playerMove(2, b2.getName());
				checkCurrentHit(2);
				checkTrapHit(2);
				riverBoundary(2);
				
				// Set boat's position after player's control
				b1.setBoatPosition(b1.getPieceTrack());
				b2.setBoatPosition(b2.getPieceTrack());
				
				// Update the display
				r1.updateRiver(2, r1.getColumn(), r1.getRiverCheck(), r1.getRiverTrack(), b1.getSymbol(), b2.getSymbol(), b1.getTrack(), b2.getTrack());
				r2.updateRiver(2, r2.getColumn(), r2.getRiverCheck(), r2.getRiverTrack(), b2.getSymbol(), b1.getSymbol(), b2.getTrack(), b1.getTrack());
				
				// Print display
				System.out.print(r1);
				System.out.print(r2);	
			}
		}
		// Death Game mode
		else if (gm == 1) {
			b1 = new Boat(p1, "P1", 0, 1);
			b2 = new Boat(p2, "P2", 2, 2);
			b2.setBoatPosition(2);
			c = new Current(1);
			t = new Trap(1);
			r = new River(b1, b2, c, t);
			
			System.out.print(r.deathtoString());
			
			while (b1.getPieceTrack() < 102 || b2.getPieceTrack() < 102) {
				
				checkWinner(1);
				
				if (gameEnd) {
					return; // Exit method after game end
				}
				
				playerMove(1, b1.getName());
				attackPlayer(1);
				riverBoundary(1);
				checkCurrentHit(1); // Double checking current and trap
				checkTrapHit(1);
				playerMove(1, b2.getName());
				attackPlayer(2);
				riverBoundary(1);
				checkCurrentHit(1);
				checkTrapHit(1);
				
				b1.setBoatPosition(b1.getPieceTrack());
				b2.setBoatPosition(b2.getPieceTrack());
				
				r.updateRiver(1, r.getColumn(), r.getRiverCheck(), r.getRiverTrack(), b1.getSymbol(), b2.getSymbol(), b1.getTrack(), b2.getTrack());
				
				System.out.print(r.deathtoString());
			}

		}
		
	}
	
	// Rolling dice
	public int rollDice() {
		Random r = new Random();
		int dice = r.nextInt(6)+1;
		return dice; 
	}
	
	// Deciding on winner
	public void checkWinner(int gm) {
		if (gm == 2) {
			if ((r1.getRow() == 5) && (r2.getRow() == 5)) {
				if ((b2.getPieceTrack() >= 20) && (b2.getPieceTrack() >= 20)) {
					System.out.print("\n\nIts a Tie!\n\n");
					winnerScore = 0;
					winnerName = null;
					gameEnd = true;
				}
			}
			else if ((r1.getRow() == 6) && (r2.getRow() == 6)) {
				if ((b2.getPieceTrack() >= 0) && (b2.getPieceTrack() >= 0)) {
					System.out.print("\n\nIts a Tie!\n\n");
					winnerScore = 0;
					winnerName = null;
					gameEnd = true;
				}
			}
			if (r1.getRow() == 5) {
				if (b1.getPieceTrack() == 20) {
					System.out.printf("\n\nCongratulations! %s won the race with %d turns!\n\n", b1.getName(), b1.getTurn());
					winnerScore = b1.getTurn();
					winnerName = b1.getName();
					gameEnd = true;
				}
			}
			else if (r1.getRow() == 6) {
				if (b1.getPieceTrack() >= 0) { 
					System.out.printf("\n\nCongratulations! %s won the race with %d turns!\n\n", b1.getName(), b1.getTurn());
					winnerScore = b1.getTurn();
					winnerName = b1.getName();
					gameEnd = true;
				}
			}
			if (r2.getRow() == 5) {
				if (b2.getPieceTrack() == 20) {
					System.out.printf("\n\nCongratulations! %s won the race with %d turns!\n\n", b2.getName(), b2.getTurn());
					winnerScore = b2.getTurn();
					winnerName = b2.getName();
					gameEnd = true;
				}
			}
			else if (r2.getRow() == 6) {
				if (b2.getPieceTrack() >= 0) {
					System.out.printf("\n\nCongratulations! %s won the race with %d turns!\n\n", b2.getName(), b2.getTurn());
					winnerScore = b2.getTurn();
					winnerName = b2.getName();
					gameEnd = true;
				}
			}
		}
		else if (gm == 1) {
			if ((b2.getPieceTrack() >= 100) && (b2.getPieceTrack() >= 100)) {
				System.out.print("\n\nIts a Tie!\n\n");
				winnerScore = 0;
				winnerName = null;
				gameEnd = true;
			}
			if (b1.getPieceTrack() >= 100) {
				System.out.printf("\n\nCongratulations! %s won the race with %d turns!\n\n", b1.getName(), b1.getTurn());
				winnerScore = b1.getTurn();
				winnerName = b1.getName();
				gameEnd = true;
			}
			else if (b2.getPieceTrack() >= 100) {
				System.out.printf("\n\nCongratulations! %s won the race with %d turns!\n\n", b2.getName(), b2.getTurn());
				winnerScore = b2.getTurn();
				winnerName = b2.getName();
				gameEnd = true;
			}
			
		}
		
	}
	
	// Player's controller
	public void playerMove(int gm, String nm) {
		if (gm == 2) {
			System.out.printf("\n%s press Any to roll the dice: ", nm);
			input.nextLine();
			int diceNum = rollDice();
			System.out.printf("%s rolled a %d!\n", nm, diceNum);
			if (nm.equals(b1.getName())) {
				b1.addTurn();
				b1.nextTrack(diceNum);
				b1.setBoatPosition(b1.getPieceTrack());
			}
			else if (nm.equals(b2.getName())) {
				b2.addTurn();
				b2.nextTrack(diceNum);
				b2.setBoatPosition(b1.getPieceTrack());
			}
			checkCurrentHit(2);
			checkTrapHit(2);
			checkNextRound(diceNum);
			checkBackRound(diceNum);
			riverBoundary(2);
		}
		else if (gm == 1 ) {
			System.out.printf("\n%s press Any to roll the dice: ", nm);
			input.nextLine();
			int diceNum = rollDice();
			System.out.printf("%s rolled a %d!\n", nm, diceNum);
			if (nm.equals(b1.getName())) {
				b1.addTurn();
				b1.nextTrack(diceNum);
				b1.setBoatPosition(b1.getPieceTrack());
			}
			else if (nm.equals(b2.getName())) {
				b2.addTurn();
				b2.nextTrack(diceNum);
				b2.setBoatPosition(b1.getPieceTrack());
			}
			checkCurrentHit(1);
			checkTrapHit(1);
			riverBoundary(1);
		}
		
	}
	
	// When boat collides
	public void attackPlayer(int playerNum) {
		if (checkAttack() && (playerNum == 1)) {
			System.out.printf("%s Got hit by %s!\n", b2.getName(), b1.getName());
			System.out.printf("%s moves backward X%d!\n", b2.getName(), 3);
			b2.backTrack(3);
		}
		if (checkAttack() && (playerNum == 2)) {
			System.out.printf("%s Got hit by %s!\n", b1.getName(), b2.getName());
			System.out.printf("%s moves backward X%d!\n", b1.getName(), 3);
			b1.backTrack(3);
		}
	}
	
	public boolean checkAttack() {
		if (b1.getPieceTrack() == b2.getPieceTrack()) {
			return true;
		}
		return false;
	}
	
	// If boat proceeds to the next round
	public void checkNextRound(int d) {
		if (b1.getPieceTrack() > 20) { 
			r1.nextRow();
			r1.lastCurrent();
			r1.lastTrap();
			r1.updateCurrent();
			r1.updateTrap();
			b1.continueTrack(d);
		}
		if (b2.getPieceTrack() > 20) {
			r2.nextRow();
			r2.lastCurrent();
			r2.lastTrap();
			r2.updateCurrent();
			r2.updateTrap();
			b2.continueTrack(d);
		}
	}
	
	// If boat falls to the previous round
	public void checkBackRound(int d) {
		if (b1.getPieceTrack() < 1) { 
			r1.prevRow();
			r1.lastCurrent();
			r1.lastTrap();
			r1.reuseCurrent();
			r1.reuseTrap();
			b1.previousTrack(d);
		}
		if (b2.getPieceTrack() < 1) {
			r2.prevRow();
			r2.lastCurrent();
			r2.lastTrap();
			r2.reuseCurrent();
			r2.reuseTrap();
			b2.previousTrack(d);
		}
	}
	
	public void riverBoundary(int gm) {
		if (gm == 2) {
			if (r1.getRow() < 1) {
				b1.setTrack(0);
				b1.setPieceTrack(1);
				r1.setRow(1);
			}
			if (r2.getRow() < 1) {
				b2.setTrack(0);
				b2.setPieceTrack(1);
				r2.setRow(1);
			}
		}
		if (gm == 1) {
			if (b1.getPieceTrack() < 1) {
				b1.setTrack(0);
				b1.setPieceTrack(1);
				r.setRow(1);
			}
			if (b2.getPieceTrack() < 1) {
				b2.setTrack(0);
				b2.setPieceTrack(1);
				r.setRow(1);
			}
		}
	}
	
	// Check if boat hits the current
	public void checkCurrentHit(int gm) {
		Random rd = new Random();
		if (gm == 2) {
			while (r1.checkCurrent(b1.getTrack())) {
				System.out.printf("%s Got hit by a current!\n", b1.getName());
				int rc = rd.nextInt(3) + 1;
				b1.nextTrack(rc);
				System.out.printf("%s moves forward X%d!\n", b1.getName(), rc);
				
			}
			while (r2.checkCurrent(b2.getTrack())) {
				System.out.printf("%s Got hit by a current!\n", b2.getName());
				int rc = rd.nextInt(3) + 1;
				b2.nextTrack(rc);
				System.out.printf("%s moves forward X%d!\n", b2.getName(), rc);
			}
		}
		else if (gm == 1) {
			while (r.checkCurrent(b1.getTrack())) {
				System.out.printf("%s Got hit by a current!\n", b1.getName());
				int rc = rd.nextInt(3) + 1;
				b1.nextTrack(rc);
				System.out.printf("%s moves forward X%d!\n", b1.getName(), rc);
				
			}
			while (r.checkCurrent(b2.getTrack())) {
				System.out.printf("%s Got hit by a current!\n", b2.getName());
				int rc = rd.nextInt(3) + 1;
				b2.nextTrack(rc);
				System.out.printf("%s moves forward X%d!\n", b2.getName(), rc);
			}
		}
		
	}
	
	// Check if boat hits the trap
	public void checkTrapHit(int gm) {
		Random rd = new Random();
		if (gm == 2) {
			while (r1.checkTrap(b1.getTrack())) {
				System.out.printf("%s Got hit by a trap!\n", b1.getName());
				int rt = rd.nextInt(3) + 1;
				b1.backTrack(rt);
				checkBackRound(rt);
				System.out.printf("%s moves backward X%d!\n", b1.getName(), rt);
			}
			while (r2.checkTrap(b2.getTrack())) {
				System.out.printf("%s Got hit by a trap!\n", b2.getName());
				int rt = rd.nextInt(3) + 1;
				b2.backTrack(rt);
				checkBackRound(rt);
				System.out.printf("%s moves backward X%d!\n", b2.getName(), rt);
			}
		}
		else if (gm == 1) {
			while (r.checkTrap(b1.getTrack())) {
				System.out.printf("%s Got hit by a trap!\n", b1.getName());
				int rt = rd.nextInt(3) + 1;
				b1.backTrack(rt);
				System.out.printf("%s moves backward X%d!\n", b1.getName(), rt);
			}
			while (r.checkTrap(b2.getTrack())) {
				System.out.printf("%s Got hit by a trap!\n", b2.getName());
				int rt = rd.nextInt(3) + 1;
				b2.backTrack(rt);
				System.out.printf("%s moves backward X%d!\n", b2.getName(), rt);
			}
		}
		
	}
	
	// Get winner's name and score
	public int getWinnerScore() {
		return winnerScore;
	}
	public String getWinnerName() {
		return winnerName;
	}
	
	
} 