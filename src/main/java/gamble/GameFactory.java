package gamble;

import gamble.card.*;
import gamble.game.GameService;
import gamble.match.MatchService;
import gamble.player.PlayerService;
import gamble.round.*;
import gamble.village.VillageService;
import gamble.game.GameConsoleOutputter;
import gamble.village.VillageConsoleInputter;
import gamble.match.MatchConsoleOutputter;
import gamble.village.VillageConsoleOutputter;
import gamble.game.GameEventListener;
import gamble.village.VillageInputter;
import gamble.village.VillageOutputter;

public class GameFactory {
    public static GameService create() {

        GameEventListener out = new GameConsoleOutputter();

        CardConsoleOutputter cco = new CardConsoleOutputter();
        CardInputter ci = new CardConsoleInputter(cco);
        CardService cs = new CardService(ci, cco);

        RoundOutputter ro = new RoundConsoleOutputter();
        RoundInputter ri = new RoundConsoleInputter(ro, ci);
        RoundService rs = new RoundService(cco, ro, ri, cs);

        PlayerService ps = new PlayerService();

        MatchConsoleOutputter mco = new MatchConsoleOutputter(cs, cco);
        MatchService ms = new MatchService(rs);
        ms.addMatchEventListener(mco);

        VillageOutputter vos = new VillageConsoleOutputter();
        VillageInputter in = new VillageConsoleInputter(ro);
        VillageService vs = new VillageService(vos, in, ps);

        return new GameService(out, ms, vs);
    }
}
