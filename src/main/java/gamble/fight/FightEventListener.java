package gamble.fight;

import gamble.card.Card;

import java.util.List;

/**
 * Events that occur during a round
 */
public interface FightEventListener {
  /**
   * Notify that fighter is showing their HP
   *
   * @param target Fighter's total HP
   * @param playerTotal Figher's damage taken
   * @param cards All cards the player has
   */
  void fighterShowingHp(int target, int playerTotal, List<Card> cards);

  /**
   * Notify that the player is choosing their next card
   */
  void playerChoosingCard(List<Card> cards);

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
   * Notify that a round is over
   */
  void roundOver();
}