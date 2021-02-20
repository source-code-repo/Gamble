package gamble.match;

import gamble.player.Player;
import gamble.round.Round;
import gamble.round.RoundResult;
import gamble.round.RoundService;
import gamble.card.CardService;
import gamble.card.CardOutputter;

/**
 * Plays a match against the CPU. A match has many rounds, one for each CPU card.
 */
public class MatchService {
    
	private RoundService rs;
	private CardService cs;
	private MatchOutputter mos;
	private CardOutputter co;
	
    public MatchService(RoundService rs, CardService cs, MatchOutputter mos, CardOutputter co) {
        super();
        this.rs = rs;
        this.cs = cs;
        this.mos = mos;
        this.co = co;
    }

    public MatchResult play(Player p, int cpuCards) {
		int roundNum = 1;
		
		for(; cpuCards > 0; cpuCards--) {
			
			Round r = rs.createRound();
			mos.nextRound(roundNum, cpuCards);
			
			RoundResult rr = rs.play(r,  p);
			
			if(!rr.won) {
				mos.gameOver();
				return new MatchResult(false);
			} else {
				roundNum++;
			}
			
			if(rr.exactMatch) {
			    mos.rechargeChard();
			    co.showPlayerCards(p.cards);
			    cs.rechargeCard(p);
			}
		}
		return new MatchResult(true);
	}
}
