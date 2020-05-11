package gamble.service.io;

import java.util.List;
import java.util.Scanner;

import gamble.entities.PlayerCard;

public class ConsoleInputter implements Inputter {

	private static final String YES_OR_NO = "(yes|no|YES|NO)";
	private static final String YES = "(yes|YES)";

	Scanner reader = new Scanner(System.in);
	RoundOutputter roundOut = new ConsoleRoundOutputter();
	
	/* (non-Javadoc)
     * @see gamble.service.Inputter#selectAvailableCard(java.util.List)
     */
	@Override
    public PlayerCard selectAvailableCard(List<PlayerCard> cards) {
		PlayerCard pc = null;
		boolean cardChosen = false;
		while(!cardChosen) {
			roundOut.chooseCard();
			
			pc = selectCard(cards);
			
			if(pc.uses == 0) {
				roundOut.cardUsedUp();
				cardChosen = false;
				pc = null;
			} else {
				cardChosen = true;
			}
		}
		return pc;
	}

	/* (non-Javadoc)
     * @see gamble.service.Inputter#selectCard(java.util.List)
     */
    @Override
    public PlayerCard selectCard(List<PlayerCard> cards) {
        PlayerCard pc = null;
        while(pc == null) {
        	try {
        		if(!reader.hasNextInt()) {
        			reader.nextLine();
        			roundOut.print("Sorry I don't understand :( Try again.");
        			continue;
        		}
        		int n = reader.nextInt();
        		pc = cards.get(n - 1);
        	} catch(Exception e) {
        		roundOut.print("Sorry I don't understand :( Try again.");
        	}
        }
        return pc;
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
