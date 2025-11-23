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

    @Scheduled(fixedRate = 1000)
    public void buyingSimulator() {
        int randomBuyShare = simulatorService.generateBuyRandomShare();
        BuyingResult result = BuyingResult.finalPrice(
                simulatorService.simulBuyingStock(randomBuyShare)
        );

        webSocketHandler.sendBuyingResult(result);
    }

    @Scheduled(fixedRate = 5000)
    public void sellingSimulator() {
        try {
            int randomSellShare = simulatorService.generateSellRandomShare();
            BuyingResult sellingResult = BuyingResult.sellFinalPrice(
                    simulatorService.simulSellingStock(randomSellShare)
            );

            webSocketHandler.sendSellingResult(sellingResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
