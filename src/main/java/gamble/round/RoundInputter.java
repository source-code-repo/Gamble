package gamble.round;

import gamble.entities.PlayerCard;

import java.util.List;

public interface RoundInputter {

    /**
     * Selects a card with uses left
     */
    PlayerCard selectAvailableCard(List<PlayerCard> cards);
}
