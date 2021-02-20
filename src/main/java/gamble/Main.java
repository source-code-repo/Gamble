package gamble;

import com.beust.jcommander.JCommander;
import gamble.card.CardCreator;
import gamble.player.Player;
import gamble.player.PlayerService;

/**
 * Overview
 * <p>
 * The GameFactory creates a GameService which starts the game using a Player
 * created from the PlayerService. GameService starts the Matches
 * and allows the user to visit the village.
 * <p>
 * The MatchService plays a match which is a series of rounds, run by the RoundService.
 * <p>
 * The Outputters and Inputters provide IO with the user.
 */
public class Main {
  public static void main(String[] args) {
    Arguments arguments = new Arguments();
    JCommander.newBuilder()
      .addObject(arguments)
      .build()
      .parse(args);

    if (arguments.targetGold != null) {
      Config.targetGold = arguments.targetGold;
    }

    Player p = new PlayerService(new CardCreator()).setup();
    GameFactory.create().play(p, Config.CARDS_PER_MATCH);
  }
}