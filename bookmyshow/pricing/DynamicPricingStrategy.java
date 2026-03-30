package bookmyshow.pricing;

import bookmyshow.enums.ScreenType;
import bookmyshow.interfaces.PricingStrategy;
import bookmyshow.models.Show;
import bookmyshow.models.ShowSeat;

import java.time.DayOfWeek;

/**
 * Decorator over any PricingStrategy.
 * Applies surcharges based on show-level rules:
 *   - Weekend (Sat/Sun)   → +20%
 *   - Premium screen type → +15%
 *
 * OCP: new surcharge rules = new decorator, no changes to existing classes.
 * LSP: substitutes BasePricingStrategy cleanly wherever PricingStrategy is expected.
 */
public class DynamicPricingStrategy implements PricingStrategy {

    private final PricingStrategy base;

    public DynamicPricingStrategy(PricingStrategy base) {
        this.base = base;
    }

    @Override
    public double calculatePrice(ShowSeat showSeat) {
        double price = base.calculatePrice(showSeat);
        Show   show  = showSeat.getShow();

        DayOfWeek day = show.getStartTime().getDayOfWeek();
        if (day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY) {
            price *= 1.20;
        }

        ScreenType screenType = show.getScreen().getScreenType();
        if (screenType == ScreenType.IMAX || screenType == ScreenType.FOUR_DX) {
            price *= 1.15;
        }

        return Math.round(price * 100.0) / 100.0;
    }
}
