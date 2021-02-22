package gamble;

public class Config {
  public static final int MIN_CPU_CARD_VALUE = 15;
  public static final int MAX_CPU_CARD_VALUE = 20;
  public static final int DELAY = 500;

  // Rewards for each match
  public static final int[] REWARDS = {1, 2, 3, 3, 3, 3, 4, 5, 6, 7,
    7, 7, 8, 9, 9};

  // Number of fighters in each clan
  public static final int[] FIGHTERS_PER_CLAN = {1, 2, 2, 2, 3, 2, 2, 4, 3, 2,
    3, 4, 4, 4, 4};

  public static final String[] NUM_TO_NAME = {"first", "second", "third", "fourth", "fifth",
    "sixth", "seventh", "eigth", "ninth", "tenth",
    "eleventh", "twelfth", "thirteenth", "fourteenth",
    "fifteenth"};

  public static final int VILLAGE_UNLOCKED_AFTER = 1;
  public static int targetGold = 66;
}
