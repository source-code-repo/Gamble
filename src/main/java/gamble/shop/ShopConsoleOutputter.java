package gamble.shop;

import gamble.ConsoleInputter;
import gamble.Util;

import java.util.List;

public class ShopConsoleOutputter implements ShopEventListener {
  @Override
  public void optionToVisit() {
    Util.print("A travelling salesman is nearby, advertising a collection \n" +
      "of \"rarest quality, finest trinkets\". Take a closer look?\n");
  }

  @Override
  public void visiting(int playersGold) {
    Util.print(String.format(
      "The salesman pulls back a ragged cloth doubling as a curtain%n" +
      "on the back of his wagon and beckons you in to take a closer look.%n" +
      "You have %d gold.%n", playersGold));
  }

  @Override
  public void offerItems(List<Purchasable> items) {
    Util.print("Items:");

    int count = 0;
    for (Purchasable item : items) {
      count++;
      Util.printNoDelay(String.format(
        "%d [%d Gold] %s %n", count, item.cost(), item.description()
      ));
    }
    Util.print("Make your choice (enter 0 to exit): ");
  }

  @Override
  public void cantAffordItem(Purchasable item) {
    Util.print(String.format(
      "The salesman pulls the item away. \"You don't have %d gold\"", item.cost()
    ));
  }

  public void incorrectSelection() {
    Util.print(ConsoleInputter.BAD_INPUT);
  }
}
