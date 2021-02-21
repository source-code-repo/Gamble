package gamble.forest;

import gamble.Config;
import gamble.match.MatchResult;
import gamble.match.MatchService;
import gamble.player.Player;
import gamble.village.VillageService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ForestService {
  public static final int LOSE_PENALTY = 5;
  private final ForestEventListener listener;
  private final MatchService ms;
  private final VillageService vs;

  /**
   * Fight a set of clans repeatedly
   * @param p Player
   * @param fightersPerClan Mapping of fighters to clan (array index = clan #)
   */
  public void play(Player p, int[] fightersPerClan) {
    // Which number fighting clan the player is up against
    int clanNumber = 1;

    listener.gameStarted();

    for (int fighters : fightersPerClan) {
      listener.matchStarted(clanNumber, Config.REWARDS[clanNumber - 1]);
      MatchResult mr = ms.play(p, fighters);
      if (mr.isWon()) {
        p.setMultiplier(p.getMultiplier() + 1);
        int reward = Config.REWARDS[clanNumber - 1] * p.getMultiplier();
        p.setGold(p.getGold() + reward);
        listener.matchWon(clanNumber, reward, p.getMultiplier());
        clanNumber++;
      } else {
        listener.matchLost(clanNumber);
        if(p.getGold() > LOSE_PENALTY * 2L) {
          p.setGold(p.getGold() - LOSE_PENALTY);
          listener.goldLost(LOSE_PENALTY, p.getGold());
        }
      }
      listener.rewardGiven(p.getGold());
      if(vs.willVisit(clanNumber)) {
        vs.villageVisit(clanNumber, p, Config.targetGold);
      }
    }
  }
}