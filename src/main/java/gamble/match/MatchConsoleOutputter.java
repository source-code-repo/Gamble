package gamble.match;

import gamble.Config;
import gamble.Util;
import gamble.card.CardConsoleOutputter;
import gamble.card.CardService;
import gamble.player.Player;

public class MatchConsoleOutputter implements MatchEventListener {
  private final CardConsoleOutputter co;

  public MatchConsoleOutputter(CardService cs, CardConsoleOutputter co) {
    super();
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
  public void exactHit(Player p) {
    String str = "A precise hit lets you recharge a card: ";
    Util.print(str);

    co.showPlayerCards(p.cards);
  }

  @Override
  public void roundStarted(int roundNum, int opponentCardsLeft) {
    String str = String.format("Your opponent shows their %s card. "
        + "They have %d other card(s).", Util.numToName(roundNum),
      opponentCardsLeft - 1);
    Util.print(str);
    Util.pause(Config.DELAY);
  }
}
