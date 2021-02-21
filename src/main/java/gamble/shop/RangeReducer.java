package gamble.shop;

/**
 * Increases a card's minimum damage, reducing it's range
 */
public class RangeReducer implements Purchasable {
  private static final int MINIMUM_CLAN_NUMBER = 5;

  @Override
  public boolean isAvailable(int clanNumber) {
    return clanNumber >= MINIMUM_CLAN_NUMBER;
  }

  @Override
  public void purchase() {
    System.out.println("Implementing range reducer");
  }

  @Override
  public String description() {
    return "Bundle of herbs\n" +
      "  A strongly scented herb with an unusual green tinge in the sunlight.\n" +
      "  Boosts a card's minimum damage";
  }

  @Override
  public int cost() {
    return 15;
  }
}
