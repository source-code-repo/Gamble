package gamble;

import gamble.service.CardService;
import gamble.service.GameService;
import gamble.service.MatchService;
import gamble.service.PlayerService;
import gamble.service.RoundService;
import gamble.service.VillageService;
import gamble.service.io.CardOutputter;
import gamble.service.io.ConsoleCardOutputter;
import gamble.service.io.ConsoleGameOutputter;
import gamble.service.io.ConsoleInputter;
import gamble.service.io.ConsoleMatchOutputter;
import gamble.service.io.ConsoleRoundOutputter;
import gamble.service.io.ConsoleVillageOutputter;
import gamble.service.io.GameOutputter;
import gamble.service.io.Inputter;
import gamble.service.io.MatchOutputter;
import gamble.service.io.RoundOutputter;
import gamble.service.io.VillageOutputter;

public class GameFactory {
    public static GameService create() {

        Inputter in = new ConsoleInputter();
        
        GameOutputter out = new ConsoleGameOutputter();
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
