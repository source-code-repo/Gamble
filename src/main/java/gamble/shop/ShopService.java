package gamble.shop;

import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class ShopService {
  private final List<ShopEventListener> listeners = new ArrayList<>();
  private List<Purchasable> items;
  private final ShopInputter shopInputter;

  public void visit(int matchCount) {
    // Only offer to visit the shop if there are items available
    if(items.stream().noneMatch(e -> e.isAvailable(matchCount))) {
      return;
    }

    listeners.forEach(ShopEventListener::optionToVisit);
    if(!shopInputter.shouldVisitShop()) {
      return;
    }

    listeners.forEach(ShopEventListener::visiting);
    listeners.forEach(e -> e.offerItems(items));

    shopInputter
      .selectItem(items)
      .ifPresent(Purchasable::purchase);
  }

  public void addEventListener(ShopEventListener shopEventListener) {
    listeners.add(shopEventListener);
  }

  public void setItems(List<Purchasable> items) {
    this.items = items;
  }
}