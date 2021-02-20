package gamble.game;

public interface GameEventListener {

    void matchStarted(int matchNum, int rewards);

    void matchWon(int opponentNum, int reward, int multiplier);

    void matchLost(int opponentNum);

    void rewardGiven(int gold);

    void gameStarted();
}