package gamble.round;

public interface RoundOutputter {

    void showCpuCards(int target, int totalPlayed);

    void print(String s);

    void reward();

    void roundOver();

    void playedCard(int value);

    void chooseCard();

    void cardUsedUp();

}