package gamble;

import gamble.card.CardConsoleInput;
import gamble.card.CardConsoleOutput;
import gamble.card.CardInput;
import gamble.card.CardService;
import gamble.fight.FightConsoleOutput;
import gamble.fight.Fight;
import gamble.gameloop.GameLoopConsoleOutput;
import gamble.gameloop.GameLoopListener;
import gamble.gameloop.GameLoop;
import gamble.clanbattle.ClanBattleConsoleOutput;
import gamble.clanbattle.ClanBattle;
import gamble.shop.*;
import gamble.shop.item.DamageBoost;
import gamble.shop.item.UpgradeConsoleOutput;
import gamble.shop.item.RangeReducer;
import gamble.village.VillageConsoleOutput;
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
    Input input = new ConsoleInput();

    CardConsoleOutput cardOut = new CardConsoleOutput();
    CardInput cardIn = new CardConsoleInput(cardOut);
    CardService cardService = new CardService(cardIn);
    cardService.addCardEventListener(cardOut);

    Fight fight = new Fight(cardService, cardIn);
    fight.addFightEventListener(new FightConsoleOutput(cardOut));

    ClanBattle clanBattle = new ClanBattle(fight, cardService);
    clanBattle.addClanBattleEventListener(new ClanBattleConsoleOutput(cardOut));

    VillageService villageService = new VillageService(input, cardService);
    villageService.addEventListener(new VillageConsoleOutput());

    ShopConsoleOutput shopConsoleOutput = new ShopConsoleOutput();
    ShopInput shopInput = new ShopConsoleInput(input, shopConsoleOutput);
    ShopService shopService = new ShopService(shopInput);
    shopService.addEventListener(shopConsoleOutput);

    UpgradeConsoleOutput upgradeConsoleOutput = new UpgradeConsoleOutput(cardOut);
    DamageBoost damageBoost = new DamageBoost(cardIn, upgradeConsoleOutput);
    RangeReducer rangeReducer = new RangeReducer(cardIn, upgradeConsoleOutput);
    shopService.setItems(List.of(damageBoost, rangeReducer));

    // Trigger a shop visit when a village visit event occurs
    ShopVillageTrigger shopVillageTrigger = new ShopVillageTrigger(shopService);
    villageService.addEventListener(shopVillageTrigger);

    GameLoopListener gameLoopListener = new GameLoopConsoleOutput();
    return new GameLoop(gameLoopListener, clanBattle, villageService);
  }
}