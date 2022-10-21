
public class Boat extends BoardPiece { // Boat is-a BoardPiece

	private String name;
	private int boatPosition;
	private int turn;
	
	// Constructor
    public Boat(String nm){
    	super();
    	name = nm;
    	boatPosition = 1;
    	turn = 0;
    }
    
    // Constructor for boat in Death Game mode
    public Boat(String nm, String s, int t, int pt) {
    	super();
    	name = nm;
    	boatPosition = 1;
    	turn = 0;
    	setSymbol(s);
    	setTrack(t);
    	setPieceTrack(pt);
    }
    
    // Setter/Getter for name and its position to display
    public void setName(String nm) {
    	name = nm;
    }
    public String getName() {
        return name;
    }
    
    public void setBoatPosition(int bp) {
    	boatPosition = bp;
    }
    public int getBoatPosition() {
    	return boatPosition;
    }
    
 // To calculate total turns for player to win
    public void setTurn(int t) {
    	turn = t;
    }
    public void addTurn() {
    	turn ++;
    }
    public int getTurn() {
    	return turn;
    }
}
