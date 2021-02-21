package gamble.shop;

import gamble.village.VillageEventListener;
import lombok.RequiredArgsConstructor;

/**
 * Triggers a visit to the shop when the player visits the village
 */
@RequiredArgsConstructor
public class ShopVillageTrigger implements VillageEventListener {
  private final ShopService shopService;

  @Override
  public void visiting(int matchCount) {
    shopService.visit(matchCount);
  }
}
