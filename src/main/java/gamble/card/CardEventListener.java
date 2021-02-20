package gamble.card;

public interface CardEventListener {
  /**
   * Called when a player's cards are being recharged
   */
  void rechargingCards();

  /**
   * Called when the player gives a bad response to the card service
   */
  void badResponse();
}
