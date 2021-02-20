package gamble.forest;

public interface ForestEventListener {
  /**
   * Notify that a match has started
   * @param matchNum Match number
   * @param reward Reward to be given
   */
  void matchStarted(int matchNum, int reward);

  /**
   * Notify that a match was won
   * @param fighterClanNumber Which fighting clan the player beat
   * @param reward Reward for winning
   * @param multiplier New multiplier following the win
   */
  void matchWon(int fighterClanNumber, int reward, int multiplier);

  /**
   * Notify that a match was lost
   * @param fighterClanNumber Fighting clan number that was just defeated
   */
  void matchLost(int fighterClanNumber);

  /**
   * Notify that a reward was given to the player
   * @param gold Number of gold
   */
  void rewardGiven(int gold);

  /**
   * Notify that a new game was started
   */
  void gameStarted();

  /**
   * Player lost gold
   * @param lost Amount of gold lost
   * @param gold Total gold held
   */
  void goldLost(int lost, int gold);
}