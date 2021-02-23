package gamble;

public class Config {
  public static final int MIN_FIGHTER_HP = 13;
  public static final int MAX_FIGHTER_HP = 15;
  public static final int DELAY = 400;

  // Rewards for each match
  public static final int[] REWARDS = {1, 1, 1, 2, 2, 2, 3, 3, 3, 3, 4, 4, 4,
    4, 4, 4, 4, 4, 5, 5, 5, 5};

  // Number of fighters in each clan
  public static final int[] FIGHTERS_PER_CLAN = {1, 1, 1, 2, 2, 2, 3, 3, 3, 3,
    4, 4, 4, 4, 4, 4, 4, 4, 5, 5, 5, 5};

  public static final String[] NUM_TO_NAME = {"first", "second", "third",
    "fourth", "fifth", "sixth", "seventh", "eighth", "ninth", "tenth",
    "eleventh", "twelfth", "thirteenth", "fourteenth",
    "fifteenth"};

  public static final int VILLAGE_UNLOCKED_AFTER = 1;
  public static int targetGold = 66;
}