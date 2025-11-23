package stock.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import stock.domain.BuyingResult;
import stock.dto.BuyingStock;

@Controller
public class TradingController {

    @MessageMapping("/trade/buyingStock")
    @SendTo("/stock/buyStock")
    public BuyingResult buyingStock(BuyingStock buyingStock) {
        return BuyingResult.finalPrice(buyingStock);
    }

    @MessageMapping("/trade/sellingStock")
    @SendTo("/stock/sellStock")
    public BuyingResult sellingStock(BuyingStock buyingStock) {
        return BuyingResult.sellFinalPrice(buyingStock);
    }
}
