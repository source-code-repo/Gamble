package gamble.service.io;

public interface MatchOutputter {

    void gameOver();

    void score(long score);

    void nextRound(int roundNum, int cpuCardsLeft);

    void rechargeChard();
}