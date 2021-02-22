package gamble;

import gamble.player.Player;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@ExtendWith(MockitoExtension.class)
class PlayerTest {
  @InjectMocks
  Player player;

  @BeforeEach
  void beforeEach() {
    player.setGold(10);
  }

  @Test
  void reduceGold() {
    // When
    player.reduceGold(4);
    // Then
    assertThat(player.getGold(), equalTo(6));
  }

  @Test
  void goldCanBeZero() {
    // When
    player.reduceGold(10);
    // Then
    assertThat(player.getGold(), equalTo(0));
  }

  @Test
  void goldMustBeMoreThanZero() {
    // When/Then
    Assertions.assertThrows(
      IllegalStateException.class,
      () -> player.reduceGold(11)
    );
  }
}
