package gamble.match;

import gamble.Config;
import gamble.card.CardService;
import gamble.fight.FightService;
import gamble.fight.Fighter;
import gamble.fight.FightResult;
import gamble.player.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Plays a match against the CPU. A match has many rounds, one for each CPU card.
 */
public class MatchService {
  private final List<MatchEventListener> matchEventListeners = new ArrayList<MatchEventListener>();
  private final FightService fightService;
  private final CardService cardService;

  public MatchService(FightService fightService, CardService cardService) {
    this.fightService = fightService;
    this.cardService = cardService;
  }

  public MatchResult play(Player p, int cpuCards) {
    int roundNum = 1;

    for (; cpuCards > 0; cpuCards--) {

      int finalRoundNum = roundNum;
      int finalCpuCards = cpuCards;
      matchEventListeners.forEach(mel -> mel.roundStarted(finalRoundNum, finalCpuCards));
      Fighter fighter = fightService.createFighter(
        Config.MIN_CPU_CARD_VALUE,
        Config.MAX_CPU_CARD_VALUE);

      FightResult rr = fightService.fight(fighter, p);

      if (!rr.won) {
        matchEventListeners.forEach(mel -> mel.matchLost());
        return new MatchResult(false);
      } else {
        roundNum++;
      }

      if (rr.exactHit) {
        matchEventListeners.forEach(mel -> mel.exactHit(p));
        cardService.rechargeCard(p);
      }
    }
    return new MatchResult(true);
  }

  public void addMatchEventListener(MatchEventListener matchEventListener) {
    matchEventListeners.add(matchEventListener);
  }
}
