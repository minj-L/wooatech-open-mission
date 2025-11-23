package stock.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import stock.dto.BuyingStock;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BuyingResult {
    private static final int TICK_SIZE = 10; // 호가 단위는 10으로 가정
    private static final int CURRENT_PRICE = 4200;

    private String stockName;
    private int finalPrice;
    private int quantity;

    // 1주씩 매수할 때마다 주가가 1호가 단위만큼 오른다고 가정
    public static BuyingResult fianlPrice(BuyingStock buyingStock) {
        int calcPrice = CURRENT_PRICE + (buyingStock.getQuantity() * TICK_SIZE);

        return new BuyingResult(
                buyingStock.getStockName(),
                calcPrice,
                buyingStock.getQuantity()
        );
    }
}
