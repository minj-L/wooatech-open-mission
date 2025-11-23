package stock.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import stock.constants.StockConstants;
import stock.dto.OrderStock;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderResult {
    public static int primitivePrice = 4200;

    private String stockName;
    private int finalPrice;
    private int quantity;

    // 1주씩 매수할 때마다 주가가 10호가 단위만큼 오른다고 가정
    // 매수
    public static OrderResult buyFinalPrice(OrderStock buyingStock) {
        return new OrderResult(
                buyingStock.getStockName(),
                buyStock(buyingStock.getQuantity()),
                buyingStock.getQuantity()
        );
    }

    // 매도
    public static OrderResult sellFinalPrice(OrderStock sellingStock) {
        return new OrderResult(
                sellingStock.getStockName(),
                sellStock(sellingStock.getQuantity()),
                sellingStock.getQuantity()
        );
    }

    private static synchronized int buyStock(int quantity) {
        return primitivePrice += quantity * StockConstants.TICK_SIZE;
    }

    private static synchronized int sellStock(int quantity) {
        return primitivePrice -= quantity * StockConstants.TICK_SIZE;
    }
}
