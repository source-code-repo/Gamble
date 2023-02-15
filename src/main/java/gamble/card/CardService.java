package gamble.card;

import gamble.player.Player;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class CardService {
  private final CardInputter in;
  private final List<CardEventListener> eventListeners = new ArrayList<>();

  public boolean hasAttacksLeft(List<Card> cards) {
    for (Card pc : cards) {
      if (pc.getUses() > 0) {
        return true;
      }
    }
    return false;
  }

  /**
   * Allows the player to recharge a single card
   */
  public void rechargeCard(Player p) {
    eventListeners.forEach(CardEventListener::rechargingCards);
    Card card = in.selectCard(p.getCards());
    card.setUses(card.getMaxUses());
  }

  public void resetCardUses(List<Card> cards) {
    for (Card c : cards) {
      c.setUses(c.getMaxUses());
    }
  }

  public void addCardEventListener(CardEventListener cardEventListener) {
    eventListeners.add(cardEventListener);
  }
}
