package gamble.forest;

import gamble.Config;
import gamble.match.MatchResult;
import gamble.match.MatchService;
import gamble.player.Player;
import gamble.village.VillageService;

public class ForestService {
  ForestEventListener listener;
  MatchService ms;
  VillageService vs;

  public ForestService(ForestEventListener listener, MatchService ms, VillageService vs) {
    super();
    this.listener = listener;
    this.ms = ms;
    this.vs = vs;
  }

  public void play(Player p, int[] cardsPerMatch) {
    // Which number fighting clan the player is up against
    int clanNumber = 1;

    listener.gameStarted();

    for (int cpuCards : cardsPerMatch) {
      listener.matchStarted(clanNumber, Config.REWARDS[clanNumber - 1]);
      MatchResult mr = ms.play(p, cpuCards);
      if (mr.won) {
        int reward = Config.REWARDS[clanNumber - 1] * p.multiplier;
        p.multiplier++;
        listener.matchWon(clanNumber, reward, p.multiplier);
        p.gold += reward;
      } else {
        listener.matchLost(clanNumber);
      }
      listener.rewardGiven(p.gold);
      vs.visit(clanNumber, p, Config.targetGold);
      clanNumber++;
    }
  }
}