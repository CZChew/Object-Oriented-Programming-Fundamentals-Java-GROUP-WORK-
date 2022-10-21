
public class BoardPiece {
	private int track; // Display boat position on track (Even number)
	private int pieceTrack; // In-game position tracking
	private String symbol; // The symbol of the game piece (boat, current, trap)
	private int gameMode;
	
	
	// Constructor
	public BoardPiece() { 
		track = 0;
		pieceTrack = 1;
		symbol = "X"; // Default values
	}
	

	// Track
	public void setTrack(int t) {
		track = t;
	}
	public void nextTrack(int t) { // Boat move forward
		track += (t * 2);
		pieceTrack += t;
	}
	public void backTrack(int t) { // Boat move backward
		track -= (t * 2);
		pieceTrack -= t;
	}
	public void continueTrack(int d) { // When boat have entered the new row/round
		pieceTrack = (getPieceTrack() - d) - (20 - d);
		track = (getTrack() - (d * 2)) - (40 - (d * 2));
	}
	public void previousTrack(int d) { // When boat have fall to the previous row/round
		pieceTrack = 20 + (getPieceTrack() - d);
		track = 40 + (getTrack() - (d * 2));
	}
	public int getTrack() {
		return track;
	}
	public void setPieceTrack(int pt) {
		pieceTrack = pt;
	}
	public int getPieceTrack() {
		return pieceTrack;
	}
	
	// Symbol
	public void setSymbol(String s) {
		symbol = s;
	}
	public String getSymbol() {
		return symbol;
	}
	
	// GameMode (Additional functionality purposes)
	public void setGameMode(int gm) {
		gameMode = gm;
	}
	public int getGameMode() {
		return gameMode;
	}
	
	
	
}
