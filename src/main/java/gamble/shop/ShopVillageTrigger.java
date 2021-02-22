package gamble.shop;

import gamble.player.Player;
import gamble.village.VillageEventListener;
import lombok.RequiredArgsConstructor;

/**
 * Triggers a visit to the shop when the player visits the village
 */
@RequiredArgsConstructor
public class ShopVillageTrigger implements VillageEventListener {
  private final ShopService shopService;

  @Override
  public void visiting(int clansBeaten, Player player) {
    shopService.visit(clansBeaten, player);
  }
}
