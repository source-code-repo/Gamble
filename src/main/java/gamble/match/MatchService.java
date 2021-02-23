package gamble.match;

import gamble.Config;
import gamble.card.CardService;
import gamble.fight.FightService;
import gamble.fight.Fighter;
import gamble.fight.FightResult;
import gamble.player.Player;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * Plays a match against the CPU. A match has many rounds, one for each CPU card.
 */
@RequiredArgsConstructor
public class MatchService {
  private final List<MatchEventListener> eventListeners = new ArrayList<>();
  private final FightService fightService;
  private final CardService cardService;

  /**
   * Play a match against a clan of fighters
   * @param p Player
   * @param clanFighterCount Number of fighters in the clan
   * @return Result of the match
   */
  public MatchResult play(Player p, int clanFighterCount) {
    int roundNum = 1;

    for (; clanFighterCount > 0; clanFighterCount--) {
      int finalRoundNum = roundNum;
      int finalCpuCards = clanFighterCount;
      eventListeners.forEach(e -> e.roundStarted(finalRoundNum, finalCpuCards));
      Fighter fighter = fightService.createFighter(
        Config.MIN_FIGHTER_HP,
        Config.MAX_FIGHTER_HP);

      FightResult rr = fightService.fight(fighter, p);

      if (!rr.isWon()) {
        eventListeners.forEach(MatchEventListener::matchLost);
        return new MatchResult(false);
      } else {
        roundNum++;
      }

      if (rr.isExactHit()) {
        eventListeners.forEach(mel -> mel.exactHit(p));
        cardService.rechargeCard(p);
      }
    }
    return new MatchResult(true);
  }

  public void addMatchEventListener(MatchEventListener matchEventListener) {
    eventListeners.add(matchEventListener);
  }
}
