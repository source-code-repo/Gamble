package gamble.shop.item;

import gamble.card.Card;
import gamble.card.CardInputter;
import gamble.player.Player;
import gamble.shop.Purchasable;
import lombok.RequiredArgsConstructor;

/**
 * Boosts the damage output of a card
 */
@RequiredArgsConstructor
public class DamageBoost implements Purchasable {
  private static final int MINIMUM_CLAN_NUMBER = 2;
  private static final int UPGRADE_AMOUNT = 3;

  private final DamageBoostEventListener damageBoostEventListener;
  private final CardInputter cardInputter;

  @Override
  public boolean isAvailable(int clansBeaten) {
    return clansBeaten >= MINIMUM_CLAN_NUMBER;
  }

  @Override
  public void purchase(Player player) {
    damageBoostEventListener.selectingCard(player.getCards());
    Card card = cardInputter.selectCard(player.getCards());
    card.setMinValue(card.getMinValue() + UPGRADE_AMOUNT);
    card.setMaxValue(card.getMaxValue() + UPGRADE_AMOUNT);
    damageBoostEventListener.upgradeComplete(card);
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
