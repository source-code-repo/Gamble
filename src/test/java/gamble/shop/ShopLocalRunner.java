package gamble.shop;

import gamble.ConsoleInputter;
import gamble.Inputter;
import gamble.player.Player;
import gamble.shop.item.DamageBoost;
import gamble.shop.item.RangeReducer;

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
    shopService.setItems(List.of(
        new DamageBoost(), new RangeReducer()
    ));

    Player player = new Player();
    player.setGold(10);

    shopService.visit(2, player);
  }
}