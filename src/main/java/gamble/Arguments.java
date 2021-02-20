package gamble;

import com.beust.jcommander.Parameter;

public class Arguments {
  @Parameter(names = {"--targetGold"}, description = "Target gold needed to win")
  Integer targetGold;
}
