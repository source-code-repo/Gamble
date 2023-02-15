package gamble.forest;

public interface GameLoopListener {
  void clanBattleStarting(int clanNumber, int reward);
  void clanBattleWon(int clanNumber, int reward, int multiplier);
  void clanBattleLost(int clanNumber);
  void rewardGiven(int gold);
  void gameLoopStarted();
  void goldLost(int goldLost, int goldLeft);
}