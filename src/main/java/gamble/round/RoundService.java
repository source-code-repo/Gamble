package gamble.round;

import gamble.card.CardEventListener;
import gamble.card.CardService;
import gamble.Config;
import gamble.player.Player;
import gamble.card.Card;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Plays a round - the player has to beat one CPU card.
 */
public class RoundService {

	final RoundInputter in;
	final CardService cs;
	final List<RoundEventListener> eventListeners = new ArrayList<RoundEventListener>();
	
	public RoundService(CardEventListener cardOut, RoundInputter in, CardService cs) {
        this.in = in;
        this.cs = cs;
    }

    public Round createRound() {
		Round r = new Round();
		r.opponentCardTarget = randomCardValue();
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

			eventListeners.forEach(e ->
					e.selectNextCard(r.opponentCardTarget, r.playerTotal, p.cards));

			// TODO refactor input similar to output?
			Card pc = in.selectAvailableCard(p.cards);
			pc.uses--;
			int value = pc.getValue();

			eventListeners.forEach(e -> e.playerPlayingCard(value));
			
			r.playerTotal += value;
			
			if(r.playerTotal == r.opponentCardTarget) {
				eventListeners.forEach(e -> e.exactMatch());
				eventListeners.forEach(e -> e.roundOver());
				return new RoundResult(true, true);
			}
			
			if(r.playerTotal >= r.opponentCardTarget) {
				eventListeners.forEach(e -> e.roundOver());
				return new RoundResult(false, true);
			}
		}
	}

	public void addRoundEventListener(RoundEventListener roundEventListener) {
		eventListeners.add(roundEventListener);
	}
}
