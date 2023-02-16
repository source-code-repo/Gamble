package gamble.shop.item;

import gamble.Util;
import gamble.card.Card;
import gamble.card.CardConsoleOutput;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class UpgradeConsoleOutput implements UpgradeEventListener {
  private final CardConsoleOutput cardConsoleOutput;

  @Override
  public void selectingCard(List<Card> cards) {
    Util.print("Choose a card to upgrade:");
    cardConsoleOutput.showPlayerCards(cards);
  }

  @Override
  public void upgradeComplete(Card card) {
    Util.print(String.format(
      "Your card's new damage is %d-%d",
      card.getMinValue(), card.getMaxValue()
    ));
  }

  @Override
  public void cantUpgrade(String reason) {
    Util.print(reason);
  }
}
