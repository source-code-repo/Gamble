package gamble.village;

import gamble.player.Player;

/**
 * Notify of events that occur in the village
 * All methods have a blank default implementation
 * so implementors only need to extend events they
 * are interested in.
 */
public interface VillageEventListener {
  /**
   * Notify that a player is deciding to visit the village
   *
   * @param fightersInNextClan Number of fighters in the next clan
   */
  default void decidingToVisit(int fightersInNextClan) {}

  /**
   * Notify that the player is travelling to the village
   */
  default void travellingToVillage() {}

  /**
   * Notify that the player is visiting the village for the first time
   * VillageEventListener#visiting() will also be called
   */
  default void firstVillageVisit() {}

  /**
   * Notify that the player is visiting the village.
   * @param clanNumber The clan number the player has beaten
   * @param player The player
   */
  default void visiting(int clanNumber, Player player) {}

  /**
   * Notify that the player's cards have been recharged
   */
  default void cardsRecharged() {}

  /**
   * Notify that the player is heading back to battle
   */
  default void backToBattle() {}

  /**
   * Notify that the player is looking at the idon
   */
  default void lookingAtIdol() {}

  /**
   * Notify that the player has not yet won the game
   * @param targetGold Gold needed to win
   */
  default void notWon(int targetGold) {}

  /**
   * Notify that the player has won the game
   */
  default void gameWon() {}

  default void optionToLeave() {}
}