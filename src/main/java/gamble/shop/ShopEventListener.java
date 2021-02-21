package gamble.shop;

import java.util.List;

public interface ShopEventListener {
  /**
   * Shop is offering the option to visit
   */
  void optionToVisit();

  /**
   * Player is visiting the shops
   */
  void visiting();

  /**
   * Player is offered items
   * @param items
   */
  void offerItems(List<Purchasable> items);
}
