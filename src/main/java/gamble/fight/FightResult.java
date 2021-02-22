package gamble.fight;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class FightResult {
  private final boolean exactHit;
  private final boolean won;
}
