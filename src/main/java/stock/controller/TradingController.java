package stock.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import stock.domain.OrderResult;
import stock.dto.OrderStock;

@Controller
public class TradingController {

    @MessageMapping("/trade/buyingStock")
    @SendTo("/stock/buyStock")
    public OrderResult buyingStock(OrderStock buyingStock) {
        return OrderResult.buyFinalPrice(buyingStock);
    }

    @MessageMapping("/trade/sellingStock")
    @SendTo("/stock/sellStock")
    public OrderResult sellingStock(OrderStock sellingStock) {
        return OrderResult.sellFinalPrice(sellingStock);
    }
}
