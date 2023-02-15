package gamble;

import gamble.card.CardConsoleInputter;
import gamble.card.CardConsoleOutputter;
import gamble.card.CardInputter;
import gamble.card.CardService;
import gamble.fight.FightConsoleOutputter;
import gamble.fight.Fight;
import gamble.gameloop.GameLoopConsoleOutputter;
import gamble.gameloop.GameLoopListener;
import gamble.gameloop.GameLoop;
import gamble.clanbattle.ClanBattleConsoleOutputter;
import gamble.clanbattle.ClanBattle;
import gamble.shop.*;
import gamble.shop.item.DamageBoost;
import gamble.shop.item.UpgradeConsoleOutputter;
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

  public static GameLoop createGameLoop() {
    Inputter inputter = new ConsoleInputter();

    CardConsoleOutputter cardOut = new CardConsoleOutputter();
    CardInputter cardIn = new CardConsoleInputter(cardOut);
    CardService cardService = new CardService(cardIn);
    cardService.addCardEventListener(cardOut);

    Fight fight = new Fight(cardService, cardIn);
    fight.addFightEventListener(new FightConsoleOutputter(cardOut));

    ClanBattle clanBattle = new ClanBattle(fight, cardService);
    clanBattle.addClanBattleEventListener(new ClanBattleConsoleOutputter(cardOut));

    VillageService villageService = new VillageService(inputter, cardService);
    villageService.addEventListener(new VillageConsoleOutputter());

    ShopConsoleOutputter shopConsoleOutputter = new ShopConsoleOutputter();
    ShopInputter shopInputter = new ShopConsoleInputter(inputter, shopConsoleOutputter);
    ShopService shopService = new ShopService(shopInputter);
    shopService.addEventListener(shopConsoleOutputter);

    UpgradeConsoleOutputter upgradeConsoleOutputter = new UpgradeConsoleOutputter(cardOut);
    DamageBoost damageBoost = new DamageBoost(cardIn, upgradeConsoleOutputter);
    RangeReducer rangeReducer = new RangeReducer(cardIn, upgradeConsoleOutputter);
    shopService.setItems(List.of(damageBoost, rangeReducer));

    // Trigger a shop visit when a village visit event occurs
    ShopVillageTrigger shopVillageTrigger = new ShopVillageTrigger(shopService);
    villageService.addEventListener(shopVillageTrigger);

    GameLoopListener gameLoopListener = new GameLoopConsoleOutputter();
    return new GameLoop(gameLoopListener, clanBattle, villageService);
  }
}