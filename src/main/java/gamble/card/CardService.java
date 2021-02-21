package gamble.card;

import gamble.player.Player;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class CardService {
  private final CardInputter in;
  private final List<CardEventListener> eventListeners = new ArrayList<>();

  public boolean movesLeft(List<Card> cards) {
    for (Card pc : cards) {
      if (pc.uses > 0) {
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
    card.uses = card.maxUses;
  }

  public void resetCardUses(List<Card> cards) {
    for (Card c : cards) {
      c.uses = c.maxUses;
    }
  }

  public void addCardEventListener(CardEventListener cardEventListener) {
    eventListeners.add(cardEventListener);
  }
}
