package gamble;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;

class ConfigTest {
  /**
   * Enforce a minimum text delay.
   * To test faster, I often reduce the text delay to zero.
   */
  @Test
  void textDelayMustBeSet() {
    assertThat(Config.DELAY, greaterThan(399));
  }
}