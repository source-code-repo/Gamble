package gamble.shop;

import gamble.ConsoleInputter;
import gamble.Inputter;

import java.util.List;
import java.util.stream.Stream;

public class ShopLocalRunner {
  /**
   * For local testing with system output
   */
  public static void main(String[] args) {
    Inputter inputter = new ConsoleInputter();
    ShopService shopService = new ShopService(inputter);
    shopService.addEventListener(new ShopConsoleOutputter());
    shopService.setItems(List.of(
        new DamageBoost(), new RangeReducer()
    ));

    shopService.visit(15);
  }
}