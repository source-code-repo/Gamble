package gamble.game;

import gamble.Config;
import gamble.Util;

public class GameConsoleOutputter implements GameEventListener {

    @Override
    public void matchStarted(int matchNum, int reward) {
        String str = String.format("**************************************\n"
                + "You come face to face with opponent %d\n"
                + "**************************************\n", matchNum);
    
        Util.print(str);
        str = String.format("Reward: %d gold", reward);
        Util.print(str);
        Util.pause(Config.DELAY * 3);
        
    }

	/* (non-Javadoc)
     * @see gamble.service.output.GameOutputter#beatOpponent(int, int)
     */
	@Override
    public void matchWon(int opponentNum, int reward, int multiplier) {
		String str = String.format("Nice one! Opponent %d is defeated. "
		        + "You get %d gold (%d for the win with a %d multiplier). "
		        + "Your multiplier increased to %d.", 
		        opponentNum, reward, reward / (multiplier - 1), multiplier - 1, 
		        multiplier);
		Util.print(str);
		Util.pause(Config.DELAY * 2); 
	}
	
	/* (non-Javadoc)
     * @see gamble.service.output.GameOutputter#lost(int)
     */
	@Override
    public void matchLost(int opponentNum) {
		String str = String.format("Uh oh. You've been beaten by opponent %d ", opponentNum);
		Util.print(str);
		Util.pause(Config.DELAY * 2); 
	}

	/* (non-Javadoc)
     * @see gamble.service.output.GameOutputter#gold(int)
     */
	@Override
    public void rewardGiven(int gold) {
		String str = String.format("You have %d gold\n", gold);
		Util.print(str);
	}

    @Override
    public void gameStarted() {
        String str = "You are a warrior tasked by the king of the realm "
                + "to protect the treacherous dark forest.\nIt turns out the "
                + "forest is quite safe and full of gamblers\nlooking "
                + "to play a new card game that has swept the kingdom.\n"
                + "Before you realise it, you are addicted!\n";
        Util.print(str);
        Util.pause(5000);
    }
}
