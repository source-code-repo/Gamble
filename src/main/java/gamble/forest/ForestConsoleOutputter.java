package gamble.forest;

import gamble.Config;
import gamble.Util;

public class ForestConsoleOutputter implements ForestEventListener {
  @Override
  public void matchStarted(int clanNum, int reward) {
    String str = String.format(
        "*******************************************************\n"
      + "You come face to face with fighting clan #%d\n"
      + "*******************************************************\n",
      clanNum);

    Util.print(str);
    str = String.format("Reward: %d gold", reward);
    Util.print(str);
    Util.pause(Config.DELAY * 3L);
  }

  @Override
  public void matchWon(int fighterClanNumber, int reward, int multiplier) {
    String str = String.format(
          "Well done! You beat fighting clan #%d.%n"
        + "You win %d gold (%d for the win with a %d multiplier).%n"
        + "Your multiplier increased to %d.",
      fighterClanNumber,
      reward, reward / (multiplier - 1), multiplier - 1,
      multiplier);
    Util.print(str);
    Util.pause(Config.DELAY * 2L);
  }

  /* (non-Javadoc)
   * @see gamble.service.output.GameOutputter#lost(int)
   */
  @Override
  public void matchLost(int fighterClanNumber) {
    String str = String.format(
      "Uh oh. You've been beaten by the fighting clan #%d",
      fighterClanNumber);
    Util.print(str);
    Util.pause(Config.DELAY * 2L);
  }

  /* (non-Javadoc)
   * @see gamble.service.output.GameOutputter#gold(int)
   */
  @Override
  public void rewardGiven(int gold) {
    String str = String.format("You have %d gold%n", gold);
    Util.print(str);
  }

  @Override
  public void gameStarted() {
    String str =
        "You are a warrior tasked by the king of the realm "
      + "to protect the treacherous dark forest. "
      + "You take your magic cards into the forest for "
      + "protection but it isn't long before you run into "
      + "a clan of highly skilled fighters.";
    Util.print(str);
    Util.pause(5000);
  }

  @Override
  public void goldLost(int lost, int gold) {
    String str = String.format("You have lost %d gold, you now have %d%n",
      lost, gold);
    Util.print(str);
  }
}