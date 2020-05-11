package gamble.service;

import gamble.config.Config;
import gamble.entities.MatchResult;
import gamble.entities.Player;
import gamble.service.io.GameOutputter;

public class GameService {
    
    GameOutputter out;
    MatchService ms;
    VillageService vs;
	
	public GameService(GameOutputter out, MatchService ms, VillageService vs) {
        super();
        this.out = out;
        this.ms = ms;
        this.vs = vs;
    }

    public void play(Player p, int[] cardsPerMatch) {
		int matchCount = 1;
		
		out.intro();
		
		for(int cpuCards : cardsPerMatch) {
			out.nextMatch(matchCount, Config.REWARDS[matchCount - 1]);
			MatchResult mr = ms.play(p, cpuCards);
			if(mr.won) {
				int reward = Config.REWARDS[matchCount - 1] * p.multiplier;
	            p.multiplier++;
				out.beatOpponent(matchCount, reward, p.multiplier);
				p.gold += reward;
			} else {
				out.lost(matchCount);
			}
			out.gold(p.gold);
			vs.visit(matchCount, p, Config.targetGold);
			matchCount++;
		}
	}
}
