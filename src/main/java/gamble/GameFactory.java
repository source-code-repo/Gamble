package gamble;

import gamble.card.CardConsoleInputter;
import gamble.card.CardConsoleOutputter;
import gamble.card.CardInputter;
import gamble.card.CardService;
import gamble.game.GameConsoleOutputter;
import gamble.game.GameEventListener;
import gamble.game.GameService;
import gamble.match.MatchConsoleOutputter;
import gamble.match.MatchService;
import gamble.player.PlayerService;
import gamble.round.RoundConsoleOutputter;
import gamble.round.RoundService;
import gamble.village.*;

/**
 * Possible improvement: DI framework/library
 */
public class GameFactory {
  public static GameService create() {

    CardConsoleOutputter cardOut = new CardConsoleOutputter();
    CardInputter cardIn = new CardConsoleInputter(cardOut);
    CardService cardService = new CardService(cardIn);
    cardService.addCardEventListener(cardOut);

    RoundConsoleOutputter roundOut = new RoundConsoleOutputter(cardOut);
    RoundService roundService = new RoundService(cardService, cardIn);
    roundService.addRoundEventListener(roundOut);

    MatchConsoleOutputter matchOut = new MatchConsoleOutputter(cardService, cardOut);
    MatchService matchService = new MatchService(roundService, cardService);
    matchService.addMatchEventListener(matchOut);

    PlayerService playerService = new PlayerService();

    VillageEventListener villageOut = new VillageConsoleOutputter();
    VillageInputter villageIn = new VillageConsoleInputter();
    VillageService villageService = new VillageService(villageIn, playerService);
    villageService.addEventListener(villageOut);

    GameEventListener gameOut = new GameConsoleOutputter();
    return new GameService(gameOut, matchService, villageService);
  }
}
