package gamble.card;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Scanner;

@RequiredArgsConstructor
public class CardConsoleInputter implements CardInputter {
  private final Scanner reader = new Scanner(System.in);
  private final CardEventListener cardEventListener;

  @Override
  public Card selectCard(List<Card> cards) {
    Card pc = null;
    while (pc == null) {
      try {
        if (!reader.hasNextInt()) {
          reader.nextLine();
          cardEventListener.badResponse();
          continue;
        }
        int n = reader.nextInt();
        pc = cards.get(n - 1);
      } catch (Exception e) {
        cardEventListener.badResponse();
      }
    }
    return pc;
  }
}
