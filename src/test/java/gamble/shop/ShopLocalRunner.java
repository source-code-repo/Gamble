package gamble.shop;

import gamble.ConsoleInputter;
import gamble.Inputter;

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

    shopService.visit(15);
  }
}