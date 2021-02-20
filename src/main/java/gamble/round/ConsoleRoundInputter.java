package gamble.round;

import gamble.card.CardInputter;
import gamble.entities.PlayerCard;

import java.util.List;
import java.util.Scanner;

public class ConsoleRoundInputter implements RoundInputter {

    private final RoundOutputter roundOut;
    private final CardInputter cardInputter;

    Scanner reader = new Scanner(System.in);

    public ConsoleRoundInputter(RoundOutputter roundOut, CardInputter cardInputter) {
        this.roundOut = roundOut;
        this.cardInputter = cardInputter;
    }

    @Override
    public PlayerCard selectAvailableCard(List<PlayerCard> cards) {
        PlayerCard pc = null;
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
