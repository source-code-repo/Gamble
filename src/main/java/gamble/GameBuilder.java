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
import gamble.shop.*;
import gamble.shop.item.DamageBoost;
import gamble.shop.item.RangeReducer;
import gamble.village.VillageConsoleOutputter;
import gamble.village.VillageService;

import java.util.List;

/**
 * Possible improvement: DI framework/library
 */
public class GameBuilder {

  private GameBuilder() {
    throw new IllegalStateException("Not intended to be instantiated");
  }

  public static ForestService create() {
    Inputter inputter = new ConsoleInputter();

    CardConsoleOutputter cardOut = new CardConsoleOutputter();
    CardInputter cardIn = new CardConsoleInputter(cardOut);
    CardService cardService = new CardService(cardIn);
    cardService.addCardEventListener(cardOut);

    FightConsoleOutputter roundOut = new FightConsoleOutputter(cardOut);
    FightService fightService = new FightService(cardService, cardIn);
    fightService.addRoundEventListener(roundOut);

    MatchService matchService = new MatchService(fightService, cardService);
    matchService.addMatchEventListener(new MatchConsoleOutputter(cardOut));

    VillageService villageService = new VillageService(inputter, cardService);
    villageService.addEventListener(new VillageConsoleOutputter());

    ShopConsoleOutputter shopConsoleOutputter = new ShopConsoleOutputter();
    ShopInputter shopInputter = new ShopConsoleInputter(inputter, shopConsoleOutputter);
    ShopService shopService = new ShopService(shopInputter);
    shopService.addEventListener(shopConsoleOutputter);
    shopService.setItems(List.of(new DamageBoost(), new RangeReducer()));

    // Triggers a shop visit through the village visit event
    ShopVillageTrigger shopVillageTrigger = new ShopVillageTrigger(shopService);
    villageService.addEventListener(shopVillageTrigger);

    ForestEventListener gameOut = new ForestConsoleOutputter();
    return new ForestService(gameOut, matchService, villageService);
  }
}