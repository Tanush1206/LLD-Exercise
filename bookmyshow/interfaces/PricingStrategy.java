package bookmyshow.interfaces;

import bookmyshow.models.ShowSeat;

/**
 * Strategy interface for seat pricing.
 * Implementations can be chained (decorator pattern) or swapped freely.
 * Satisfies OCP: add new pricing rules without modifying existing classes.
 */
public interface PricingStrategy {
    double calculatePrice(ShowSeat showSeat);
}
