package gamble.fight;

public class FightResult {
  public boolean exactHit = false;
  public boolean won = false;

  public FightResult(boolean exactHit, boolean won) {
    super();
    this.exactHit = exactHit;
    this.won = won;
  }
}
