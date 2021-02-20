package gamble.card;

import java.util.List;

import gamble.Util;

public class CardConsoleOutputter implements CardEventListener {

    @Override
    public void showPlayerCards(List<Card> cards) {
        
        String str = String.format("You have %d cards", cards.size());
        Util.print(str);
        
        int cardCount = 1;
        StringBuilder sb = new StringBuilder();
        for(Card pc : cards) {
            sb.append(String.format("Card %d  %2d-%2d     Uses left  %2d/%2d\n", 
                    cardCount, pc.minValue, pc.maxValue, pc.uses, pc.maxUses));
            
            
            cardCount++;
        }
        Util.print(sb.toString());
    }

    @Override
    public void rechargingCards() {
        Util.print("Choose a card to recharge: ");
        
    }

    @Override
    public void dontUnderstand() {
        Util.printNoDelay("Sorry I don't understand :( Try again.");
    }
}
