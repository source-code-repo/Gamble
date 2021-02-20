package gamble.village;

import gamble.Util;

import java.util.Scanner;

public class VillageConsoleInputter implements VillageInputter {
  private static final String YES_OR_NO = "(yes|no|YES|NO)";
  private static final String YES = "(yes|YES)";

  Scanner reader = new Scanner(System.in);

  /* (non-Javadoc)
   * @see gamble.service.Inputter#yesOrNo()
   */
  @Override
  public boolean yesOrNo() {
    boolean response = false;

    String input = null;
    // Hack: without this, the reader next line fires without user input
    // in Eclipse's console
    reader = new Scanner(System.in);
    while (!response) {
      input = reader.nextLine();
      if (input.matches(YES_OR_NO)) {
        response = true;
      } else {
        Util.print("Sorry, what was that? Maybe you need to give it another try.");
      }
    }

    if (input.matches(YES)) {
      return true;
    } else {
      return false;
    }
  }
}
