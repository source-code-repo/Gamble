package gamble.service.io;

public interface GameOutputter {

    void nextMatch(int matchNum, int rewards);

    void beatOpponent(int opponentNum, int reward, int multiplier);

    void lost(int opponentNum);

    void gold(int gold);

    void intro();
}