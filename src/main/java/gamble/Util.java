package gamble;

import java.util.Random;

public class Util {
  private static final Random r = new Random();

  private Util() {
    throw new IllegalStateException("Not intended to be instantiated");
  }

  public static void pause(long waitTime) {
    try {
      Thread.sleep(waitTime);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
      throw new IllegalStateException(e);
    }
  }

  @SuppressWarnings("java:S106") // Allow System.out.println as this is a console app
  public static void print(String str) {
    System.out.println(str);
    pause(Config.DELAY);
  }

  @SuppressWarnings("java:S106") // Allow System.out as this is a console app
  public static void printNoDelay(String s) {
    System.out.println(s);
  }

  /**
   * Convert number to name e.g. 2 = second, 3 = third etc
   *
   * @param num integer number to convert
   * @return String string representation e.g. second, third
   */
  public static String numToName(int num) {
    return Config.NUM_TO_NAME[num - 1];
  }

  /**
   * Produces a random value between min and max
   * @param min Minimum value
   * @param max Maximum value
   * @return Random value
   */
  public static int randomBetween(int min, int max) {
    int random = r.nextInt(max - min) + 1;
    return min + random;
  }
}