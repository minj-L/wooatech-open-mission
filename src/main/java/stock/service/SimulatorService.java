package stock.service;

import org.springframework.stereotype.Service;
import stock.dto.BuyingStock;

@Service
public class SimulatorService {
    private final int MAX_BUY_SHARE = 5000;
    private final int MAX_SELL_SHARE = 1000;

    public BuyingStock simulBuyingStock(int share) {
        return new BuyingStock("apple", share);
    }

    public BuyingStock simulSellingStock(int share) {
        return new BuyingStock("apple", share);
    }

    public int generateBuyRandomShare() {
        return (int) (Math.random() * MAX_BUY_SHARE) + 1;
    }

    public int generateSellRandomShare() {
        return (int) (Math.random() * MAX_SELL_SHARE) + 1;
    }
}
