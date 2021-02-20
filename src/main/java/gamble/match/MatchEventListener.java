package gamble.match;

import gamble.player.Player;

public interface MatchEventListener {

    void exactMatch(Player p);

    void roundStarted(int roundNum, int cpuCards);

    void matchLost();
}
