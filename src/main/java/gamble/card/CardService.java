package gamble.card;

import java.util.List;

import gamble.Config;
import gamble.player.Player;

public class CardService {
    
    private CardInputter in;
    private CardEventListener co;

	public CardService(CardInputter cardInputter, CardEventListener co) {
        super();
        this.in = cardInputter;
        this.co = co;
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
        co.rechargingCards();
        Card card = in.selectCard(p.cards);
        card.uses = card.maxUses;
    }

}
