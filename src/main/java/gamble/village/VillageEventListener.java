package gamble.village;

public interface VillageEventListener {
  /**
   * Notify that a player is deciding to visit the village
   * @param nextReward Reward the player would have
   */
  void decidingToVisit(int nextReward);

  /**
   * Notify that the player is travelling to the village
   */
  void travellingToVillage();

  /**
   * Notify that the player is visiting the village for the first time
   * VillageEventListener#visiting() will also be called
   */
  void firstVillageVisit();

  /**
   * Notify that the player is visiting the village.
   */
  void visiting();

  /**
   * Notify that the player's cards have been recharged
   */
  void cardsRecharged();

  /**
   * Notify that the player is heading back to battle
   */
  void backToBattle();

  /**
   * Notify that the player is looking at the idon
   */
  void lookingAtIdol();

  /**
   * Notify that the player has not yet won the game
   * @param targetGold Gold needed to win
   */
  void notWon(int targetGold);

  /**
   * Notify that the player has won the game
   */
  void gameWon();
}