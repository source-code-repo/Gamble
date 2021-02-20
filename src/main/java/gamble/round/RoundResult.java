package gamble.round;

public class RoundResult {
  public boolean exactHit = false;
  public boolean won = false;

  public RoundResult(boolean exactHit, boolean won) {
    super();
    this.exactHit = exactHit;
    this.won = won;
  }
}
