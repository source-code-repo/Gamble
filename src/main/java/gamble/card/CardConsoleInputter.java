package gamble.card;

import java.util.List;
import java.util.Scanner;

public class CardConsoleInputter implements CardInputter {

    private final Scanner reader = new Scanner(System.in);
    private final CardOutputter cardOutputter;

    public CardConsoleInputter(CardOutputter cardOutputter) {
        this.cardOutputter = cardOutputter;
    }

    @Override
    public Card selectCard(List<Card> cards) {
        Card pc = null;
        while(pc == null) {
            try {
                if(!reader.hasNextInt()) {
                    reader.nextLine();
                    cardOutputter.dontUnderstand();
                    continue;
                }
                int n = reader.nextInt();
                pc = cards.get(n - 1);
            } catch(Exception e) {
                cardOutputter.dontUnderstand();
            }
        }
        return pc;
    }
}
