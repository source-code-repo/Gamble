package gamble.game;

public interface GameEventListener {
  /**
   * Notify that a match has started
   * @param matchNum Match number
   * @param reward Reward to be given
   */
  void matchStarted(int matchNum, int reward);

  /**
   * Notify that a match was won
   * @param opponentNum Opponent number
   * @param reward Reward for winning
   * @param multiplier New multiplier following the win
   */
  void matchWon(int opponentNum, int reward, int multiplier);

  /**
   * Notify that a match was lost
   * @param opponentNum Opponent number the match was lost to
   */
  void matchLost(int opponentNum);

  /**
   * Notify that a reward was given to the player
   * @param gold Number of gold
   */
  void rewardGiven(int gold);

  /**
   * Notify that a new game was started
   */
  void gameStarted();
}