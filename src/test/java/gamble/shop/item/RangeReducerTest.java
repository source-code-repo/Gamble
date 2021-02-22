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
class RangeReducerTest {
  @InjectMocks
  RangeReducer rangeReducer;

  @Mock
  Player player;

  @Mock
  Card card;

  @Mock
  CardInputter cardInputter;

  @Mock
  UpgradeEventListener upgradeEventListener;

  @Test
  void promptToSelectCard() {
    // Given
    when(player.getCards()).thenReturn(List.of(card));
    when(cardInputter.selectCard(List.of(card))).thenReturn(card);
    // When
    rangeReducer.purchase(player);
    // Then
    verify(upgradeEventListener, times(1))
      .selectingCard(List.of(card));
    verify(cardInputter, times(1))
      .selectCard(List.of(card));
  }

  /**
   * Upgrading a card should increase minimum damage by two
   */
  @Test
  void cardUpgraded() {
    // Given
    when(player.getCards()).thenReturn(List.of(card));
    when(cardInputter.selectCard(List.of(card)))
      .thenReturn(card);
    // Card is valid for upgrade (range not too small)
    when(card.getMinValue()).thenReturn(3);
    when(card.getMaxValue()).thenReturn(7);
    // When
    rangeReducer.purchase(player);
    // Then
    verify(card, times(1)).setMinValue(5);
  }

  /**
   * Can't upgrade a card if the min value would be less
   * than two from the max
   *
   * e.g. card with 2-5 damage can't be upgraded
   *      because it would be upgraded to 4-5
   *      which makes the game too easy.
   */
  @Test
  void errorIfCantUpgrade() {
    // Given
    when(cardInputter.selectCard(List.of())).thenReturn(card);
    when(card.getMinValue()).thenReturn(2);
    when(card.getMaxValue()).thenReturn(5);

    // When
    rangeReducer.purchase(player);
    // Then
    verify(upgradeEventListener, times(1))
      .cantUpgrade(anyString());
    verify(player, never()).reduceGold(any(Integer.class));
  }

  @Test
  void chargedForUpgrade() {
    // Given
    when(cardInputter.selectCard(List.of())).thenReturn(card);
    // Card is valid for upgrade (range not too small)
    when(card.getMinValue()).thenReturn(2);
    when(card.getMaxValue()).thenReturn(6);
    // When
    rangeReducer.purchase(player);
    // Then
    verify(player, times(1)).reduceGold(15);
  }

  @Test
  void showUpgradeResult() {
    // Given
    when(cardInputter.selectCard(List.of())).thenReturn(card);
    // Card is valid for upgrade (range not too small)
    when(card.getMinValue()).thenReturn(2);
    when(card.getMaxValue()).thenReturn(6);
    // When
    rangeReducer.purchase(player);
    // Then
    verify(upgradeEventListener).upgradeComplete(card);
  }
}