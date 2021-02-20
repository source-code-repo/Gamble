package gamble.service.input;

import java.util.List;
import java.util.Scanner;

import gamble.Util;
import gamble.entities.PlayerCard;
import gamble.round.ConsoleRoundOutputter;
import gamble.round.RoundOutputter;

public class ConsoleInputter implements Inputter {

	private static final String YES_OR_NO = "(yes|no|YES|NO)";
	private static final String YES = "(yes|YES)";

	Scanner reader = new Scanner(System.in);
	RoundOutputter roundOut;

	public ConsoleInputter(RoundOutputter roundOut) {
		this.roundOut = roundOut;
	}

	/* (non-Javadoc)
     * @see gamble.service.Inputter#pause()
     */
	@Override
    public void pause() {
		roundOut.print("Press enter to continue ");
		reader.nextLine();
		
	}

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
		while(!response) {
			input= reader.nextLine();
			if(input.matches(YES_OR_NO)) {
				response = true;
			} else {
				Util.print("Sorry, what was that? Maybe you need to give it another try.");
			}
		}
		
		if(input.matches(YES)) {
			return true;
		} else {
			return false;
		}
	}
}
