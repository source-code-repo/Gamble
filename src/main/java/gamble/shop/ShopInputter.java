package gamble.shop;

import java.util.List;
import java.util.Optional;

public interface ShopInputter {
  /**
   * Ask the player to visit the shop
   * @return Player's response
   */
  boolean shouldVisitShop();

  /**
   * Ask the player to select an item
   * @param items To choose from
   * @return Selected item
   */
  Optional<Purchasable> selectItem(List<Purchasable> items);
}
