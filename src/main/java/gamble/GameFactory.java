package gamble;

import gamble.card.CardConsoleInputter;
import gamble.card.CardConsoleOutputter;
import gamble.card.CardInputter;
import gamble.card.CardService;
import gamble.fight.FightConsoleOutputter;
import gamble.fight.FightService;
import gamble.forest.ForestConsoleOutputter;
import gamble.forest.ForestEventListener;
import gamble.forest.ForestService;
import gamble.match.MatchConsoleOutputter;
import gamble.match.MatchService;
import gamble.village.*;

/**
 * Possible improvement: DI framework/library
 */
public class GameFactory {
  public static ForestService create() {
    CardConsoleOutputter cardOut = new CardConsoleOutputter();
    CardInputter cardIn = new CardConsoleInputter(cardOut);
    CardService cardService = new CardService(cardIn);
    cardService.addCardEventListener(cardOut);

    FightConsoleOutputter roundOut = new FightConsoleOutputter(cardOut);
    FightService fightService = new FightService(cardService, cardIn);
    fightService.addRoundEventListener(roundOut);


    MatchConsoleOutputter matchOut = new MatchConsoleOutputter(cardService, cardOut);
    MatchService matchService = new MatchService(fightService, cardService);
    matchService.addMatchEventListener(matchOut);

    VillageEventListener villageOut = new VillageConsoleOutputter();
    VillageInputter villageIn = new VillageConsoleInputter();
    VillageService villageService = new VillageService(villageIn, cardService);
    villageService.addEventListener(villageOut);

    ForestEventListener gameOut = new ForestConsoleOutputter();
    return new ForestService(gameOut, matchService, villageService);
  }
}
