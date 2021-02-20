package gamble.player;

import gamble.card.CardCreator;

public class PlayerService {
  private final CardCreator cardCreator;

  public PlayerService(CardCreator cardCreator) {
    this.cardCreator = cardCreator;
  }

  public Player setup() {
    Player p = new Player();
    p.cards = cardCreator.createCards();
    return p;
  }
}
