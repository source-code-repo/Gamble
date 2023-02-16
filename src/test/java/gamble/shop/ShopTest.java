package gamble.shop;

import gamble.player.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ShopTest {
  @InjectMocks
  ShopService shopService;

  @Mock
  ShopEventListener shopEventListener;

  @Mock
  ShopInput input;

  @Mock
  Purchasable item1;

  @Mock
  Purchasable item2;

  @Mock
  Player player;

  List<Purchasable> items;

  @BeforeEach
  public void setup() {
    shopService.addEventListener(shopEventListener);
    // Can't add item1 + 2 to items on initialization as
    // they are null, not yet initialized by @Mock
    items = List.of(item1, item2);
  }

  @Test
  void shopClosedIfNoAvailableItems() {
    // Given
    // One item that isn't available
    shopService.setItems(List.of(item1));
    when(item1.isAvailable(1)).thenReturn(false);
    // When
    shopService.visit(1, player);
    // Then
    verify(shopEventListener, times(0)).optionToVisit();
  }

  @Test
  void shopOpenIfItemsAvailable() {
    // Given
    openShop();
    when(item1.isAvailable(1)).thenReturn(true);
    // When
    shopService.visit(1, player);
    // Then
    verify(shopEventListener, times(1)).optionToVisit();
  }

  @Test
  void declineShopVisit() {
    // Given
    openShop();
    when(item1.isAvailable(1)).thenReturn(true);
    when(input.shouldVisitShop()).thenReturn(false);
    // When
    shopService.visit(1, player);
    // Then
    verify(shopEventListener, times(0)).visiting(player.getGold());
  }

  @Test
  void shopCanBeVisited() {
    // Given
    openShop();
    when(item1.isAvailable(1)).thenReturn(true);
    when(input.shouldVisitShop()).thenReturn(true);
    // When
    shopService.visit(1, player);
    // Then
    verify(shopEventListener, times(1)).visiting(player.getGold());
  }

  @Test
  void shopListsItems() {
    // Given
    openShop();
    when(item1.isAvailable(10)).thenReturn(true);
    when(item2.isAvailable(10)).thenReturn(true);
    when(input.shouldVisitShop()).thenReturn(true); // visit shop
    shopService.setItems(items);
    // When
    shopService.visit(10, player);
    // Then
    verify(shopEventListener, times(1)).offerItems(items);
  }

  @Test
  void purchaseItem() {
    // Given
    openShop();
    when(input.shouldVisitShop()).thenReturn(true); // visit shop
    when(input.selectItem(List.of(item1))).thenReturn(Optional.of(item1));
    when(item1.isAvailable(20)).thenReturn(true);
    // When
    shopService.visit(20, player);
    // Then
    verify(item1, times(1)).purchase(player);
  }

  @Test
  void notEnoughGold() {
    // Given
    shopService.setItems(items);
    when(item1.isAvailable(10)).thenReturn(true);
    when(item2.isAvailable(10)).thenReturn(true);
    when(input.shouldVisitShop()).thenReturn(true);
    when(input.selectItem(items))
      .thenReturn(Optional.of(item1)) // first time request the damage boost we can't afford
      .thenReturn(Optional.empty()); // second time, choose to exit
    when(player.getGold()).thenReturn(1);
    when(item1.cost()).thenReturn(100);
    // When
    shopService.visit(10, player);
    // Then
    verify(shopEventListener, times(1)).cantAffordItem(item1);
  }

  @Test
  void notOfferedUnavailableItem() {
    // Given
    shopService.setItems(List.of(item1, item2));
    when(item1.isAvailable(10)).thenReturn(true);
    when(item2.isAvailable(10)).thenReturn(false);
    when(input.shouldVisitShop()).thenReturn(true);
    when(input.selectItem(List.of(item1))).thenReturn(Optional.empty());
    // When
    shopService.visit(10, player);
    // Then
    verify(shopEventListener, times(1)).offerItems(List.of(item1));
  }

  @Test
  void notifyShopOpeningSoon() {
    // Given
    shopService.setItems(List.of(item1));
    // When
    shopService.visit(1, player);
    // Then
    verify(shopEventListener).cantAccessYet();
  }

  /**
   * Open the shop by having one item available
   */
  private void openShop() {
    shopService.setItems(List.of(item1));
  }
}
