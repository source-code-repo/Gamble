package gamble.village;

import gamble.Util;

public class ConsoleVillageOutputter implements VillageOutputter {

	/* (non-Javadoc)
     * @see gamble.service.output.VillageOutputter#shouldVisit()
     */
	@Override
    public void shouldVisit(int nextReward) {
	    String str = String.format("Will you trek back to the village?\n"
                + "If you visit the village you'll lose your multiplier.\n"
                + "Your next opponent has %d gold\n"
                + "Type yes or no: ", nextReward);
	    
		Util.print(str);
	}

	/* (non-Javadoc)
     * @see gamble.service.output.VillageOutputter#welcome()
     */
	@Override
    public void welcome() {
		Util.print("A nearby village has a place you can rest and recharge your cards.");
	}

	/* (non-Javadoc)
     * @see gamble.service.output.VillageOutputter#visiting(int)
     */
	@Override
    public void visiting() {
		String str = String.format("The tranquil village is a welcome break from the forest. "
		        + "You are having a good time.\n");
		Util.print(str);
		
	}

	/* (non-Javadoc)
     * @see gamble.service.output.VillageOutputter#readyForBattle()
     */
	@Override
    public void readyForBattle() {
		Util.print("Are you ready to return to battle?");
	}

	/* (non-Javadoc)
     * @see gamble.service.output.VillageOutputter#cardsRecharged()
     */
	@Override
    public void cardsRecharged() {
		Util.print("You arrive in the village and find a mysterious old man who\n"
				+ "passes his hand over your cards, replenishing them.\n");
	}

	/* (non-Javadoc)
     * @see gamble.service.output.VillageOutputter#backToBattle()
     */
	@Override
    public void backToBattle() {
		Util.print("You leave the village and trek through the dense forest.\n");		
	}

	/* (non-Javadoc)
     * @see gamble.service.output.VillageOutputter#trekToVillage()
     */
	@Override
    public void trekToVillage() {
		Util.print("You trek through a dark, dense forest. On the other side you find the village.");		
	}

    @Override
    public void idol() {
        Util.print("In the window of a small shop, a fabulous idol catches your eye.\n"
                + "You feel strongly that you must have this idol.");
    }

    @Override
    public void notEnoughGold(int targetGold) {
        String str = String.format("From inside the shop you hear a booming voice: \"Get lost, "
                + "you don't have %d gold to pay for that!\".\n", targetGold);
        Util.print(str);
    }

    @Override
    public void win() {
        String str = "You walk purposefully towards the shop that you've been rejected from "
                + "so many times before.\n"
                + "You exchange your hard-won gold for the idol you've been dreaming of all this time.\n"
                + "Literally all your dreams come true. You win!";
        Util.print(str);
    }
}
