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
}