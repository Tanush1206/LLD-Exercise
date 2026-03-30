package Parking_Lot.Services;

import java.util.HashMap;
import java.util.Map;
import java.time.Duration;
import Parking_Lot.Models.SlotType;
import Parking_Lot.Models.Ticket;

public class PricingService {
     private Map<SlotType, Double> rates = new HashMap<>();

    public PricingService() {
        rates.put(SlotType.SMALL, 10.0);
        rates.put(SlotType.MEDIUM, 20.0);
        rates.put(SlotType.LARGE, 30.0);
    }

    public double calculate(Ticket ticket) {
        long hours = Duration.between(
                ticket.getEntryTime(),
                ticket.getExitTime()
        ).toHours();

        if (hours == 0) hours = 1;

        return hours * rates.get(ticket.getSlot().getType());
    }
}