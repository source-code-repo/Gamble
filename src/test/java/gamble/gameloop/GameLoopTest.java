package gamble.gameloop;

import gamble.Inputter;
import gamble.clanbattle.ClanBattleResult;
import gamble.clanbattle.ClanBattle;
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
class GameLoopTest {

  @InjectMocks
  private GameLoop gameLoop;

  @Mock
  private GameLoopListener gameLoopListener;

  @Mock
  private ClanBattle clanBattleMock;

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
    when(clanBattleMock.battle(p, 1)).thenReturn(new ClanBattleResult(true));

    // When
    gameLoop.play(p, new int[]{1});

    // Then
    assertThat(p.getMultiplier(), equalTo(2));
  }

  @Test
  void multiplierIncreaseTenMatches() {
    // Given
    // 10 Matches
    Player p = new Player();
    p.setMultiplier(1);
    when(clanBattleMock.battle(p, 1)).thenReturn(new ClanBattleResult(true));

    // When
    gameLoop.play(p, new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1});

    // Then
    assertThat(p.getMultiplier(), equalTo(11));
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
    when(clanBattleMock.battle(p, 1)).thenReturn(new ClanBattleResult(false));

    // When
    gameLoop.play(p, new int[]{1});

    // Then
    assertThat(p.getGold(), equalTo(6));
    verify(gameLoopListener, times(1)).goldLost(5, 6);
  }

  /**
   * Don't reduce a player's gold below 0 if they lose a clan battle.
   */
  @Test
  void loseClanBattleNoGoldLost() {
    // Given
    Player p = new Player();
    p.setMultiplier(1);
    p.setGold(5);
    when(clanBattleMock.battle(p, 1)).thenReturn(new ClanBattleResult(false));

    // When
    gameLoop.play(p, new int[]{1});

    // Then
    assertThat(p.getGold() ,equalTo(5));
    verify(gameLoopListener, times(0)).goldLost(10, 1);
  }
}
