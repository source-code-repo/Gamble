package gamble.shop;

import java.util.List;

public interface ShopEventListener {
  /**
   * Shop is offering the option to visit
   */
  void optionToVisit();

  /**
   * Player is visiting the shops
   * @param playersGold Player's gold
   */
  void visiting(int playersGold);

  /**
   * Player is being offered items to purchase
   * @param items Item details
   */
  void offerItems(List<Purchasable> items);

  /**
   * Player tried to buy an item they can't afford
   * @param item The item the player tried to buy
   */
  void cantAffordItem(Purchasable item);

  /**
   * Shop can't be accessed yet
   */
  void cantAccessYet();
}
