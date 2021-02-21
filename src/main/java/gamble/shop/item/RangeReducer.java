package gamble.shop.item;

import gamble.Util;
import gamble.player.Player;
import gamble.shop.Purchasable;

/**
 * Increases a card's minimum damage, reducing it's range
 */
public class RangeReducer implements Purchasable {
  private static final int MINIMUM_CLAN_NUMBER = 3;

  @Override
  public boolean isAvailable(int clansBeaten) {
    return clansBeaten >= MINIMUM_CLAN_NUMBER;
  }

  @Override
  public void purchase(Player player) {
    Util.print("Implementing bundle of herbs min damage increase");
  }

  @Override
  public String description() {
    return "Bundle of herbs\n" +
      "  A strongly scented herb with an unusual green tinge in the sunlight.\n" +
      "  Boosts a card's minimum damage";
  }

  @Override
  public int cost() {
    return 15;
  }
}
