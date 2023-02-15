package gamble.fight;

import gamble.card.Card;

import java.util.List;

/**
 * Events that occur during a round
 */
public interface FightEventListener {
  void fighterShowingHp(int target, int playerTotal, List<Card> cards);
  void playerChoosingCard(List<Card> cards);
  void chosenEmptyCard();
  void playerAttacking(int value);
  void roundOver();
}