package gamble.match;

import gamble.player.Player;

public interface ClanBattleEventListener {
  /**
   * Player got an exact hit during this match
   * @param p Player
   */
  void exactHit(Player p);

  /**
   * The next fight in this battle has started
   * @param fightNumber Round Number
   * @param fightersLeft Number of fighters left in the clan
   */
  void fightStarting(int fightNumber, int fightersLeft);

  /**
   * Clan battle was lost
   */
  void clanBattleLost();
}
