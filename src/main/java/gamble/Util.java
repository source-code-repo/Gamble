package gamble;

public class Util {
  public static void pause(long waitTime) {
    try {
      Thread.sleep(waitTime);
    } catch (InterruptedException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  public static void print(String str) {
    System.out.println(str);
    pause(Config.DELAY);
  }

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
}
