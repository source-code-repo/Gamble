package gamble.clanbattle;

import gamble.Config;
import gamble.Util;
import gamble.card.CardConsoleOutputter;
import gamble.player.Player;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ClanBattleConsoleOutputter implements ClanBattleEventListener {
  private final CardConsoleOutputter co;

  /* (non-Javadoc)
   * @see gamble.service.output.MatchOutputter#gameOver()
   */
  @Override
  public void clanBattleLost() {
    Util.print("Uh oh, you're out of cards :(");
  }

  @Override
  public void exactHit(Player p) {
    Util.print("You got a precise hit! Choose a card to recharge: ");
    co.showPlayerCards(p.getCards());
  }

  @Override
  public void fightStarting(int fightNumber, int fightersLeft) {
    String str = String.format("The clan send out the %s fighter. "
        + "They have %d other fighter(s) left.",
      Util.numToName(fightNumber),
      fightersLeft - 1);
    Util.print(str);
    Util.pause(Config.DELAY);
  }
}
