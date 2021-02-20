package gamble.card;

import gamble.entities.PlayerCard;

import java.util.List;

public interface CardInputter {
    PlayerCard selectCard(List<PlayerCard> cards);
}
