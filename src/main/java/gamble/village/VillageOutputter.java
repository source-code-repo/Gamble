package gamble.village;

public interface VillageOutputter {

    void shouldVisit(int nextReward);

    void welcome();

    void visiting();

    void readyForBattle();

    void cardsRecharged();

    void backToBattle();

    void trekToVillage();

    void idol();

    void notEnoughGold(int targetGold);

    void win();

}