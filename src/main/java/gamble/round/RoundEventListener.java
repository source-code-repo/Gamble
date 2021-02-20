package gamble.round;

import gamble.card.Card;

import java.util.List;

/**
 * Events that occur during a round
 */
public interface RoundEventListener {
    /**
     * Player is choosing a card
     */
    //void choosingCard();

    /**
     * Player has chosen an empty card
     */
    void chosenEmptyCard();

    /**
     * The player needs to select the next card
     * @param target
     * @param playerTotal
     * @param cards
     */
    void selectNextCard(int target, int playerTotal, List<Card> cards);

    /**
     * Player is playing their next card
     * @param value
     */
    void playerPlayingCard(int value);

    /**
     * Player won a round with an exact match
     */
    void exactMatch();

    void roundOver();
}