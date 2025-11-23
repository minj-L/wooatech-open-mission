package stock.service;

import org.springframework.stereotype.Service;
import stock.dto.OrderStock;

@Service
public class SimulatorService {
    private final int MAX_BUY_SHARE = 5000;
    private final int MAX_SELL_SHARE = 1000;

    public OrderStock simulBuyingStock(int share) {
        return new OrderStock("apple", share);
    }

    public OrderStock simulSellingStock(int share) {
        return new OrderStock("apple", share);
    }

    public int generateBuyRandomShare() {
        return (int) (Math.random() * MAX_BUY_SHARE) + 1;
    }

    public int generateSellRandomShare() {
        return (int) (Math.random() * MAX_SELL_SHARE) + 1;
    }
}
