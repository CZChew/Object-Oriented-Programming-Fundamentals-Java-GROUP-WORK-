import java.util.Random;

public class Trap extends BoardPiece{ // Trap is-a BoardPiece
	private int trapNum;
	
	// Constructor
	public Trap(int gm) {
		super();
		setGameMode(gm);
		setTrapNum();
		setSymbol("#");
	}
	
	// Setter/Getter
	// To determine the number of traps in a row
	public void setTrapNum() {
		Random r = new Random();
		if (getGameMode() == 2) {
			int t = r.nextInt(3) + 1;
			trapNum = t;
		}
		else if (getGameMode() == 1) {
			int t = r.nextInt(20) + 1;
			trapNum = t;
		}
		
	}
	public int getTrapNum() {
		return trapNum;
	}
	// To scatter the traps around the river
	public void setTrapTrack() {
		Random r = new Random();
		if (getGameMode() == 2) {
			int tp = (r.nextInt(19) + 1) * 2;
			setTrack(tp);
		}
		else if (getGameMode() == 1) {
			int tp = (r.nextInt(99) + 1) * 2;
			setTrack(tp);
		}
		
	}

}
