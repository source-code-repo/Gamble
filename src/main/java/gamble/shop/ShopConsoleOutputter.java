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
  public void visiting() {
    Util.print("The salesman pulls back a ragged cloth doubling as a curtain\n" +
      "on the back of his wagon and beckons you in to take a closer look.\n");
  }

  @Override
  public void offerItems(List<Purchasable> items) {
    Util.print("Items:");

    int count = 0;
    for (Purchasable item : items) {
      count++;
      System.out.printf("%d %s %n%n",count, item.description());
    }
    Util.print("Make your choice (enter 0 to exit): ");
  }

  public void incorrectSelection() {
    Util.print(ConsoleInputter.BAD_INPUT);
  }
}
