package gamble.shop.item;

import gamble.card.Card;
import gamble.card.CardInput;
import gamble.player.Player;
import gamble.shop.Purchasable;
import lombok.RequiredArgsConstructor;

/**
 * Reduce a card's range by increasing its minimum damage,
 */
@RequiredArgsConstructor
public class RangeReducer implements Purchasable {
  private static final int MINIMUM_CLAN_NUMBER = 7;
  private static final int UPGRADE_AMOUNT = 2;

  private final CardInput cardInput;
  private final UpgradeEventListener upgradeEventListener;

  @Override
  public boolean isAvailable(int clansBeaten) {
    return clansBeaten >= MINIMUM_CLAN_NUMBER;
  }

  @Override
  public void purchase(Player player) {
    upgradeEventListener.selectingCard(player.getCards());
    Card card = cardInput.selectCard(player.getCards());
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
    upgradeEventListener.upgradeComplete(card);
  }

  @Override
  public String description() {
    return """
      Bundle of herbs
      A strongly scented herb with an unusual green tinge in the sunlight.
      Boosts a card's minimum damage""";
  }

  @Override
  public int cost() {
    return 15;
  }
}
