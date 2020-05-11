package gamble.service.io;

public interface RoundOutputter {

    void showCpuCards(int target, int totalPlayed);

    void print(String s);

    void reward();

    void roundOver();

    void playedCard(int value);

    void nextRound();

    void chooseCard();

    void cardUsedUp();

}