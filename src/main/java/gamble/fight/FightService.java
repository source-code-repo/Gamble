package gamble.fight;

import gamble.Util;
import gamble.card.Card;
import gamble.card.CardInputter;
import gamble.card.CardService;
import gamble.player.Player;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * Plays a round - the player has to beat one CPU card.
 */
@RequiredArgsConstructor
public class FightService {
  final CardService cs;
  final List<FightEventListener> eventListeners = new ArrayList<>();
  private final CardInputter cardInputter;

  /**
   * Creates a fighter with a random HP
   * @param min Fighter's minimum possible HP
   * @param max Fighter's maximum possible HP
   * @return Fighter
   */
  public Fighter createFighter(int min, int max) {
    Fighter r = new Fighter();
    r.setMaxHp(Util.randomBetween(min, max));
    r.setDamageTaken(0);
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
      if (!cs.movesLeft(p.getCards())) {
        return new FightResult(false, false);
      }

      eventListeners.forEach(e ->
        e.fighterShowingHp(fighter.getMaxHp(), fighter.getDamageTaken(), p.getCards()));

      Card pc = chooseCard(p.getCards());
      pc.uses--;
      int value = pc.getValue();

      eventListeners.forEach(e -> e.playerPlayingCard(value));

      fighter.setDamageTaken(fighter.getDamageTaken() + value);

      if (fighter.getDamageTaken() == fighter.getMaxHp()) {
        eventListeners.forEach(FightEventListener::roundOver);
        return new FightResult(true, true);
      }

      if (fighter.getDamageTaken() >= fighter.getMaxHp()) {
        eventListeners.forEach(FightEventListener::roundOver);
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
        eventListeners.forEach(FightEventListener::chosenEmptyCard);
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
