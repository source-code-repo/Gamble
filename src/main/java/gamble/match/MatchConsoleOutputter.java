package gamble.match;

import gamble.Util;
import gamble.card.CardConsoleOutputter;
import gamble.card.CardService;
import gamble.Config;
import gamble.player.Player;

public class MatchConsoleOutputter implements MatchEventListener {
	
	private final CardService cs;
	private final CardConsoleOutputter co;

	public MatchConsoleOutputter(CardService cs, CardConsoleOutputter co) {
        super();
        this.cs = cs;
		this.co = co;
    }

    /* (non-Javadoc)
     * @see gamble.service.output.MatchOutputter#gameOver()
     */
	@Override
    public void matchLost() {
		String str = String.format("Uh oh, you're out of cards :(");
		Util.print(str);
	}

	@Override
	public void exactMatch(Player p) {
		String str = "A precise hit lets you recharge a card: ";
		Util.print(str);

		co.showPlayerCards(p.cards);
		cs.rechargeCard(p);
	}

	@Override
	public void roundStarted(int roundNum, int cpuCardsLeft) {
		String str = String.format("Your opponent shows their %s card. "
						+ "They have %d other card(s).", cs.numToName(roundNum),
				cpuCardsLeft - 1);
		Util.print(str);
		Util.pause(Config.DELAY);
	}
}
