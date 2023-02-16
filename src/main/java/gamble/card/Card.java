package gamble.card;

import lombok.Getter;
import lombok.Setter;

import java.util.Random;

@Getter
@Setter
public class Card {
  private int minValue;
  private int maxValue;
  private int maxUses;
  private int uses;
  private Random random = new Random();

  public Card(int minValue, int maxValue, int maxUses) {
    this.minValue = minValue;
    this.maxValue = maxValue;
    this.maxUses = maxUses;
    this.uses = maxUses;
  }

  /**
   * Calculate the damage that an attack does
   */
  public int getAttackDamage() {
    int value = random.nextInt(maxValue - minValue + 1);
    value = value + minValue;
    return value;
  }
}
