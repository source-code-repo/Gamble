package gamble.village;

import gamble.Config;
import gamble.player.Player;
import gamble.player.PlayerService;

import java.util.ArrayList;
import java.util.List;

public class VillageService {
  private final List<VillageEventListener> eventListeners = new ArrayList<>();
  private final VillageInputter input;
  private final PlayerService playerService;

  public VillageService(VillageInputter input, PlayerService playerService) {
    this.input = input;
    this.playerService = playerService;
  }

  public void visit(int matchCount, Player p, int targetGold) {
    if (villageJustUnlocked(matchCount)) {
      eventListeners.forEach(e -> e.firstVillageVisit());
    }

    if (!villageUnlocked(matchCount)) {
      return;
    }

    eventListeners.forEach(e -> e.decidingToVisit(Config.REWARDS[matchCount]));
    boolean visit = input.yesOrNo();
    if (!visit) {
      return;
    }

    villageVisit(p, targetGold);
  }

  public void villageVisit(Player p, int targetGold) {
    boolean finished = false;

    eventListeners.forEach(e -> e.travellingToVillage());
    playerService.resetCardUses(p.cards);
    eventListeners.forEach(e -> e.cardsRecharged());
    p.multiplier = 1;

    eventListeners.forEach(e -> e.lookingAtIdol());
    if (p.gold >= targetGold) {
      eventListeners.forEach(e -> e.gameWon());
      System.exit(0);
    } else {
      eventListeners.forEach(e -> e.notWon(targetGold));
    }


    while (!finished) {
      eventListeners.forEach(e -> e.visiting());
      finished = input.yesOrNo();
    }

    eventListeners.forEach(e -> e.backToBattle());
  }

  private boolean villageJustUnlocked(int matchCount) {
    return matchCount == Config.VILLAGE_UNLOCKED_AFTER;
  }

  private boolean villageUnlocked(int matchCount) {
    return matchCount >= Config.VILLAGE_UNLOCKED_AFTER;
  }

  public void addEventListener(VillageEventListener villageEventListener) {
    eventListeners.add(villageEventListener);
  }
}
