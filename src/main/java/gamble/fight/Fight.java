package gamble.fight;

import gamble.Util;
import gamble.card.Card;
import gamble.card.CardInputter;
import gamble.card.CardService;
import gamble.player.Player;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class Fight {
  final CardService cardService;
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
   * An individual fight between you and one fighter.
   * You repeatedly attack the fighter using your cards.
   * The fight is over when you win by reducing the fighter's hit points to 0.
   * Or until you have no more cards left to use.
   *
   * @param fighter The fighter to fight
   * @param p Player
   * @return Result, did the player win? Did they win by exact match?
   */
  public FightResult fight(Fighter fighter, Player p) {
    while (true) {
      if (!cardService.hasAttacksLeft(p.getCards())) {
        return new FightResult(false, false);
      }

      eventListeners.forEach(e ->
        e.fighterShowingHp(fighter.getMaxHp(), fighter.getDamageTaken(), p.getCards()));

      var card = chooseCard(p.getCards());
      card.setUses(card.getUses() - 1);
      int damage = card.getAttackDamage();

      eventListeners.forEach(e -> e.playerAttacking(damage));

      fighter.setDamageTaken(fighter.getDamageTaken() + damage);

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

      if (card.getUses() == 0) {
        eventListeners.forEach(FightEventListener::chosenEmptyCard);
      } else {
        cardChosen = true;
      }
    }
    return card;
  }

  public void addFightEventListener(FightEventListener fightEventListener) {
    eventListeners.add(fightEventListener);
  }
}
