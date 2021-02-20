package gamble.game;

import gamble.config.Config;
import gamble.match.MatchResult;
import gamble.player.Player;
import gamble.match.MatchService;
import gamble.village.VillageService;

public class GameService {
    
    GameEventListener listener;
    MatchService ms;
    VillageService vs;
	
	public GameService(GameEventListener listener, MatchService ms, VillageService vs) {
        super();
        this.listener = listener;
        this.ms = ms;
        this.vs = vs;
    }

    public void play(Player p, int[] cardsPerMatch) {
		int matchCount = 1;
		
		listener.gameStarted();
		
		for(int cpuCards : cardsPerMatch) {
			listener.matchStarted(matchCount, Config.REWARDS[matchCount - 1]);
			MatchResult mr = ms.play(p, cpuCards);
			if(mr.won) {
				int reward = Config.REWARDS[matchCount - 1] * p.multiplier;
	            p.multiplier++;
				listener.matchWon(matchCount, reward, p.multiplier);
				p.gold += reward;
			} else {
				listener.matchLost(matchCount);
			}
			listener.rewardGiven(p.gold);
			vs.visit(matchCount, p, Config.targetGold);
			matchCount++;
		}
	}
}
