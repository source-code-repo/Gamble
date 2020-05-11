package gamble.entities;

import java.util.Random;

public class PlayerCard {
	
	public int minValue;
	public int maxValue;
	public int maxUses;
	public int uses;
	
	public PlayerCard(int minValue, int maxValue, int maxUses) {
		super();
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
