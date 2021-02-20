package gamble.match;

import gamble.config.Config;
import gamble.card.CardService;
import gamble.Util;

public class ConsoleMatchOutputter implements MatchOutputter {
	
	private CardService cs;

	public ConsoleMatchOutputter(CardService cs) {
        super();
        this.cs = cs;
    }

    /* (non-Javadoc)
     * @see gamble.service.output.MatchOutputter#gameOver()
     */
	@Override
    public void gameOver() {
		String str = String.format("Uh oh, you're out of cards :(");
		Util.print(str);
	}
	
	/* (non-Javadoc)
     * @see gamble.service.output.MatchOutputter#score(long)
     */
	@Override
    public void score(long score) {
		String str = String.format("Your new score: %d gold", score);
		Util.print(str);
	}

	/* (non-Javadoc)
     * @see gamble.service.output.MatchOutputter#nextRound(int)
     */
	@Override
    public void nextRound(int roundNum, int cpuCardsLeft) {
		String str = String.format("Your opponent shows their %s card. "
		        + "They have %d other card(s).", cs.numToName(roundNum), 
		        cpuCardsLeft - 1);
		Util.print(str);
		Util.pause(Config.DELAY);
	}

    @Override
    public void rechargeChard() {
        String str = "A precise hit lets you recharge a card: ";
        Util.print(str);
    }
}
