package stock.schduler;

import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import stock.domain.BuyingResult;
import stock.service.SimulatorService;
import stock.websocket.WebSocketHandler;

@Component
@AllArgsConstructor
public class StockSimulateScheduler {
    private final SimulatorService simulatorService;
    private final WebSocketHandler webSocketHandler;

    @Scheduled(fixedRate = 2000)
    public void buyingSimulator() {
        int randomShare = simulatorService.generateRandomShare();
        BuyingResult result = BuyingResult.finalPrice(
                simulatorService.simulBuyintStock(randomShare)
        );

        webSocketHandler.sendBuyingResult(result);
    }
}
