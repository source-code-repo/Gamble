package gamble.shop.item;

import gamble.Util;

public class DamageBoostConsoleOutputter implements DamageBoostEventListener {
  @Override
  public void selectingCard() {
    Util.print("Choose a card to upgrade:");
  }
}
