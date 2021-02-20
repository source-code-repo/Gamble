package gamble;

import gamble.card.*;
import gamble.game.GameService;
import gamble.match.MatchService;
import gamble.player.PlayerService;
import gamble.round.*;
import gamble.village.VillageService;
import gamble.game.ConsoleGameEventListener;
import gamble.service.input.ConsoleInputter;
import gamble.match.ConsoleMatchOutputter;
import gamble.village.ConsoleVillageOutputter;
import gamble.game.GameEventListener;
import gamble.service.input.Inputter;
import gamble.match.MatchOutputter;
import gamble.village.VillageOutputter;

public class GameFactory {
    public static GameService create() {

        GameEventListener out = new ConsoleGameEventListener();

        CardOutputter co = new ConsoleCardOutputter();
        CardInputter ci = new ConsoleCardInputter(co);
        CardService cs = new CardService(ci, co);

        RoundOutputter ro = new ConsoleRoundOutputter();
        RoundInputter ri = new ConsoleRoundInputter(ro, ci);
        RoundService rs = new RoundService(co, ro, ri, cs);

        Inputter in = new ConsoleInputter(ro);

        PlayerService ps = new PlayerService();

        MatchOutputter mo = new ConsoleMatchOutputter(cs);
        MatchService ms = new MatchService(rs, cs, mo, co);

        VillageOutputter vos = new ConsoleVillageOutputter();
        VillageService vs = new VillageService(vos, in, ps);

        return new GameService(out, ms, vs);
    }
}
