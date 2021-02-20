package gamble.round;

import gamble.config.Config;
import gamble.Util;

/**
 * UI for round output
 */
public class ConsoleRoundOutputter implements RoundOutputter {

	/* (non-Javadoc)
     * @see gamble.service.output.RoundOutputter#showCpuCards(int, int)
     */
	@Override
    public void showCpuCards(int target, int totalPlayed) {
		String str = String.format("\nYour opponent's card has %01d/%01d points.\n", 
		        target - totalPlayed, target);
		print(str);
		Util.pause(Config.DELAY * 2);
	}
	
	/* (non-Javadoc)
     * @see gamble.service.output.RoundOutputter#print(java.lang.String)
     */
	@Override
    public void print(String s) {
		System.out.println(s);
	}

	/* (non-Javadoc)
     * @see gamble.service.output.RoundOutputter#reward()
     */
	@Override
    public void reward() {
		print("Spot on! Precise hit!");
	}

	/* (non-Javadoc)
     * @see gamble.service.output.RoundOutputter#roundOver()
     */
	@Override
    public void roundOver() {
		String str = String.format("\n*** You beat your opponent's card! ***\n");
		print(str);
		Util.pause(Config.DELAY * 3);
	}

	/* (non-Javadoc)
     * @see gamble.service.output.RoundOutputter#playedCard(int)
     */
	@Override
    public void playedCard(int value) {
		System.out.print("\nYour card's value is.");
		
		Util.pause(Config.DELAY);
		System.out.print(".");
		Util.pause(Config.DELAY);
		System.out.print(".");
		Util.pause(Config.DELAY);
		
		String str = String.format("%d", value);
		print(str);

		Util.pause(Config.DELAY * 2);
		
	}

	/* (non-Javadoc)
     * @see gamble.service.output.RoundOutputter#chooseCard()
     */
	@Override
    public void chooseCard() {
		System.out.println("Choose a card to play: ");
	}

	/* (non-Javadoc)
     * @see gamble.service.output.RoundOutputter#cardUsedUp()
     */
	@Override
    public void cardUsedUp() {
		print("Card has reached max uses, please select another card");
	}
}
