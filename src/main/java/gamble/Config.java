package gamble;

public class Config {
  private Config() {
    throw new IllegalStateException("Utility class, not to be instantiated");
  }
  public static final int MIN_FIGHTER_HP = 10;
  public static final int MAX_FIGHTER_HP = 15;

  @SuppressWarnings("java:S2386") // Accept risk of mutable global state
  public static final int[] FIGHTERS_PER_CLAN = {1, 1, 1, 2, 2, 2, 3, 3, 3, 3,
    // Number of fighters in each clan
    4, 4, 4, 4, 4, 4, 4, 4, 5, 5, 5, 5};

  @SuppressWarnings("java:S2386") // Accept risk of mutable global state
  // Amount of gold rewarded for beating each clan
  public static final int[]  REWARDS = {1, 1, 1, 2, 2, 2, 3, 3, 3, 3, 4, 4, 4,
    4, 4, 4, 4, 4, 5, 5, 5, 5};

  @SuppressWarnings("java:S2386") // Accept risk of mutable global state
  public static final String[] NUM_TO_NAME = {"first", "second", "third",
    "fourth", "fifth", "sixth", "seventh", "eighth", "ninth", "tenth",
    "eleventh", "twelfth", "thirteenth", "fourteenth",
    "fifteenth"};

  // How many clan battles must be won before the village can be visited
  public static final int VILLAGE_UNLOCKED_AFTER = 1;

  // Gold needed to win the game
  private static int targetGold = 66;

  // Dramatic delay when printing text
  public static final int DELAY = 400;

  public static int getTargetGold() {
    return targetGold;
  }

  public static void setTargetGold(int targetGold) {
    Config.targetGold = targetGold;
  }
}