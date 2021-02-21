package gamble.shop.item;

import gamble.card.Card;

import java.util.List;

public interface DamageBoostEventListener {
  /**
   * Player is selecting a card
   * @param cards Cards to choose from
   */
  void selectingCard(List<Card> cards);

  /**
   * Card upgrade is complete
   */
  void upgradeComplete(Card card);
}
