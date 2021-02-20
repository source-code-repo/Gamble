package gamble.round;

import gamble.card.Card;

import java.util.List;

/**
 * Events that occur during a round
 */
public interface RoundEventListener {
  /**
   * Notify that opponent is showing their current card
   *
   * @param target Opponent's card total value
   * @param playerTotal Total the player has scored so far (player total needs to equal opponent total to win)
   * @param cards All cards the player has
   */
  void opponentShowingCard(int target, int playerTotal, List<Card> cards);

  /**
   * Notify that the player is choosing their next card
   */
  void playerChoosingCard();

  /**
   * Notify that the player has chosen an empty card
   */
  void chosenEmptyCard();

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