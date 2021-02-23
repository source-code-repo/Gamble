package gamble.card;

import gamble.Util;

import java.util.List;

public class CardConsoleOutputter implements CardEventListener {
  /**
   * Shows a player's cards
   * @param cards Cards
   */
  public void showPlayerCards(List<Card> cards) {
    int cardCount = 1;
    var sb = new StringBuilder();
    for (Card pc : cards) {
      sb.append(String.format("Card %d  %2d-%2d     Uses left  %2d/%2d%n",
        cardCount, pc.minValue, pc.maxValue, pc.uses, pc.maxUses));


      cardCount++;
    }
    System.out.print(sb.toString());
  }

  @Override
  public void rechargingCards() {
    Util.print("Choose a card to recharge: ");

  }

  @Override
  public void badResponse() {
    Util.printNoDelay("Sorry I don't understand :( Try again.");
  }
}
