package gamble.shop;

import gamble.player.Player;
import gamble.shop.item.DamageBoost;
import gamble.shop.item.RangeReducer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
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
  ShopInputter inputter;

  @Mock
  Purchasable item;

  @Mock
  Purchasable item2;

  @Mock
  Player player;

  private final DamageBoost damageBoost = new DamageBoost();
  private final RangeReducer rangeReducer = new RangeReducer();
  List<Purchasable> items = List.of(damageBoost, rangeReducer);

  @BeforeEach
  public void setup() {
    shopService.addEventListener(shopEventListener);
  }

  @Test
  void shopClosedIfNoAvailableItems() {
    // Given
    // One item that isn't available
    shopService.setItems(List.of(item));
    when(item.isAvailable(1)).thenReturn(false);
    // When
    shopService.visit(1, player);
    // Then
    verify(shopEventListener, times(0)).optionToVisit();
  }

  @Test
  void shopOpenIfItemsAvailable() {
    // Given
    openShop();
    when(item.isAvailable(1)).thenReturn(true);
    // When
    shopService.visit(1, player);
    // Then
    verify(shopEventListener, times(1)).optionToVisit();
  }

  @Test
  void declineShopVisit() {
    // Given
    openShop();
    when(item.isAvailable(1)).thenReturn(true);
    when(inputter.shouldVisitShop()).thenReturn(false);
    // When
    shopService.visit(1, player);
    // Then
    verify(shopEventListener, times(0)).visiting(player.getGold());
  }

  @Test
  void shopCanBeVisited() {
    // Given
    openShop();
    when(item.isAvailable(1)).thenReturn(true);
    when(inputter.shouldVisitShop()).thenReturn(true);
    // When
    shopService.visit(1, player);
    // Then
    verify(shopEventListener, times(1)).visiting(player.getGold());
  }

  @Test
  void shopListsItems() {
    // Given
    openShop();
    when(inputter.shouldVisitShop()).thenReturn(true); // visit shop
    shopService.setItems(items);
    // When
    shopService.visit(10, player);
    // Then
    verify(shopEventListener, times(1)).offerItems(items);
  }

  @Test
  void purchaseItem() {
    // Given
    var itemsWithMock = new ArrayList<>(items);
    itemsWithMock.add(item);
    openShop();
    when(inputter.shouldVisitShop()).thenReturn(true); // visit shop
    when(inputter.selectItem(itemsWithMock)).thenReturn(Optional.of(item));
    when(item.isAvailable(20)).thenReturn(true);
    shopService.setItems(itemsWithMock);
    // When
    shopService.visit(20, player);
    // Then
    verify(item, times(1)).purchase(player);
  }

  @Test
  void notEnoughGold() {
    // Given
    shopService.setItems(items);
    when(inputter.shouldVisitShop()).thenReturn(true);
    when(inputter.selectItem(items))
      .thenReturn(Optional.of(damageBoost)) // first time request the damage boost we can't afford
      .thenReturn(Optional.empty()); // second time, choose to exit
    when(player.getGold()).thenReturn(1);
    // When
    shopService.visit(10, player);
    // Then
    verify(shopEventListener, times(1)).cantAffordItem(damageBoost);
  }

  @Test
  void notOfferedUnavailableItem() {
    // Given
    shopService.setItems(List.of(item, item2));
    when(item.isAvailable(10)).thenReturn(true);
    when(item2.isAvailable(10)).thenReturn(false);
    when(inputter.shouldVisitShop()).thenReturn(true);
    when(inputter.selectItem(List.of(item))).thenReturn(Optional.empty());
    // When
    shopService.visit(10, player);
    // Then
    verify(shopEventListener, times(1)).offerItems(List.of(item));
  }

  /**
   * Open the shop by having one item available
   */
  private void openShop() {
    shopService.setItems(List.of(item));
  }
}
