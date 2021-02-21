package gamble.shop;

import gamble.Inputter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ShopTest {
  @InjectMocks
  private ShopService shopService;

  @Mock
  private ShopEventListener shopEventListener;

  @Mock
  private Inputter inputter;

  @Mock
  private Purchasable item;

  @Before
  public void setup() {
    shopService.addEventListener(shopEventListener);
  }

  @Test
  public void shopClosedIfNoAvailableItems() {
    // Given
    // One item that isn't available
    shopService.setItems(List.of(item));
    when(item.isAvailable(1)).thenReturn(false);
    // When
    shopService.visit(1);
    // Then
    verify(shopEventListener, times(0)).optionToVisit();
  }

  @Test
  public void shopOpenIfItemsAvailable() {
    // Given
    openShop();
    // When
    shopService.visit(1);
    // Then
    verify(shopEventListener, times(1)).optionToVisit();
  }

  @Test
  public void declineShopVisit() {
    // Given
    openShop();
    when(inputter.yesOrNo()).thenReturn(false);
    // When
    shopService.visit(1);
    // Then
    verify(shopEventListener, times(0)).visiting();
  }

  @Test
  public void shopCanBeVisited() {
    // Given
    openShop();
    when(inputter.yesOrNo()).thenReturn(true);
    // When
    shopService.visit(1);
    // Then
    verify(shopEventListener, times(1)).visiting();
  }

  @Test
  public void shopListsItems() {
    // Given
    List<Purchasable> items = List.of(
      new DamageBoost(), new RangeReducer()
    );
    openShop();
    when(inputter.yesOrNo()).thenReturn(true); // visit shop
    shopService.setItems(items);
    // When
    shopService.visit(20);
    // Then
    verify(shopEventListener, times(1)).offerItems(items);
  }

  /**
   * Open the shop by having one item available
   */
  private void openShop() {
    shopService.setItems(List.of(item));
    when(item.isAvailable(1)).thenReturn(true);
  }
}
