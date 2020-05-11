package gamble.service.io;

import java.util.List;

import gamble.entities.PlayerCard;

public class ConsoleCardOutputter implements CardOutputter {

    @Override
    public void showPlayerCards(List<PlayerCard> cards) {
        
        String str = String.format("You have %d cards", cards.size());
        Util.print(str);
        
        int cardCount = 1;
        StringBuilder sb = new StringBuilder();
        for(PlayerCard pc : cards) {
            sb.append(String.format("Card %d  %2d-%2d     Uses left  %2d/%2d\n", 
                    cardCount, pc.minValue, pc.maxValue, pc.uses, pc.maxUses));
            
            
            cardCount++;
        }
        Util.print(sb.toString());
    }

    @Override
    public void chooseCardToRecharge() {
        Util.print("Choose a card to recharge: ");
        
    }
}
