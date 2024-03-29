package gamble.shop.item;

import gamble.card.Card;
import gamble.card.CardInput;
import gamble.player.Player;
import gamble.shop.Purchasable;
import lombok.RequiredArgsConstructor;

/**
 * Boosts the damage output of a card
 */
@RequiredArgsConstructor
public class DamageBoost implements Purchasable {
  private static final int MINIMUM_CLAN_NUMBER = 5;
  private static final int UPGRADE_AMOUNT = 3;

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
    card.setMinValue(card.getMinValue() + UPGRADE_AMOUNT);
    card.setMaxValue(card.getMaxValue() + UPGRADE_AMOUNT);
    upgradeEventListener.upgradeComplete(card);
    player.reduceGold(cost());
  }

  @Override
  public String description() {
    return """
      Sorcerer's Gem
      A dull stone, semi-translucent, glowing faintly from its core.
      Boosts a card's damage.""";
  }

  @Override
  public int cost() {
    return 10;
  }
}
