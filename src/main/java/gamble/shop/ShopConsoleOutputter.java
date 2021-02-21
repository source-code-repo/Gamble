package gamble.shop;

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
    System.out.println("Items:");

    int count = 0;
    for (Purchasable item : items) {
      count++;
      System.out.println(
        String.format("%d %s \n",count, item.description())
      );
    }
    Util.print("Make your choice (enter 0 to exit): ");
  }
}
