package gamble.match;

import gamble.Config;
import gamble.Util;
import gamble.card.CardConsoleOutputter;
import gamble.card.CardService;
import gamble.player.Player;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MatchConsoleOutputter implements MatchEventListener {
  private final CardConsoleOutputter co;

  /* (non-Javadoc)
   * @see gamble.service.output.MatchOutputter#gameOver()
   */
  @Override
  public void matchLost() {
    Util.print("Uh oh, you're out of cards :(");
  }

  @Override
  public void exactHit(Player p) {
    Util.print("You got a precise hit! Choose a card to recharge: ");
    co.showPlayerCards(p.getCards());
  }

  @Override
  public void roundStarted(int roundNum, int fightersLeft) {
    String str = String.format("The clan send out the %s fighter. "
        + "They have %d other fighter(s) left.",
      Util.numToName(roundNum),
      fightersLeft - 1);
    Util.print(str);
    Util.pause(Config.DELAY);
  }
}
