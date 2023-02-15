package gamble;

import com.beust.jcommander.JCommander;
import gamble.card.CardCreator;
import gamble.fight.Fight;
import gamble.forest.GameLoop;
import gamble.match.ClanBattle;
import gamble.player.Player;
import gamble.player.PlayerBuilder;

/**
 * Overview
 * <p>
 *   {@link GameBuilder} creates the {@link GameLoop} which runs the game with
 *   {@link Player} created by the {@link PlayerBuilder}
 * </p>
 * <p>
 *   {@link GameLoop} starts one {@link ClanBattle} after another
 *   and allows the user to visit the village in between matches.
 *   {@link ClanBattle} sends out each fighter one by one to fight against the player and uses
 *   {@link Fight} for the player to attack each fighter.
 * </p>
 * <p>
 *   The "fights" are very one-sided - the fighters don't actually fight back.
 *   The skill in the game comes from deciding when to return to the village to recharge their cards.
 *   The longer the player stays out fighting, the bigger reward they get, but the fewer attacks
 *   they have left.
 * </p>
 * <p>
 *   The player has a set of magic cards that it uses to attack each figher with.
 * </p>
 * <p>
 *    The Outputters and Inputters provide IO with the user.
 * </p>
 */
public class Main {
  public static void main(String[] args) {
    Arguments arguments = new Arguments();
    JCommander.newBuilder()
      .addObject(arguments)
      .build()
      .parse(args);

    if (arguments.targetGold != null) {
      Config.setTargetGold(arguments.targetGold);
    }

    Player p = new PlayerBuilder(new CardCreator()).createPlayer();
    GameBuilder.createGameLoop().play(p, Config.FIGHTERS_PER_CLAN);
  }
}