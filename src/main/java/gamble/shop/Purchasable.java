package gamble.shop;

import gamble.player.Player;

public interface Purchasable {

  /**
   * Is the item available for purchase?
   * @param clansBeaten How many clans the player has beaten
   */
  boolean isAvailable(int clansBeaten);

  /**
   * Purchase this item and have it take the appropriate effect
   * @param player Player information - will be updated
   */
  void purchase(Player player);

  /**
   * Description of this item
   * @return Description
   */
  String description();

  /**
   * How much does this cost?
   * @return Cost in gold
   */
  int cost();
}
