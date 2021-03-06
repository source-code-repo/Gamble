package gamble.card;

import lombok.Getter;
import lombok.Setter;

import java.util.Random;

@Getter
@Setter
public class Card {
  public int minValue;
  public int maxValue;
  public int maxUses;
  public int uses;

  public Card(int minValue, int maxValue, int maxUses) {
    this.minValue = minValue;
    this.maxValue = maxValue;
    this.maxUses = maxUses;
    this.uses = maxUses;
  }

  public int getValue() {
    int value = new Random().nextInt(maxValue - minValue + 1);
    value = value + minValue;
    return value;
  }
}
