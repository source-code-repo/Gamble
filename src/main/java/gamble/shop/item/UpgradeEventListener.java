package gamble.shop.item;

import gamble.card.Card;

import java.util.List;

public interface UpgradeEventListener {
  /**
   * Player is selecting a card
   * @param cards Cards to choose from
   */
  void selectingCard(List<Card> cards);

  /**
   * Card upgrade is complete
   */
  void upgradeComplete(Card card);

  /**
   * User selected an upgrade that they can't have
   * @param reason Reason why they can't upgrade
   */
  void cantUpgrade(String reason);
}
