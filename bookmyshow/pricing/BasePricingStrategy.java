package bookmyshow.pricing;

import bookmyshow.enums.SeatType;
import bookmyshow.interfaces.PricingStrategy;
import bookmyshow.models.ShowSeat;

import java.util.EnumMap;
import java.util.Map;

/**
 * Returns a flat base price depending on seat type.
 * Admin can subclass or wrap this to change floor prices.
 */
public class BasePricingStrategy implements PricingStrategy {

    private final Map<SeatType, Double> basePriceMap;

    public BasePricingStrategy() {
        basePriceMap = new EnumMap<>(SeatType.class);
        basePriceMap.put(SeatType.SILVER,   150.0);
        basePriceMap.put(SeatType.GOLD,     250.0);
        basePriceMap.put(SeatType.PLATINUM, 400.0);
    }

    @Override
    public double calculatePrice(ShowSeat showSeat) {
        return basePriceMap.getOrDefault(showSeat.getSeat().getSeatType(), 150.0);
    }
}
