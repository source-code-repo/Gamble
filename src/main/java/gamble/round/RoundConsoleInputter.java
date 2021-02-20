package gamble.round;

import gamble.card.Card;
import gamble.card.CardInputter;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RoundConsoleInputter implements RoundInputter {

  private final List<RoundEventListener> eventListeners = new ArrayList<>();
  private final CardInputter cardInputter;
  private final RoundConsoleOutputter cardOutputter;

  public RoundConsoleInputter(CardInputter cardInputter, RoundConsoleOutputter cardOutputter) {
    this.cardInputter = cardInputter;
    this.cardOutputter = cardOutputter;
  }

  @Override
  public Card selectAvailableCard(List<Card> cards) {
    Card pc = null;
    boolean cardChosen = false;
    while (!cardChosen) {
      // TODO refactor to reduce tight coupling
      cardOutputter.printChooseCardText();

      pc = cardInputter.selectCard(cards);

      // TODO refactor to remove game logic from input logic
      if (pc.uses == 0) {
        eventListeners.forEach(e -> e.chosenEmptyCard());
        cardChosen = false;
        pc = null;
      } else {
        cardChosen = true;
      }
    }
    return pc;
  }

  public void addRoundEventListener(RoundEventListener roundEventListener) {
    eventListeners.add(roundEventListener);
  }
}
