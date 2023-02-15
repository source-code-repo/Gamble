package gamble.village;

import gamble.Config;
import gamble.Inputter;
import gamble.card.CardService;
import gamble.player.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class VillageServiceTest {

  @InjectMocks
  private VillageService villageService;

  @Mock
  private Inputter inputMock;

  // Injected into VillageService
  @Mock
  private CardService cardServiceMock;

  @Mock
  private VillageEventListener eventListenerMock;

  @BeforeEach
  public void injectEventListener() {
    villageService.addEventListener(eventListenerMock);
  }

  @Test
  void multiplierReset() {

    // Given
    Player p = new Player();
    p.setMultiplier(11);
    when(inputMock.yesOrNo()).thenReturn(true);

    // When
    villageService.villageVisit(1, p, Config.getTargetGold());

    // Then
    assertThat(1, equalTo(p.getMultiplier()));
  }

  @Test
  void testNotWon() {
    // Given
    Player p = new Player();
    p.setGold(1);
    when(inputMock.yesOrNo()).thenReturn(true);

    // When
    villageService.villageVisit(1, p, 5);

    // Then
    verify(eventListenerMock).notWon(5);
  }

  @Test
  void notifyOfVillageVisit() {
    // Given
    Player p = new Player();
    p.setGold(1);
    when(inputMock.yesOrNo()).thenReturn(true);

    // When
    villageService.villageVisit(1, p, 5);

    // Then
    verify(eventListenerMock).visiting(1, p);
  }

  // Tricky to test winning as it exits the JVM by design
}