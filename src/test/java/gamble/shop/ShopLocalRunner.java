package gamble.shop;

import gamble.ConsoleInput;
import gamble.Input;
import gamble.card.CardConsoleInput;
import gamble.card.CardConsoleOutput;
import gamble.card.CardCreator;
import gamble.card.CardInput;
import gamble.player.Player;
import gamble.shop.item.DamageBoost;
import gamble.shop.item.RangeReducer;
import gamble.shop.item.UpgradeConsoleOutput;

import java.util.List;

public class ShopLocalRunner {
  /**
   * For local testing with system output
   */
  public static void main(String[] args) {
    Input input = new ConsoleInput();
    ShopConsoleOutput shopConsoleOutput = new ShopConsoleOutput();
    ShopInput shopInput = new ShopConsoleInput(input, shopConsoleOutput);
    ShopService shopService = new ShopService(shopInput);
    shopService.addEventListener(new ShopConsoleOutput());

    CardConsoleOutput cardOut = new CardConsoleOutput();
    CardInput cardIn = new CardConsoleInput(cardOut);

    UpgradeConsoleOutput upgradeConsoleOutput = new UpgradeConsoleOutput(cardOut);
    DamageBoost damageBoost = new DamageBoost(cardIn, upgradeConsoleOutput);
    RangeReducer rangeReducer = new RangeReducer(cardIn, upgradeConsoleOutput);

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