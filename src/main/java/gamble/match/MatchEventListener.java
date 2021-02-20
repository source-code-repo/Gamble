package gamble.match;

import gamble.player.Player;

public interface MatchEventListener {

  /**
   * Player got an exact hit during this match
   * @param p
   */
  void exactHit(Player p);

  /**
   * A new round of this match has started
   * @param roundNum Round Number
   * @param opponentCardsLeft Number of cards the opponent has left
   */
  void roundStarted(int roundNum, int opponentCardsLeft);

  /**
   * Player lost the match
   */
  void matchLost();
}
