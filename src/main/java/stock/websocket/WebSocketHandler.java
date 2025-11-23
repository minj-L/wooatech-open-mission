package stock.websocket;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import stock.domain.BuyingResult;

@Controller
@RequiredArgsConstructor
public class WebSocketHandler {
    private final SimpMessagingTemplate template;

    public void sendBuyingResult(BuyingResult result) {
        template.convertAndSend("/stock/buyStock", result);
    }
}
