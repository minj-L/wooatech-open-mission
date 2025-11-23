package stock.service;

import org.springframework.stereotype.Service;
import stock.dto.BuyingStock;

@Service
public class SimulatorService {
    private final int MAX_SHARE = 5000;

    public BuyingStock simulBuyintStock(int share) {
        return new BuyingStock("apple", share);
    }

    public int generateRandomShare() {
        return (int) (Math.random() * MAX_SHARE) + 1;
    }
}
