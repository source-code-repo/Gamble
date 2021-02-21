package gamble.shop;

import gamble.Util;
import lombok.RequiredArgsConstructor;

/**
 * Boosts the damage output of a card
 */
@RequiredArgsConstructor
public class DamageBoost implements Purchasable {
  private static final int MINIMUM_CLAN_NUMBER = 5;

  @Override
  public boolean isAvailable(int clanNumber) {
    return clanNumber >= MINIMUM_CLAN_NUMBER;
  }

  @Override
  public void purchase() {
    Util.print("Implementing damage boost");
  }

  @Override
  public String description() {
    return "Sorcerer's Gem\n" +
      "  A dull stone, semi-transparent with a faint glow from the core.\n" +
      "  Boosts a card's damage.";
  }

  @Override
  public int cost() {
    return 10;
  }
}
