package gamble.shop;

import gamble.Input;
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
class ShopConsoleInputTest {
  @InjectMocks
  ShopConsoleInput shopConsoleInput;

  @Mock
  Input input;

  @Mock
  ShopConsoleOutput shopConsoleOutput;

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
  void selectItem() {
    // Given
    when(input.chooseNumber()).thenReturn(1);
    // When
    Optional<Purchasable> purchasable = shopConsoleInput.selectItem(items);
    // Then
    assertThat(purchasable.isPresent(), equalTo(true));
    assertThat(purchasable.get(), is(item1));
  }

  @Test
  void selectItemTwo() {
    // Given
    when(input.chooseNumber()).thenReturn(2);
    // When
    Optional<Purchasable> purchasable = shopConsoleInput.selectItem(items);
    // Then
    assertTrue(purchasable.isPresent());
    assertThat(purchasable.get(), is(item2));
  }

  @Test
  void zeroReturnsNothing() {
    // Given
    when(input.chooseNumber()).thenReturn(0);
    // When
    Optional<Purchasable> purchasable = shopConsoleInput.selectItem(items);
    // Then
    assertTrue(purchasable.isEmpty());
  }

  @Test
  void incorrectSelectionPromptsAgain() {
    // Given
    when(input.chooseNumber())
      .thenReturn(100)
      .thenReturn(1);
    // When
    Optional<Purchasable> purchasable = shopConsoleInput.selectItem(items);
    // Then
    assertTrue(purchasable.isPresent());
    assertThat(purchasable.get(), is(item1));
    verify(input, times(2)).chooseNumber();
    verify(shopConsoleOutput, times(1)).incorrectSelection();
  }
}