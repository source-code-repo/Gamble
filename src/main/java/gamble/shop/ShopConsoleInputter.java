package gamble.shop;

import gamble.Inputter;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class ShopConsoleInputter implements ShopInputter {
  private final Inputter inputter;
  private final ShopConsoleOutputter shopConsoleOutputter;

  @Override
  public boolean shouldVisitShop() {
    return inputter.yesOrNo();
  }

  @Override
  public Optional<Purchasable> selectItem(List<Purchasable> items) {
    Purchasable item = null;
    while(item == null) {
      int num = inputter.chooseNumber();
      if(num == 0) {
        return Optional.empty();
      }
      if(num > items.size()) {
        shopConsoleOutputter.incorrectSelection();
        continue;
      }
      item = items.get(num - 1);
    }

    return Optional.of(item);
  }
}