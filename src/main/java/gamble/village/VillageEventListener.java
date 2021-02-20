package gamble.village;

public interface VillageEventListener {

    void decidingToVisit(int nextReward);

    void firstVillageVisit();

    void visiting();

    void cardsRecharged();

    void backToBattle();

    void travellingToVillage();

    void lookingAtIdol();

    void notWon(int targetGold);

    void gameWon();

}