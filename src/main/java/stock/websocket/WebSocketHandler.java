package stock.websocket;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import stock.domain.OrderResult;

@Controller
@RequiredArgsConstructor
public class WebSocketHandler {
    private final SimpMessagingTemplate template;

    public void sendBuyingResult(OrderResult buyingResult) {
        template.convertAndSend("/stock/buyStock", buyingResult);
    }

    public void sendSellingResult(OrderResult sellingResult) {
        template.convertAndSend("/stock/sellStock", sellingResult);
    }
}
