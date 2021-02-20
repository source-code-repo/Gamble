package gamble.card;

import gamble.Config;
import gamble.player.Player;

import java.util.ArrayList;
import java.util.List;

public class CardService {
    
    private CardInputter in;
    private List<CardEventListener> eventListeners = new ArrayList<>();

	public CardService(CardInputter cardInputter) {
        this.in = cardInputter;
    }

    public boolean movesLeft(List<Card> cards) {
		for(Card pc : cards) {
			if(pc.uses > 0) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Convert number to name e.g. 2 = second, 3 = third etc
	 * @param num integer number to convert
	 * @return String string representation e.g. second, third
	 */
	public String numToName(int num) {
		return Config.NUM_TO_NAME[num - 1];
	}

	/**
	 * Allows the player to recharge a single card
	 */
    public void rechargeCard(Player p) {
        eventListeners.forEach(e -> e.rechargingCards());
        Card card = in.selectCard(p.cards);
        card.uses = card.maxUses;
    }

    public void addCardEventListener(CardEventListener cardEventListener) {
    	eventListeners.add(cardEventListener);
	}
}
