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
import gamble.village.VillageConsoleEventListener;
import gamble.game.GameEventListener;
import gamble.village.VillageInputter;
import gamble.village.VillageEventListener;

public class GameFactory {
    public static GameService create() {

        CardConsoleOutputter cardOut = new CardConsoleOutputter();
        CardInputter cardIn = new CardConsoleInputter(cardOut);
        CardService cardService = new CardService(cardIn);
        cardService.addCardEventListener(cardOut);

        RoundConsoleOutputter roundOut = new RoundConsoleOutputter(cardOut);
        RoundInputter roundIn = new RoundConsoleInputter(cardIn, roundOut);
        RoundService roundService = new RoundService(cardOut, roundIn, cardService);
        roundService.addRoundEventListener(roundOut);

        MatchConsoleOutputter matchOut = new MatchConsoleOutputter(cardService, cardOut);
        MatchService matchService = new MatchService(roundService);
        matchService.addMatchEventListener(matchOut);

        PlayerService playerService = new PlayerService();

        VillageEventListener villageOut = new VillageConsoleEventListener();
        VillageInputter villageIn = new VillageConsoleInputter();
        VillageService villageService = new VillageService(villageIn, playerService);
        villageService.addEventListener(villageOut);

        GameEventListener gameOut = new GameConsoleOutputter();
        return new GameService(gameOut, matchService, villageService);
    }
}
