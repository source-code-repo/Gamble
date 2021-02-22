package gamble.shop.item;

import gamble.card.Card;
import gamble.card.CardInputter;
import gamble.player.Player;
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
  Card card1, card2;

  @Mock
  CardInputter cardInputter;

  @Mock
  UpgradeEventListener upgradeEventListener;

  @Mock
  Player player;

  @Test
  void applyPurchase() {
    // Given
    when(player.getCards()).thenReturn(List.of(card1, card2));
    when(cardInputter.selectCard(List.of(card1, card2))).thenReturn(card1);
    when(card1.getMinValue()).thenReturn(2);
    when(card1.getMaxValue()).thenReturn(7);
    // When
    damageBoost.purchase(player);
    // Then
    verify(upgradeEventListener, times(1))
      .selectingCard(List.of(card1, card2));
    verify(card1, times(1)).setMinValue(5);
    verify(card1, times(1)).setMaxValue(10);
    verify(player, times(1)).reduceGold(damageBoost.cost());
  }
}