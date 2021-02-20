package gamble.service;

import gamble.Config;
import gamble.card.CardService;
import gamble.player.Player;
import gamble.player.PlayerService;
import gamble.village.VillageEventListener;
import gamble.village.VillageInputter;
import gamble.village.VillageService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class VillageServiceTest {

  @InjectMocks
  private VillageService villageService;

  @Mock
  private VillageInputter inputMock;

  // Injected into VillageService
  @Mock
  private CardService cardServiceMock;

  @Mock
  private VillageEventListener eventListenerMock;

  @Before
  public void injectEventListener() {
    villageService.addEventListener(eventListenerMock);
  }

  @Test
  public void multiplierReset() {

    // Given
    Player p = new Player();
    p.multiplier = 11;
    when(inputMock.yesOrNo()).thenReturn(true);

    // When
    villageService.villageVisit(p, Config.targetGold);

    // Then
    assertEquals(1, p.multiplier);
  }

  @Test
  public void testNotWon() {
    // Given
    Player p = new Player();
    p.gold = 1;
    when(inputMock.yesOrNo()).thenReturn(true);

    // When
    villageService.villageVisit(p, 5);

    // Then
    verify(eventListenerMock).notWon(5);
  }

  // Tricky to test winning as it exits the JVM by design
}
