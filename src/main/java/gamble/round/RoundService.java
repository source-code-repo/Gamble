package gamble.round;

import gamble.card.CardOutputter;
import gamble.card.CardService;
import gamble.config.Config;
import gamble.player.Player;
import gamble.card.Card;

import java.util.Random;

/**
 * Plays a round - the player has to beat one CPU card.
 */
public class RoundService {
	
    CardOutputter cardOut;
	RoundOutputter roundOut;
	RoundInputter in;
	CardService cs;
	
	public RoundService(CardOutputter cardOut, RoundOutputter roundOut, RoundInputter in, CardService cs) {
        super();
        this.cardOut = cardOut;
        this.roundOut = roundOut;
        this.in = in;
        this.cs = cs;
    }

    public Round createRound() {
		Round r = new Round();
		r.target = randomCardValue();
		r.playerTotal = 0;
		return r;
	}
	
	private int randomCardValue() {
		Random r = new Random();
		return r.nextInt(Config.MAX_CPU_CARD_VALUE) + 1;
	}
	
	/**
	 * Play a round, 1 CPU card
	 * @param r Round context
	 * @param p Player context
	 * @return Result, did the player win + get exact match?
	 */
	public RoundResult play(Round r, Player p) {
		while(true) {
			if(!cs.movesLeft(p.cards)) {
				return new RoundResult(false, false);
			}

			roundOut.showCpuCards(r.target, r.playerTotal);
			cardOut.showPlayerCards(p.cards);
			
			Card pc = in.selectAvailableCard(p.cards);
			pc.uses--;
			int value = pc.getValue();
			
			roundOut.playedCard(value);
			
			r.playerTotal += value;
			
			if(r.playerTotal == r.target) {
				roundOut.reward();
				roundOut.roundOver();
				return new RoundResult(true, true);
			}
			
			if(r.playerTotal >= r.target) {
				roundOut.roundOver();
				return new RoundResult(false, true);
			}
		}
	}
}
