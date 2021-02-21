package gamble.village;

import gamble.Config;
import gamble.Inputter;
import gamble.card.CardService;
import gamble.player.Player;

import java.util.ArrayList;
import java.util.List;

public class VillageService {
  private final List<VillageEventListener> eventListeners = new ArrayList<>();
  private final Inputter input;
  private final CardService cardService;

  public VillageService(Inputter input, CardService cardService) {
    this.input = input;
    this.cardService = cardService;
  }

  public void visit(int matchCount, Player p, int targetGold) {
    if (villageJustUnlocked(matchCount)) {
      eventListeners.forEach(VillageEventListener::firstVillageVisit);
    }

    if (!villageUnlocked(matchCount)) {
      return;
    }

    eventListeners.forEach(e -> e.decidingToVisit(Config.REWARDS[matchCount]));
    boolean visit = input.yesOrNo();
    if (!visit) {
      return;
    }

    eventListeners.forEach(VillageEventListener::travellingToVillage);
    villageVisit(matchCount, p, targetGold);
  }

  public void villageVisit(int matchCount, Player p, int targetGold) {
    boolean finished = false;

    cardService.resetCardUses(p.cards);
    eventListeners.forEach(VillageEventListener::cardsRecharged);
    p.multiplier = 1;

    eventListeners.forEach(VillageEventListener::lookingAtIdol);
    if (p.gold >= targetGold) {
      eventListeners.forEach(VillageEventListener::gameWon);
      System.exit(0);
    } else {
      eventListeners.forEach(e -> e.notWon(targetGold));
    }


    while (!finished) {
      eventListeners.forEach(e -> e.visiting(matchCount));
      eventListeners.forEach(VillageEventListener::optionToLeave);
      finished = input.yesOrNo();
    }

    eventListeners.forEach(VillageEventListener::backToBattle);
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
