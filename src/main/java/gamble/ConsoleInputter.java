package gamble;

import java.util.Scanner;
import java.util.function.Predicate;

public class ConsoleInputter implements Inputter {
  private static final String YES_OR_NO = "(yes|no|YES|NO)";
  private static final String YES = "(yes|YES)";
  private static final String NUMBERS = "[0-9]+";

  Scanner reader = new Scanner(System.in);

  /* (non-Javadoc)
   * @see gamble.service.Inputter#yesOrNo()
   */
  @Override
  public boolean yesOrNo() {
    var input = getInput(e -> e.matches(YES_OR_NO));
    return input.matches(YES);
  }

  @Override
  public int chooseNumber() {
    return Integer.parseInt(
      getInput(e -> e.matches(NUMBERS))
    );
  }

  private String getInput(Predicate<String> validation) {
    String input = null;
    // Hack: without this, the reader next line fires without user input
    // in Eclipse's console
    reader = new Scanner(System.in);
    for(;;) {
      input = reader.nextLine();
      if (validation.test(input)) {
        return input;
      } else {
        Util.print("Sorry, what was that? Maybe give it another try.");
      }
    }
  }
}