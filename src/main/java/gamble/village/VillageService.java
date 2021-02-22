package gamble.village;

import gamble.Config;
import gamble.Inputter;
import gamble.card.CardService;
import gamble.player.Player;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class VillageService {
  private final List<VillageEventListener> listeners = new ArrayList<>();
  private final Inputter input;
  private final CardService cardService;

  /**
   * Will a player visit the village?
   * Player may not yet be allowed or may choose not to
   * @param clanNumber Most recently beaten by the player
   * @return Decision
   */
  public boolean willVisit(int clanNumber) {
    if (!villageUnlocked(clanNumber)) {
      return false;
    }

    if (villageJustUnlocked(clanNumber)) {
      listeners.forEach(VillageEventListener::firstVillageVisit);
    }

    listeners.forEach(e -> e.decidingToVisit(Config.FIGHTERS_PER_CLAN[clanNumber]));
    return input.yesOrNo();
  }

  public void villageVisit(int clansBeaten, Player player, int targetGold) {
    listeners.forEach(VillageEventListener::travellingToVillage);

    cardService.resetCardUses(player.getCards());
    listeners.forEach(VillageEventListener::cardsRecharged);
    player.setMultiplier(1);

    listeners.forEach(VillageEventListener::lookingAtIdol);
    if (player.getGold() >= targetGold) {
      listeners.forEach(VillageEventListener::gameWon);
      System.exit(0);
    } else {
      listeners.forEach(e -> e.notWon(targetGold));
    }

    boolean finished = false;
    while (!finished) {
      listeners.forEach(e -> e.visiting(clansBeaten, player));
      listeners.forEach(VillageEventListener::optionToLeave);
      finished = input.yesOrNo();
    }

    listeners.forEach(VillageEventListener::backToBattle);
  }

  private boolean villageJustUnlocked(int matchCount) {
    return matchCount == Config.VILLAGE_UNLOCKED_AFTER;
  }

  private boolean villageUnlocked(int matchCount) {
    return matchCount >= Config.VILLAGE_UNLOCKED_AFTER;
  }

  public void addEventListener(VillageEventListener villageEventListener) {
    listeners.add(villageEventListener);
  }
}
