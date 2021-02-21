package gamble.village;

import gamble.Util;
import gamble.player.Player;

public class VillageConsoleOutputter implements VillageEventListener {
  @Override
  public void decidingToVisit(int fightersInNextClan) {
    String str = String.format("A nearby village has a place you can rest and recharge your cards.\n" +
      "If you visit the village you'll lose your multiplier.\n" +
      "There are %d fighters in the next clan\n" +
      "Will you trek back to the village?\n" +
      "Type yes or no: ", fightersInNextClan);

    Util.print(str);
  }

  @Override
  public void firstVillageVisit() {
    Util.print("A nearby village has a place you can rest and recharge your cards.");
  }

  @Override
  public void visiting(int clanNumber, Player player) {
    Util.print("The tranquil village is a welcome break from the harsh forest. "
      + "You are having a good time.\n");
  }

  @Override
  public void optionToLeave() {
    Util.print("Are you ready to return to battle?");
  }

  @Override
  public void cardsRecharged() {
    Util.print("You arrive in the village and find a mysterious old man who\n"
      + "passes his hand over your cards, replenishing them.\n");
  }

  @Override
  public void backToBattle() {
    Util.print("You leave the village and trek through the dense forest.\n");
  }

  @Override
  public void travellingToVillage() {
    Util.print("You trek through a dark, dense forest. On the other side you find the village.");
  }

  @Override
  public void lookingAtIdol() {
    Util.print("In the window of a small shop, a fabulous idol catches your eye.\n"
      + "You feel strongly that you must have this idol.");
  }

  @Override
  public void notWon(int targetGold) {
    String str = String.format("From inside the shop you hear a booming voice: \"Get lost, "
      + "you don't have %d gold to pay for that!\".\n", targetGold);
    Util.print(str);
  }

  @Override
  public void gameWon() {
    String str = "You walk purposefully towards the shop that you've been rejected from "
      + "so many times before.\n"
      + "You exchange your hard-won gold for the idol you've been dreaming of all this time.\n"
      + "Literally all your dreams come true. You win!";
    Util.print(str);
  }
}
