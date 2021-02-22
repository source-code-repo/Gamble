package gamble.shop;

import gamble.Inputter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ShopConsoleInputterTest {
  @InjectMocks
  ShopConsoleInputter shopConsoleInputter;

  @Mock
  Inputter inputter;

  @Mock
  ShopConsoleOutputter shopConsoleOutputter;

  @Mock
  Purchasable item1;

  @Mock
  Purchasable item2;

  private List<Purchasable> items;

  @BeforeEach
  void before() {
    items = List.of(item1, item2);
  }

  @Test
  public void selectItem() {
    // Given
    when(inputter.chooseNumber()).thenReturn(1);
    // When
    Optional<Purchasable> purchasable = shopConsoleInputter.selectItem(items);
    // Then
    assertThat(purchasable.isPresent(), equalTo(true));
    assertThat(purchasable.get(), is(item1));
  }

  @Test
  public void selectItemTwo() {
    // Given
    when(inputter.chooseNumber()).thenReturn(2);
    // When
    Optional<Purchasable> purchasable = shopConsoleInputter.selectItem(items);
    // Then
    assertTrue(purchasable.isPresent());
    assertThat(purchasable.get(), is(item2));
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
    assertThat(purchasable.get(), is(item1));
    verify(inputter, times(2)).chooseNumber();
    verify(shopConsoleOutputter, times(1)).incorrectSelection();
  }
}