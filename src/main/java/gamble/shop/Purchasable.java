package gamble.shop;

public interface Purchasable {

  /**
   * Is the item availble for purchase?
   * @param clanNumber Current clan number the user has beaten
   */
  boolean isAvailable(int clanNumber);

  /**
   * Purchase this item and have it take the appropriate effect
   */
  void purchase();

  /**
   * Description of this item
   * @return
   */
  String description();

  /**
   * Gold cost of the items
   * @return
   */
  int cost();
}
