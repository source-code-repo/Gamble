package gamble.card;

import java.util.List;

public interface CardOutputter {

    public void showPlayerCards(List<Card> cards);

    public void chooseCardToRecharge();

    void dontUnderstand();
}
