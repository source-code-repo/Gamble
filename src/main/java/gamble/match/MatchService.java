package gamble.match;

import gamble.card.CardService;
import gamble.player.Player;
import gamble.round.Round;
import gamble.round.RoundResult;
import gamble.round.RoundService;

import java.util.ArrayList;
import java.util.List;

/**
 * Plays a match against the CPU. A match has many rounds, one for each CPU card.
 */
public class MatchService {
  private final List<MatchEventListener> matchEventListeners = new ArrayList<MatchEventListener>();
  private final RoundService rs;
  private final CardService cs;

  public MatchService(RoundService rs, CardService cs) {
    this.rs = rs;
    this.cs = cs;
  }

  public MatchResult play(Player p, int cpuCards) {
    int roundNum = 1;

    for (; cpuCards > 0; cpuCards--) {

      int finalRoundNum = roundNum;
      int finalCpuCards = cpuCards;
      matchEventListeners.forEach(mel -> mel.roundStarted(finalRoundNum, finalCpuCards));
      Round r = rs.createRound();

      RoundResult rr = rs.play(r, p);

      if (!rr.won) {
        matchEventListeners.forEach(mel -> mel.matchLost());
        return new MatchResult(false);
      } else {
        roundNum++;
      }

      if (rr.exactHit) {
        matchEventListeners.forEach(mel -> mel.exactHit(p));
        cs.rechargeCard(p);
      }
    }
    return new MatchResult(true);
  }

  public void addMatchEventListener(MatchEventListener matchEventListener) {
    matchEventListeners.add(matchEventListener);
  }
}
