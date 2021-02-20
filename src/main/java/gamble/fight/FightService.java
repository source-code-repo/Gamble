package gamble.fight;

import gamble.Util;
import gamble.card.Card;
import gamble.card.CardInputter;
import gamble.card.CardService;
import gamble.player.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Plays a round - the player has to beat one CPU card.
 */
public class FightService {
  final CardService cs;
  final List<FightEventListener> eventListeners = new ArrayList<>();
  private final CardInputter cardInputter;

  public FightService(CardService cs, CardInputter cardInputter) {
    this.cs = cs;
    this.cardInputter = cardInputter;
  }

  /**
   * Creates a fighter with a random HP
   * @param min Fighter's minimum possible HP
   * @param max Fighter's maximum possible HP
   * @return
   */
  public Fighter createFighter(int min, int max) {
    Fighter r = new Fighter();
    r.maxHp = Util.randomBetween(min, max);
    r.damageTaken = 0;
    return r;
  }


  /**
   * Fight one fighter
   *
   * @param fighter The fighter to fight
   * @param p Player
   * @return Result, did the player win + get exact match?
   */
  public FightResult fight(Fighter fighter, Player p) {
    while (true) {
      if (!cs.movesLeft(p.cards)) {
        return new FightResult(false, false);
      }

      eventListeners.forEach(e ->
        e.fighterShowingHp(fighter.maxHp, fighter.damageTaken, p.cards));

      Card pc = chooseCard(p.cards);
      pc.uses--;
      int value = pc.getValue();

      eventListeners.forEach(e -> e.playerPlayingCard(value));

      fighter.damageTaken += value;

      if (fighter.damageTaken == fighter.maxHp) {
        eventListeners.forEach(e -> e.roundOver());
        return new FightResult(true, true);
      }

      if (fighter.damageTaken >= fighter.maxHp) {
        eventListeners.forEach(e -> e.roundOver());
        return new FightResult(false, true);
      }
    }
  }

  private Card chooseCard(List<Card> cards) {
    Card card = null;
    boolean cardChosen = false;
    while (!cardChosen) {
      eventListeners.forEach(e -> e.playerChoosingCard(cards));

      card = cardInputter.selectCard(cards);

      if (card.uses == 0) {
        eventListeners.forEach(e -> e.chosenEmptyCard());
        cardChosen = false;
      } else {
        cardChosen = true;
      }
    }
    return card;
  }

  public void addRoundEventListener(FightEventListener fightEventListener) {
    eventListeners.add(fightEventListener);
  }
}
