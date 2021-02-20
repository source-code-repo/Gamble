package gamble.card;

import java.util.Random;

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
    Random r = new Random();
    int value = r.nextInt(maxValue - minValue + 1);
    value = value + minValue;
    return value;
  }
}
