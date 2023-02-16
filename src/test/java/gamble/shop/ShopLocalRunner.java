package gamble.shop;

import gamble.ConsoleInputter;
import gamble.Inputter;
import gamble.card.CardConsoleInputter;
import gamble.card.CardConsoleOutputter;
import gamble.card.CardCreator;
import gamble.card.CardInputter;
import gamble.player.Player;
import gamble.shop.item.DamageBoost;
import gamble.shop.item.RangeReducer;
import gamble.shop.item.UpgradeConsoleOutputter;

import java.util.List;

public class ShopLocalRunner {
  /**
   * For local testing with system output
   */
  public static void main(String[] args) {
    Inputter inputter = new ConsoleInputter();
    ShopConsoleOutputter shopConsoleOutputter = new ShopConsoleOutputter();
    ShopInputter shopInputter = new ShopConsoleInputter(inputter, shopConsoleOutputter);
    ShopService shopService = new ShopService(shopInputter);
    shopService.addEventListener(new ShopConsoleOutputter());

    CardConsoleOutputter cardOut = new CardConsoleOutputter();
    CardInputter cardIn = new CardConsoleInputter(cardOut);

    UpgradeConsoleOutputter upgradeConsoleOutputter = new UpgradeConsoleOutputter(cardOut);
    DamageBoost damageBoost = new DamageBoost(cardIn, upgradeConsoleOutputter);
    RangeReducer rangeReducer = new RangeReducer(cardIn, upgradeConsoleOutputter);

    shopService.setItems(List.of(damageBoost, rangeReducer));

    CardCreator cardCreator = new CardCreator();

    Player player = new Player();
    player.setGold(100);
    player.setCards(cardCreator.createCards());

    shopService.visit(4, player);

    cardOut.showPlayerCards(player.getCards());
    System.out.println("Player gold " + player.getGold());
  }
}