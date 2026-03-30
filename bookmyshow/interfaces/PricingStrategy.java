package bookmyshow.interfaces;

import bookmyshow.models.ShowSeat;


public interface PricingStrategy {
    double calculatePrice(ShowSeat showSeat);
}
