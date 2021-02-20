package gamble.round;

import gamble.card.CardInputter;
import gamble.card.Card;

import java.util.List;
import java.util.Scanner;

public class RoundConsoleInputter implements RoundInputter {

    private final RoundOutputter roundOut;
    private final CardInputter cardInputter;

    Scanner reader = new Scanner(System.in);

    public RoundConsoleInputter(RoundOutputter roundOut, CardInputter cardInputter) {
        this.roundOut = roundOut;
        this.cardInputter = cardInputter;
    }

    @Override
    public Card selectAvailableCard(List<Card> cards) {
        Card pc = null;
        boolean cardChosen = false;
        while(!cardChosen) {
            roundOut.chooseCard();

            pc = cardInputter.selectCard(cards);

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
}
