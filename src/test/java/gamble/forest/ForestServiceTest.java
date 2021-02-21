package gamble.forest;

import gamble.match.MatchResult;
import gamble.match.MatchService;
import gamble.player.Player;
import gamble.Inputter;
import gamble.village.VillageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ForestServiceTest {

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
  public void multiplierIncrease() {
    // Given
    Player p = new Player();
    p.multiplier = 1;
    when(matchServiceMock.play(p, 1)).thenReturn(new MatchResult(true));

    // When
    forestService.play(p, new int[]{1});

    // Then
    assertEquals(2, p.multiplier);
  }

  @Test
  public void multiplierIncreaseTenMatches() {
    // Given
    // 10 Matches
    Player p = new Player();
    p.multiplier = 1;
    when(matchServiceMock.play(p, 1)).thenReturn(new MatchResult(true));

    // When
    forestService.play(p, new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1});

    // Then
    assertEquals(11, p.multiplier);
  }

  /**
   * Lose a match with more than 10 gold = 10 gold lost
   */
  @Test
  public void loseMatchLoseGold() {
    // Given
    Player p = new Player();
    p.multiplier = 1;
    p.gold = 11;
    when(matchServiceMock.play(p, 1)).thenReturn(new MatchResult(false));

    // When
    forestService.play(p, new int[]{1});

    // Then
    assertEquals(1, p.gold);
    verify(forestEventListener, times(1)).goldLost(10, 1);
  }

  /**
   * Lose a match with less than 10 gold = no gold lost
   */
  @Test
  public void loseMatchNoGoldLost() {
    // Given
    Player p = new Player();
    p.multiplier = 1;
    p.gold = 5;
    when(matchServiceMock.play(p, 1)).thenReturn(new MatchResult(false));

    // When
    forestService.play(p, new int[]{1});

    // Then
    assertEquals(5, p.gold);
    verify(forestEventListener, times(0)).goldLost(10, 1);
  }
}
