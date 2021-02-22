package gamble.shop.item;

import gamble.card.Card;
import gamble.card.CardInputter;
import gamble.player.Player;
import gamble.shop.Purchasable;
import lombok.RequiredArgsConstructor;

/**
 * Increases a card's minimum damage, reducing it's range
 */
@RequiredArgsConstructor
public class RangeReducer implements Purchasable {
  private static final int MINIMUM_CLAN_NUMBER = 4;
  private static final int UPGRADE_AMOUNT = 2;

  private final CardInputter cardInputter;
  private final UpgradeEventListener upgradeEventListener;

  @Override
  public boolean isAvailable(int clansBeaten) {
    return clansBeaten >= MINIMUM_CLAN_NUMBER;
  }

  @Override
  public void purchase(Player player) {
    upgradeEventListener.selectingCard(player.getCards());
    Card card = cardInputter.selectCard(player.getCards());
    // Don't allow cards to have max-min damage less than 2 apart
    // e.g. damage 3-4 is not allowed.
    if(card.getMinValue() + UPGRADE_AMOUNT > card.getMaxValue() - 2) {
      upgradeEventListener.cantUpgrade(
        "You can't upgrade this card the min and max " +
          "damage are too close together");
      return;
    }
    card.setMinValue(card.getMinValue() + UPGRADE_AMOUNT);
    player.reduceGold(cost());
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
