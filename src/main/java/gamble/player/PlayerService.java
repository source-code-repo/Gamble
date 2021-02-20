package gamble.player;

import java.util.List;

import gamble.entities.Player;
import gamble.entities.PlayerCard;

public class PlayerService {

	public Player setup() {
		Player p = new Player();

		p.cards.add(new PlayerCard(1, 3, 3));
		p.cards.add(new PlayerCard(1, 5, 3));
		p.cards.add(new PlayerCard(3, 4, 3));
		p.cards.add(new PlayerCard(4, 9, 3));
		
		return p;
	}

	public void resetCardUses(List<PlayerCard> cards) {
		for(PlayerCard c : cards) {
			c.uses = c.maxUses;
		}
	}

}
