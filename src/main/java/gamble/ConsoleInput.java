package gamble;

import java.util.Scanner;
import java.util.function.Predicate;

public class ConsoleInput implements Input {
  private static final String YES_OR_NO = "(Yes|yes|YES|No|no|NO)";
  private static final String YES = "(yes|YES|Yes)";
  private static final String NUMBERS = "\\d+";
  public static final String BAD_INPUT = "Sorry, what was that? Maybe give it another try.";

  Scanner reader = new Scanner(System.in);

  /* (non-Javadoc)
   * @see gamble.service.Input#yesOrNo()
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
    String input;
    // Hack: without this, the reader next line fires without user input
    // in Eclipse's console
    reader = new Scanner(System.in);
    for(;;) {
      input = reader.nextLine();
      if (validation.test(input)) {
        return input;
      } else {
        Util.print(BAD_INPUT);
      }
    }
  }
}