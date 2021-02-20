package gamble.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import gamble.card.CardInputter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import gamble.player.Player;
import gamble.card.Card;
import gamble.card.CardService;
import gamble.card.CardOutputter;

@RunWith(MockitoJUnitRunner.class)
public class CardServiceTest {
    
    @Mock
    private CardInputter inMock;
    
    @Mock
    private CardOutputter co;
    
    @InjectMocks
    CardService cs;
    
    // Test data
    private Player p = new Player();
    
    @Before
    public void setup() {
        
        // Set up test data
        Card card1 = new Card(1, 3, 3);
        Card card2 = new Card(2, 10, 3);
        
        p.cards = new ArrayList<>();
        
        p.cards.add(card1);
        p.cards.add(card2);
    }
    
    @Test
    public void rechargeCard1() {
    
        // Given
        p.cards.get(0).uses--;
        when(inMock.selectCard(p.cards)).thenReturn(p.cards.get(0));
        
        // When
        cs.rechargeCard(p);
        
        // Then
        assertEquals(p.cards.get(0).maxUses, p.cards.get(0).uses);
    }
    
    @Test
    public void rechargeCard2Only() {
        
        // Given
        p.cards.get(0).uses--;
        p.cards.get(1).uses--;
        when(inMock.selectCard(p.cards)).thenReturn(p.cards.get(1));
        
        // When
        cs.rechargeCard(p);
        
        // Then
        assertEquals(p.cards.get(1).maxUses, p.cards.get(1).uses);
        assertEquals(2, p.cards.get(0).uses);
    }
}