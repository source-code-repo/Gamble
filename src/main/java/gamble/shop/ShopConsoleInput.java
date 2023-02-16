package gamble.shop;

import gamble.Input;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class ShopConsoleInput implements ShopInput {
  private final Input input;
  private final ShopConsoleOutput shopConsoleOutput;

  @Override
  public boolean shouldVisitShop() {
    return input.yesOrNo();
  }

  @Override
  public Optional<Purchasable> selectItem(List<Purchasable> items) {
    Purchasable item = null;
    while(item == null) {
      int num = input.chooseNumber();
      if(num == 0) {
        return Optional.empty();
      }
      if(num > items.size()) {
        shopConsoleOutput.incorrectSelection();
        continue;
      }
      item = items.get(num - 1);
    }

    return Optional.of(item);
  }
}