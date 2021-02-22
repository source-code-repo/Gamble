package gamble.player;

import gamble.card.Card;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Player {
  private List<Card> cards = new ArrayList<>();
  private int gold = 0;
  private int multiplier = 1;

  /**
   * Reduce a player's gold
   * @param reduction Amount to reduce by
   */
  public void reduceGold(int reduction) {
    if(gold < reduction) {
      throw new IllegalStateException(
        "Can't reduce gold below 0. Calling code " +
        "should have checked for this.");
    }
    gold -= reduction;
  }
}