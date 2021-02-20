package gamble.player;

import gamble.card.Card;

import java.util.List;

public class PlayerService {
  public Player setup() {
    Player p = new Player();

    p.cards.add(new Card(1, 3, 3));
    p.cards.add(new Card(1, 5, 3));
    p.cards.add(new Card(3, 4, 3));
    p.cards.add(new Card(4, 9, 3));

    return p;
  }

  public void resetCardUses(List<Card> cards) {
    for (Card c : cards) {
      c.uses = c.maxUses;
    }
  }
}
