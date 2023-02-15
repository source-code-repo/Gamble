package gamble.gameloop;

import gamble.Config;
import gamble.clanbattle.ClanBattleResult;
import gamble.clanbattle.ClanBattle;
import gamble.player.Player;
import gamble.village.VillageService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GameLoop {
  public static final int LOSE_PENALTY = 5;
  private final GameLoopListener listener;
  private final ClanBattle clanBattle;
  private final VillageService vs;

  /**
   * Battle one clan after another.
   * Each time a clan is beaten, reward the player with gold.
   * In between battling each clan, offer a visit to the village.
   * @param p Player
   * @param fightersPerClan A map of the number of fighters in each clan
   *                        [0] => number of fighters in the first clan
   *                        [1] => number of fighters in the second clan etc
   */
  public void play(Player p, int[] fightersPerClan) {
    // The current clan the player is up against
    int clanNumber = 1;

    listener.gameLoopStarted();

    for (int fighters : fightersPerClan) {
      listener.clanBattleStarting(clanNumber, Config.REWARDS[clanNumber - 1]);
      ClanBattleResult result = clanBattle.battle(p, fighters);
      if (result.isWon()) {
        int reward = Config.REWARDS[clanNumber - 1] * p.getMultiplier();
        p.setGold(p.getGold() + reward);
        p.setMultiplier(p.getMultiplier() + 1);
        listener.clanBattleWon(clanNumber, reward, p.getMultiplier());
        clanNumber++;
      } else {
        listener.clanBattleLost(clanNumber);
        // Reduce the player's gold by LOSE_PENALTY
        // But only if they have double the amount of LOSE_PENALTY gold to lose.
        if(p.getGold() > LOSE_PENALTY * 2L) {
          p.setGold(p.getGold() - LOSE_PENALTY);
          listener.goldLost(LOSE_PENALTY, p.getGold());
        }
      }
      listener.rewardGiven(p.getGold());
      if(vs.willVisit(clanNumber)) {
        vs.villageVisit(clanNumber, p, Config.getTargetGold());
      }
    }
  }
}