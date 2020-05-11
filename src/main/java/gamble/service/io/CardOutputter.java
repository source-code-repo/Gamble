package gamble.service.io;

import java.util.List;

import gamble.entities.PlayerCard;

public interface CardOutputter {

    public void showPlayerCards(List<PlayerCard> cards);

    public void chooseCardToRecharge();
}
