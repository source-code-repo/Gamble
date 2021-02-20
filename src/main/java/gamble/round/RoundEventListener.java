package gamble.round;

import gamble.card.Card;

import java.util.List;

/**
 * Events that occur during a round
 */
public interface RoundEventListener {
  /**
   * Notify that the player has chosen an empty card
   */
  void chosenEmptyCard();

  /**
   * Notify that the player needs to select the next card
   *
   * @param target
   * @param playerTotal
   * @param cards
   */
  void selectNextCard(int target, int playerTotal, List<Card> cards);

  /**
   * Notify that the player is playing their next card
   *
   * @param value
   */
  void playerPlayingCard(int value);

  /**
   * Player won a round with an exact match
   */
  void exactHit();

  /**
   * Notify that a round is over
   */
  void roundOver();
}