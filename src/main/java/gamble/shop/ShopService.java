package gamble.shop;

import gamble.player.Player;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class ShopService {
  private final List<ShopEventListener> listeners = new ArrayList<>();
  private List<Purchasable> items;
  private final ShopInputter shopInputter;

  public void visit(int clansBeaten, Player player) {
    // Only offer to visit the shop if there are items available
    if(items.stream().noneMatch(e -> e.isAvailable(clansBeaten))) {
      listeners.forEach(ShopEventListener::cantAccessYet);
      return;
    }

    listeners.forEach(ShopEventListener::optionToVisit);
    if(!shopInputter.shouldVisitShop()) {
      return;
    }

    listeners.forEach(l -> l.visiting(player.getGold()));

    var availableItems = items
      .stream()
      .filter(e -> e.isAvailable(clansBeaten))
      .collect(Collectors.toList());

    Optional<Purchasable> item;
    do {
      listeners.forEach(e -> e.offerItems(availableItems));
      item = shopInputter.selectItem(availableItems);

      if (item.isEmpty()) {
        return;
      }
    } while(!canAfford(item.get(), player));

    item.ifPresent(i -> i.purchase(player));
  }

  private boolean canAfford(Purchasable item, Player player) {
    if(item.cost() <= player.getGold()) {
      return true;
    } else {
      listeners.forEach(l -> l.cantAffordItem(item));
      return false;
    }
  }

  public void addEventListener(ShopEventListener shopEventListener) {
    listeners.add(shopEventListener);
  }

  public void setItems(List<Purchasable> items) {
    this.items = items;
  }
}