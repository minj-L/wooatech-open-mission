package stock.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import stock.constants.StockConstants;
import stock.dto.OrderStock;

class OrderResultTest {

    @BeforeEach
    void setUp() {
        OrderResult.primitivePrice = 4200;
    }

    @Test
    void buyFinalPrice_return_correct_OrderResult() {
        int quantity = 5;

        OrderStock orderStock = new OrderStock("apple", quantity);

        OrderResult orderResult = OrderResult.buyFinalPrice(orderStock);

        int expectedPrice = 4200 + (quantity * StockConstants.TICK_SIZE);

        assertThat(orderResult.getStockName()).isEqualTo("apple");
        assertThat(orderResult.getQuantity()).isEqualTo(5);
        assertThat(orderResult.getFinalPrice()).isEqualTo(expectedPrice);
    }

    @Test
    void sellFinalPrice_return_correct_OrderResult() {
        int quantity = 2;

        OrderStock orderStock = new OrderStock("apple", quantity);

        OrderResult orderResult = OrderResult.sellFinalPrice(orderStock);

        int expectedPrice = 4200 - (quantity * StockConstants.TICK_SIZE);

        assertThat(orderResult.getStockName()).isEqualTo("apple");
        assertThat(orderResult.getQuantity()).isEqualTo(2);
        assertThat(orderResult.getFinalPrice()).isEqualTo(expectedPrice);
    }
}