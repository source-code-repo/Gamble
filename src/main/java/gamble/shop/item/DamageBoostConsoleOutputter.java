package gamble.shop.item;

import gamble.Util;
import gamble.card.Card;
import gamble.card.CardConsoleOutputter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class DamageBoostConsoleOutputter implements DamageBoostEventListener {
  private final CardConsoleOutputter cardConsoleOutputter;

  @Override
  public void selectingCard(List<Card> cards) {
    Util.print("Choose a card to upgrade:");
    cardConsoleOutputter.showPlayerCards(cards);
  }

  @Override
  public void upgradeComplete(Card card) {
    Util.print(String.format(
      "Your card's new damage is %d-%d",
      card.getMinValue(), card.getMaxValue()
    ));
  }
}
