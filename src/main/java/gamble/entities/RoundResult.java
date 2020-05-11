package gamble.entities;

public class RoundResult {
	public boolean exactMatch = false;
	public boolean won = false;
	
	public RoundResult(boolean exactMatch, boolean won) {
		super();
		this.exactMatch = exactMatch;
		this.won = won;
	}
}
