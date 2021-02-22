package gamble.card;

import gamble.Util;

import java.util.ArrayList;
import java.util.List;

public class CardCreator {
  public List<Card> createCards() {
    var cards = new ArrayList<Card>();
    cards.add(new Card(1, Util.randomBetween(4, 5), 3));
    cards.add(new Card(Util.randomBetween(2, 4), Util.randomBetween(6, 8), 3));
    cards.add(new Card(Util.randomBetween(2, 3), Util.randomBetween(5, 6), 3));
    cards.add(new Card(Util.randomBetween(1, 2), Util.randomBetween(6, 10), 3));
    return cards;
  }
}