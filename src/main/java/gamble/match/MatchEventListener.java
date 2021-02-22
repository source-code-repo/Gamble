package gamble.match;

import gamble.player.Player;

public interface MatchEventListener {
  /**
   * Player got an exact hit during this match
   * @param p Player
   */
  void exactHit(Player p);

  /**
   * A new round of this match has started
   * @param roundNum Round Number
   * @param fightersLeft Number of fighters left in the clan
   */
  void roundStarted(int roundNum, int fightersLeft);

  /**
   * Player lost the match
   */
  void matchLost();
}
