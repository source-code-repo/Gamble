package gamble;

public class Config {

	public static final int MAX_CPU_CARD_VALUE = 10;
	public static int DELAY = 500;
	
	// Rewards for each match
	public static final int[] REWARDS = {1,2,3,1,3,2,2,4,1,2,
	        4,1,2,3,2};
	
	// Number of CPU cards in each match
	public static final int[] CARDS_PER_MATCH = {1,2,3,1,3,2,2,4,1,2,
	        4,1,2,3,2};
	
	public static final String[] NUM_TO_NAME = { "first", "second", "third", "fourth", "fifth",
												  "sixth", "seventh", "eigth", "ninth", "tenth",
												  "eleventh", "twelfth", "thirteenth", "fourteenth",
												  "fifteenth"};
	
	public static final int VILLAGE_UNLOCKED_AFTER = 1;
    public static int targetGold = 66;
}
