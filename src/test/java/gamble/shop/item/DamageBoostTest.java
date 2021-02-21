package gamble.shop.item;

import gamble.card.Card;
import gamble.card.CardInputter;
import gamble.player.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DamageBoostTest {
  @InjectMocks
  DamageBoost damageBoost;

  @Mock
  Card card;

  @Mock
  Card card2;

  @Mock
  CardInputter cardInputter;

  @Mock
  DamageBoostEventListener damageBoostEventListener;

  Player player;

  @BeforeEach
  void setup() {
    player = new Player();
    player.setCards(List.of(card, card2));
  }

  @Test
  void applyPurchase() {
    // Given
    when(cardInputter.selectCard(List.of(card, card2))).thenReturn(card);
    when(card.getMinValue()).thenReturn(2);
    when(card.getMaxValue()).thenReturn(7);
    // When
    damageBoost.purchase(player);
    // Then
    verify(damageBoostEventListener, times(1))
      .selectingCard(List.of(card, card2));
    verify(card, times(1)).setMinValue(5);
    verify(card, times(1)).setMaxValue(10);
  }
}
