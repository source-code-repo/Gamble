package gamble.clanbattle;

import gamble.Config;
import gamble.card.CardService;
import gamble.fight.Fight;
import gamble.fight.Fighter;
import gamble.fight.FightResult;
import gamble.player.Player;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class ClanBattle {
  private final List<ClanBattleEventListener> eventListeners = new ArrayList<>();
  private final Fight fight;
  private final CardService cardService;

  /**
   * Battle a clan of fighters. You will fight each fighter in a clan, one after another.
   * @param p Player
   * @param fighterCount Number of fighters in the clan
   * @return Result of the match
   */
  public ClanBattleResult battle(Player p, int fighterCount) {
    int fightNumber = 1;

    for (; fighterCount > 0; fighterCount--) {
      int finalFightNumber = fightNumber;
      int finalFighterCount = fighterCount;
      eventListeners.forEach(e -> e.fightStarting(finalFightNumber, finalFighterCount));
      Fighter fighter = fight.createFighter(
        Config.MIN_FIGHTER_HP,
        Config.MAX_FIGHTER_HP);

      FightResult result = fight.fight(fighter, p);

      if (!result.isWon()) {
        eventListeners.forEach(ClanBattleEventListener::clanBattleLost);
        return new ClanBattleResult(false);
      } else {
        fightNumber++;
      }

      if (result.isExactHit()) {
        eventListeners.forEach(listener -> listener.exactHit(p));
        cardService.rechargeCard(p);
      }
    }
    return new ClanBattleResult(true);
  }

  public void addClanBattleEventListener(ClanBattleEventListener clanBattleEventListener) {
    eventListeners.add(clanBattleEventListener);
  }
}
