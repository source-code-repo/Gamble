package gamble;

import gamble.card.CardService;
import gamble.game.GameService;
import gamble.match.MatchService;
import gamble.player.PlayerService;
import gamble.round.RoundService;
import gamble.village.VillageService;
import gamble.card.CardOutputter;
import gamble.card.ConsoleCardOutputter;
import gamble.game.ConsoleGameEventListener;
import gamble.service.input.ConsoleInputter;
import gamble.match.ConsoleMatchOutputter;
import gamble.round.ConsoleRoundOutputter;
import gamble.village.ConsoleVillageOutputter;
import gamble.game.GameEventListener;
import gamble.service.input.Inputter;
import gamble.match.MatchOutputter;
import gamble.round.RoundOutputter;
import gamble.village.VillageOutputter;

public class GameFactory {
    public static GameService create() {

        Inputter in = new ConsoleInputter();
        
        GameEventListener out = new ConsoleGameEventListener();
        CardOutputter co = new ConsoleCardOutputter();
        VillageOutputter vos = new ConsoleVillageOutputter();
        RoundOutputter ro = new ConsoleRoundOutputter();
        
        PlayerService ps = new PlayerService();
        CardService cs = new CardService(in, co);
        MatchOutputter mo = new ConsoleMatchOutputter(cs);
        RoundService rs = new RoundService(co, ro, in, cs);
        MatchService ms = new MatchService(rs, cs, mo, co);        
        VillageService vs = new VillageService(vos, in, ps);
        
        return new GameService(out, ms, vs);
    }
}
