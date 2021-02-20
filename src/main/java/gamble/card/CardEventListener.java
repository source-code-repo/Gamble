package gamble.card;

import java.util.List;

public interface CardEventListener {

    void showPlayerCards(List<Card> cards);

    void rechargingCards();

    void dontUnderstand();
}
