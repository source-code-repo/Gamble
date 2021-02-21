package gamble.shop;

import gamble.Inputter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class ShopService {
  private List<ShopEventListener> listeners = new ArrayList<>();
  private List<Purchasable> items;
  private final Inputter inputter;

  public void visit(int matchCount) {
    // Only offer to visit the shop if there are items available
    if(items.stream().filter(e -> e.isAvailable(matchCount)).count() == 0) {
      return;
    }

    listeners.forEach(ShopEventListener::optionToVisit);
    if(!inputter.yesOrNo()) {
      return;
    }

    listeners.forEach(ShopEventListener::visiting);
    listeners.forEach(e -> e.offerItems(items));
    int selection = inputter.chooseNumber();
  }

  public void addEventListener(ShopEventListener shopEventListener) {
    listeners.add(shopEventListener);
  }

  public void setItems(List<Purchasable> items) {
    this.items = items;
  }
}