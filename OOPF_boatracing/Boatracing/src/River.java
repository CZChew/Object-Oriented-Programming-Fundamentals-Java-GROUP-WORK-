import java.util.ArrayList;

public class River { // River has-a-relationship with boat, current and trap (Aggregation)
	
	// Variables
	private ArrayList<String> makeRiver = new ArrayList<String>();
	private int row; // To determine the round of a player (max 5 rounds)
	private int column; // Columns of the river including its path and checks
	private String riverCheck; // A sign that ends a track
	private String riverTrack; // A track for players to race
	private String river = ""; // The river display
	
	private Boat boat;
	private Boat boat2;
	private Current current;
	private Trap trap;
	
	private ArrayList<Integer>currentHistory = new ArrayList<Integer>();
	private ArrayList<Integer>trapHistory = new ArrayList<Integer>();
	private ArrayList<Integer> currentList = new ArrayList<Integer>();
	private ArrayList<Integer> trapList = new ArrayList<Integer>();
	
	
	// Constructors
	public River(Boat b, Current c, Trap t) {
		boat = b;
		current = c;
		trap = t;
		row = 1;
		column = 40;
		riverCheck = "|";
		riverTrack = " ";
		createRiver(column, riverCheck, riverTrack);
		addCurrent(current.getCurrentNum());
		addTrap(trap.getTrapNum());
		addBoat(boat.getSymbol(), boat.getTrack());
		printRiver();
	}
	
	public River(Boat b1, Boat b2, Current c, Trap t) {
		boat = b1;
		boat2 = b2;
		current = c;
		trap = t;
		row = 1;
		column = 210;
		riverCheck = "|";
		riverTrack = " ";
		createRiver(column, riverCheck, riverTrack);
		addCurrent(current.getCurrentNum());
		addTrap(trap.getTrapNum());
		addBoat(boat.getSymbol(), boat.getTrack());
		addBoat(boat2.getSymbol(), boat2.getTrack());
		printRiver();
	}
	
	// Setter/Getter
	// Increment by 1 after a row of track is completed
	public void nextRow() {
		row += 1;
	}
	// Decrement by 1 after player falls back to previous track
	public void prevRow() {
		row -= 1;
	}
	public void setRow(int r) {
		row = r;
	}
	public int getRow() {
		return row;
	}
	public int getColumn() {
		return column;
	}
	
	// Updating the display on the boat's position
	public void setBoatPosition(int bp) {
		boat.setBoatPosition(bp);
	}
	public int getBoatPosition() {
		return boat.getBoatPosition();
	}
	
	// River checkpoint for each column
	public String getRiverCheck() {
		return riverCheck;
	}
	// River's track/water in the game
	public String getRiverTrack() {
		return riverTrack;
	}
	
	
	// Methods
	public void createRiver(int c, String rc, String rt) { // Form a river for the game
		for (int x=0; x < c; x++) {
			makeRiver.add(rt); 
		}
		for (int y = 1; y < makeRiver.size(); y+=2) {
			makeRiver.set(y, rc);
		}
	}
	// Update river after every steps by boat
	public void updateRiver(int gm, int cl, String rc, String rt, String b, String b2, int p, int p2) {
		if (gm == 2) {
			makeRiver.clear();
			river = "";
			for (int x=0; x < cl; x++) {
				makeRiver.add(rt); 
			}
			for (int y = 1; y < makeRiver.size(); y+=2) {
				makeRiver.set(y, rc);
			}
			reAddCurrent();
			reAddTrap();
			addBoat(b, p);
			printRiver();
		}
		if (gm == 1) {
			makeRiver.clear();
			river = "";
			for (int x=0; x < cl; x++) {
				makeRiver.add(rt); 
			}
			for (int y = 1; y < makeRiver.size(); y+=2) {
				makeRiver.set(y, rc);
			}
			reAddCurrent();
			reAddTrap();
			addBoat(b, p);
			addBoat(b2, p2);
			printRiver();
		}
		
	}
	
	// Adding boats to display
	public void addBoat(String b, int p) {
		makeRiver.set(p, b);
	}
	
	// Adding currents to display
	public void addCurrent(int cn) {
		for (int i = 0; i < cn; i++) {
			current.setCurrentTrack();
			currentList.add(current.getTrack());
			makeRiver.set(current.getTrack(), current.getSymbol());
		}
	}
	public void reAddCurrent() {
		for (int i = 0; i < currentList.size(); i++) {
			int cp = currentList.get(i);
			makeRiver.set(cp, current.getSymbol());
		}
	}
	public void updateCurrent() {
		currentList.clear();
		current.setCurrentNum();
		addCurrent(current.getCurrentNum());
	}
	public void lastCurrent() {
		currentHistory.clear();
		for (int c: currentList) {
			currentHistory.add(c);
		}
	}
	public void reuseCurrent() {
		currentList.clear();
		for (int ch: currentHistory) {
			currentList.add(ch);
			makeRiver.set(ch,  current.getSymbol());
		}
	}
	
	// Check if boat hits current, return boolean values
	public boolean checkCurrent(int bp) {
		for (int c: currentList) {
			if (bp == c) {
				return true;
			}
		}
		return false;
	}
	
	// Adding traps to display
	public void addTrap(int tn) {
		for (int i = 0; i < tn; i++) {
			trap.setTrapTrack();
			trapList.add(trap.getTrack());
			makeRiver.set(trap.getTrack(), trap.getSymbol());
		}
	}
	public void reAddTrap() {
		for (int i = 0; i < trapList.size(); i++) {
			int tp = trapList.get(i);
			makeRiver.set(tp, trap.getSymbol());
		}
	}
	public void updateTrap() {
		trapList.clear();
		trap.setTrapNum();
		addTrap(trap.getTrapNum());
	}
	public void lastTrap() {
		trapHistory.clear();
		for (int t: trapList) {
			trapHistory.add(t);
		}
	}
	public void reuseTrap() {
		trapList.clear();
		for (int th: trapHistory) {
			trapList.add(th);
			makeRiver.set(th,  trap.getSymbol());
		}
	}
	// Check if boat hits trap, return boolean values
	public boolean checkTrap(int bp) {
		for (int t: trapList) {
			if (bp == t) {
				return true;
			}
		}
		return false;
	}
	
	// Adding the elements of all river to a String
	public void printRiver() {
		for (int i=0; i < makeRiver.size(); i++) {
			river += makeRiver.get(i);
		}
	}
	
	// toString method
	// Friendly Game mode
	public String toString() {
		return String.format("\n%s		ROUND: %d	"
				+ "%d/20\n========================================\n"
				+ "%s\n========================================\n",boat.getName(), getRow(), boat.getBoatPosition(), river);
	}
	// Death Game mode
	public String deathtoString() {
		return String.format("\n%s: %d/100					%s: %d/100\n============"
				+ "=========================================="
				+ "================================================================"
				+ "================================================="
				+ "=============================================\n"
				+ "%s\n=============================================="
				+ "=================================================="
				+ "=================================================="
				+ "============================"
				+ "======================================\n",boat.getName(), boat.getBoatPosition(), boat2.getName(), boat2.getBoatPosition(), river);
	}
	
}
