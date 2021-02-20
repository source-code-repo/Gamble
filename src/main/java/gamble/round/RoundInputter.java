package gamble.round;

import gamble.card.Card;

import java.util.List;

public interface RoundInputter {

    /**
     * Selects a card with uses left
     */
    Card selectAvailableCard(List<Card> cards);
}
