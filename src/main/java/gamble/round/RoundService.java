package gamble.round;

import gamble.Config;
import gamble.card.*;
import gamble.player.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Plays a round - the player has to beat one CPU card.
 */
public class RoundService {
  final CardService cs;
  final List<RoundEventListener> eventListeners = new ArrayList<RoundEventListener>();
  private final CardInputter cardInputter;

  public RoundService(CardService cs, CardInputter cardInputter) {
    this.cs = cs;
    this.cardInputter = cardInputter;
  }

  public Round createRound() {
    Round r = new Round();
    r.opponentCardTarget = randomCardValue();
    r.playerTotal = 0;
    return r;
  }

  private int randomCardValue() {
    Random r = new Random();
    return r.nextInt(Config.MAX_CPU_CARD_VALUE) + 1;
  }

  /**
   * Play a round, 1 CPU card
   *
   * @param r Round context
   * @param p Player context
   * @return Result, did the player win + get exact match?
   */
  public RoundResult play(Round r, Player p) {
    while (true) {
      if (!cs.movesLeft(p.cards)) {
        return new RoundResult(false, false);
      }

      eventListeners.forEach(e ->
        e.opponentShowingCard(r.opponentCardTarget, r.playerTotal, p.cards));

      Card pc = chooseCard(p);
      pc.uses--;
      int value = pc.getValue();

      eventListeners.forEach(e -> e.playerPlayingCard(value));

      r.playerTotal += value;

      if (r.playerTotal == r.opponentCardTarget) {
        eventListeners.forEach(e -> e.exactHit());
        eventListeners.forEach(e -> e.roundOver());
        return new RoundResult(true, true);
      }

      if (r.playerTotal >= r.opponentCardTarget) {
        eventListeners.forEach(e -> e.roundOver());
        return new RoundResult(false, true);
      }
    }
  }

  private Card chooseCard(Player p) {
    Card card = null;
    boolean cardChosen = false;
    while (!cardChosen) {
      eventListeners.forEach(e -> e.playerChoosingCard());

      card = cardInputter.selectCard(p.cards);

      if (card.uses == 0) {
        eventListeners.forEach(e -> e.chosenEmptyCard());
        cardChosen = false;
      } else {
        cardChosen = true;
      }
    }
    return card;
  }

  public void addRoundEventListener(RoundEventListener roundEventListener) {
    eventListeners.add(roundEventListener);
  }
}
