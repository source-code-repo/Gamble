package gamble.card;

import gamble.player.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CardServiceTest {

  @Mock
  private CardInputter inMock;

  @Mock
  private CardEventListener co;

  @InjectMocks
  CardService cs;

  // Test data
  private final Player p = new Player();

  @BeforeEach
  void setup() {

    // Set up test data
    Card card1 = new Card(1, 3, 3);
    Card card2 = new Card(2, 10, 3);

    var cards = new ArrayList<Card>();
    cards.add(card1);
    cards.add(card2);
    p.setCards(cards);
  }

  @Test
  void rechargeCard1() {

    // Given
    p.getCards().get(0).uses--;
    when(inMock.selectCard(p.getCards())).thenReturn(p.getCards().get(0));

    // When
    cs.rechargeCard(p);

    // Then
    assertThat(p.getCards().get(0).maxUses, equalTo(p.getCards().get(0).uses));
  }

  @Test
  void rechargeCard2Only() {

    // Given
    p.getCards().get(0).uses--;
    p.getCards().get(1).uses--;
    when(inMock.selectCard(p.getCards())).thenReturn(p.getCards().get(1));

    // When
    cs.rechargeCard(p);

    // Then
    assertThat(p.getCards().get(1).maxUses, equalTo(p.getCards().get(1).uses));
    assertThat(2, equalTo(p.getCards().get(0).uses));
  }
}