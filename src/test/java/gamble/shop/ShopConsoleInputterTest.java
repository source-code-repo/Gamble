package gamble.shop;

import gamble.Inputter;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ShopConsoleInputterTest {
  @InjectMocks
  ShopConsoleInputter shopConsoleInputter;

  @Mock
  Inputter inputter;

  @Mock
  ShopConsoleOutputter shopConsoleOutputter;

  private final DamageBoost damageBoost = new DamageBoost();
  private final RangeReducer rangeReducer = new RangeReducer();
  private final List<Purchasable> items = List.of(damageBoost, rangeReducer);

  @Test
  public void selectItem() {
    // Given
    when(inputter.chooseNumber()).thenReturn(1);
    // When
    Optional<Purchasable> purchasable = shopConsoleInputter.selectItem(items);
    // Then
    assertTrue(purchasable.isPresent());
    assertThat(purchasable.get(), is(damageBoost));
  }

  @Test
  public void selectItemTwo() {
    // Given
    when(inputter.chooseNumber()).thenReturn(2);
    // When
    Optional<Purchasable> purchasable = shopConsoleInputter.selectItem(items);
    // Then
    assertTrue(purchasable.isPresent());
    assertThat(purchasable.get(), is(rangeReducer));
  }

  @Test
  public void zeroReturnsNothing() {
    // Given
    when(inputter.chooseNumber()).thenReturn(0);
    // When
    Optional<Purchasable> purchasable = shopConsoleInputter.selectItem(items);
    // Then
    assertTrue(purchasable.isEmpty());
  }

  @Test
  public void incorrectSelectionPromptsAgain() {
    // Given
    when(inputter.chooseNumber())
      .thenReturn(100)
      .thenReturn(1);
    // When
    Optional<Purchasable> purchasable = shopConsoleInputter.selectItem(items);
    // Then
    assertTrue(purchasable.isPresent());
    assertThat(purchasable.get(), is(damageBoost));
    verify(inputter, times(2)).chooseNumber();
    verify(shopConsoleOutputter, times(1)).incorrectSelection();
  }
}