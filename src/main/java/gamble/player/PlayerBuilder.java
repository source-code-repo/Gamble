package gamble.player;

import gamble.card.CardCreator;

public class PlayerBuilder {
  private final CardCreator cardCreator;

  public PlayerBuilder(CardCreator cardCreator) {
    this.cardCreator = cardCreator;
  }

  public Player createPlayer() {
    Player p = new Player();
    p.setCards(cardCreator.createCards());
    return p;
  }
}
