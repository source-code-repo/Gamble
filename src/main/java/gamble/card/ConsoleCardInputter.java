package gamble.card;

import gamble.Util;
import gamble.entities.PlayerCard;
import gamble.round.RoundOutputter;

import java.util.List;
import java.util.Scanner;

public class ConsoleCardInputter implements CardInputter {

    private final Scanner reader = new Scanner(System.in);
    private final CardOutputter cardOutputter;

    public ConsoleCardInputter(CardOutputter cardOutputter) {
        this.cardOutputter = cardOutputter;
    }

    @Override
    public PlayerCard selectCard(List<PlayerCard> cards) {
        PlayerCard pc = null;
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
