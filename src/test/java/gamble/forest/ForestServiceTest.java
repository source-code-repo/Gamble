package gamble.forest;

import gamble.Inputter;
import gamble.match.MatchResult;
import gamble.match.MatchService;
import gamble.player.Player;
import gamble.village.VillageService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ForestServiceTest {

  @InjectMocks
  private ForestService forestService;

  @Mock
  private ForestEventListener forestEventListener;

  @Mock
  private MatchService matchServiceMock;

  @Mock
  private VillageService vs;

  @Mock
  private Inputter inputter;

  /**
   * Multiplier increases after every match won
   */
  @Test
  void multiplierIncrease() {
    // Given
    Player p = new Player();
    p.setMultiplier(1);
    when(matchServiceMock.play(p, 1)).thenReturn(new MatchResult(true));

    // When
    forestService.play(p, new int[]{1});

    // Then
    assertThat(2, equalTo(p.getMultiplier()));
  }

  @Test
  void multiplierIncreaseTenMatches() {
    // Given
    // 10 Matches
    Player p = new Player();
    p.setMultiplier(1);
    when(matchServiceMock.play(p, 1)).thenReturn(new MatchResult(true));

    // When
    forestService.play(p, new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1});

    // Then
    assertThat(11, equalTo(p.getMultiplier()));
  }

  /**
   * Lose 5 gold if you lose a match and had more than 10 gold
   */
  @Test
  void loseMatchLoseGold() {
    // Given
    Player p = new Player();
    p.setMultiplier(1);
    p.setGold(11);
    when(matchServiceMock.play(p, 1)).thenReturn(new MatchResult(false));

    // When
    forestService.play(p, new int[]{1});

    // Then
    assertThat(6, equalTo(p.getGold()));
    verify(forestEventListener, times(1)).goldLost(5, 6);
  }

  /**
   * Lose a match with less than 10 gold = no gold lost
   */
  @Test
  void loseMatchNoGoldLost() {
    // Given
    Player p = new Player();
    p.setMultiplier(1);
    p.setGold(5);
    when(matchServiceMock.play(p, 1)).thenReturn(new MatchResult(false));

    // When
    forestService.play(p, new int[]{1});

    // Then
    assertThat(5, equalTo(p.getGold()));
    verify(forestEventListener, times(0)).goldLost(10, 1);
  }
}
