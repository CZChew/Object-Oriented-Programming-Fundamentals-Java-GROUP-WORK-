import java.util.Random;

public class Current extends BoardPiece{ // Current is-a BoardPiece
	private int currentNum; // Number of currents in a row
	
	// Constructor
	public Current(int gm) {
		super();
		setGameMode(gm);
		setCurrentNum();
		setSymbol("C");
	}
	
	// Use random generator to determine the number of currents (1-3)
	public void setCurrentNum() {
		Random r = new Random();
		if (getGameMode() == 2) {
			int c = r.nextInt(3) + 1;
			currentNum = c;
		}
		else if (getGameMode() == 1) {
			int c = r.nextInt(20) + 1;
			currentNum = c;
		}
		
	}
	
	// Setter/Getter
	public int getCurrentNum() {
		return currentNum;
	}
	// Use random generator to scatter currents around the river
	public void setCurrentTrack() {
		Random r = new Random();
		if (getGameMode() == 2) {
			int cp = (r.nextInt(19) + 1) * 2;
			setTrack(cp);
		}
		else if (getGameMode() == 1) {
			int cp = (r.nextInt(99) + 1) * 2;
			setTrack(cp);
		}
		
	}
	
	
}
