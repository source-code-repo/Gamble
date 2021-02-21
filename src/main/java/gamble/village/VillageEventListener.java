package gamble.village;

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
   * @param nextReward Reward the player would have
   */
  default void decidingToVisit(int nextReward) {}

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
   * @param matchCount
   */
  default void visiting(int matchCount) {}

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