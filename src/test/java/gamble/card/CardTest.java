package gamble.card;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

class CardTest {

  @Test
  void getAttackDamage() {
    var card = new Card(1, 1, 1);
    assertThat(1, equalTo(card.getAttackDamage()));
  }
}