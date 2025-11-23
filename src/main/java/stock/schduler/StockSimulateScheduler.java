package stock.schduler;

import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import stock.domain.OrderResult;
import stock.service.SimulatorService;
import stock.websocket.WebSocketHandler;

@Component
@AllArgsConstructor
public class StockSimulateScheduler {
    private final WebSocketHandler webSocketHandler;
    private final SimulatorService simulatorService;

    @Scheduled(fixedRate = 1000)
    public void buyingSimulator() {
        int randomBuyShare = simulatorService.generateBuyRandomShare();
        OrderResult result = OrderResult.buyFinalPrice(
                simulatorService.simulBuyingStock(randomBuyShare)
        );

        webSocketHandler.sendBuyingResult(result);
    }

    @Scheduled(fixedRate = 5000)
    public void sellingSimulator() {
        int randomSellShare = simulatorService.generateSellRandomShare();
        OrderResult sellingResult = OrderResult.sellFinalPrice(
                simulatorService.simulSellingStock(randomSellShare)
        );

        webSocketHandler.sendSellingResult(sellingResult);
    }
}
