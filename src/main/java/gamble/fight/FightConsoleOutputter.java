package gamble.fight;

import gamble.Config;
import gamble.Util;
import gamble.card.Card;
import gamble.card.CardConsoleOutputter;

import java.util.List;

/**
 * UI for round output
 */
public class FightConsoleOutputter implements FightEventListener {
  final CardConsoleOutputter cardOut;

  public FightConsoleOutputter(CardConsoleOutputter cardOut) {
    this.cardOut = cardOut;
  }

  /* (non-Javadoc)
   * @see gamble.service.output.RoundOutputter#roundOver()
   */
  @Override
  public void roundOver() {
    String str = String.format("%n*** You beat the fighter! ***%n");
    Util.printNoDelay(str);
    Util.pause(Config.DELAY * 3L);
  }

  /* (non-Javadoc)
   * @see gamble.service.output.RoundOutputter#playedCard(int)
   */
  @Override
  public void playerAttacking(int damage) {
    Util.printNoNewLine("Your card's attack damage is.");

    Util.pause(Config.DELAY);
    Util.printNoNewLine(".");
    Util.pause(Config.DELAY);
    Util.printNoNewLine(".");
    Util.pause(Config.DELAY);

    String str = String.format("%d", damage);
    Util.printNoDelay(str);

    Util.pause(Config.DELAY * 2L);
  }

  /* (non-Javadoc)
   * @see gamble.service.output.RoundOutputter#cardUsedUp()
   */
  @Override
  public void chosenEmptyCard() {
    Util.printNoDelay("\nCard has reached max uses, please select another card");
  }

  @Override
  public void fighterShowingHp(int totalHp, int hpLeft, List<Card> cards) {
    String str = String.format("Fighter Health: %01d/%01d.",
      totalHp - hpLeft, totalHp);
    Util.printNoDelay(str);
    Util.pause(Config.DELAY * 2L);
  }

  @Override
  public void playerChoosingCard(List<Card> cards) {
    cardOut.showPlayerCards(cards);
    Util.printNoDelay("Choose a card to play: ");
  }
}