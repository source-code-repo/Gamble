package gamble.round;

import gamble.Config;
import gamble.Util;
import gamble.card.Card;
import gamble.card.CardConsoleOutputter;

import java.util.List;

/**
 * UI for round output
 */
public class RoundConsoleOutputter implements RoundEventListener {
  final CardConsoleOutputter cardOut;

  public RoundConsoleOutputter(CardConsoleOutputter cardOut) {
    this.cardOut = cardOut;
  }

  public void print(String s) {
    System.out.println(s);
  }

  /* (non-Javadoc)
   * @see gamble.service.output.RoundOutputter#roundOver()
   */
  @Override
  public void roundOver() {
    String str = String.format("\n*** You beat your opponent's card! ***\n");
    print(str);
    Util.pause(Config.DELAY * 3);
  }

  /* (non-Javadoc)
   * @see gamble.service.output.RoundOutputter#playedCard(int)
   */
  @Override
  public void playerPlayingCard(int value) {
    System.out.print("\nYour card's value is.");

    Util.pause(Config.DELAY);
    System.out.print(".");
    Util.pause(Config.DELAY);
    System.out.print(".");
    Util.pause(Config.DELAY);

    String str = String.format("%d", value);
    print(str);

    Util.pause(Config.DELAY * 2);
  }

  /* (non-Javadoc)
   * @see gamble.service.output.RoundOutputter#cardUsedUp()
   */
  @Override
  public void chosenEmptyCard() {
    print("\nCard has reached max uses, please select another card");
  }

  @Override
  public void opponentShowingCard(int target, int totalPlayed, List<Card> cards) {
    String str = String.format("\nYour opponent's card has %01d/%01d points.",
      target - totalPlayed, target);
    print(str);
    Util.pause(Config.DELAY * 2);
  }

  @Override
  public void playerChoosingCard(List<Card> cards) {
    cardOut.showPlayerCards(cards);
    print("Choose a card to play: ");
  }
}