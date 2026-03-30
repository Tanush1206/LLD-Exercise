import java.util.*;

public class CafeteriaSystem {
    private final Map<String, MenuItem> menu = new LinkedHashMap<>();
    private final Storeinvoice store;
    private final TaxPolicy taxPolicy;
    private final DiscountPolicy discountPolicy;    
    private int invoiceSeq = 1000;

    public CafeteriaSystem(Storeinvoice store, TaxPolicy taxPolicy, DiscountPolicy discountPolicy) {
        this.store = store;
        this.taxPolicy = taxPolicy;
        this.discountPolicy = discountPolicy;
    }

    public void addToMenu(MenuItem i) { menu.put(i.id, i); }

    // Intentionally SRP-violating: menu mgmt + tax + discount + format + persistence.
    public void checkout(String customerType, List<OrderLine> lines) {
        String invId = "INV-" + (++invoiceSeq);
        
        double subtotal = PriceService.calculateSubtotal(lines, menu);

        double taxPct = taxPolicy.taxPercent(customerType);
        double tax = subtotal * (taxPct / 100.0);

        double discount = discountPolicy.discountAmount(customerType, subtotal, lines.size());

        double total = subtotal + tax - discount;
        
        String printable = InvoiceFormatter.identityFormat(
            invId,
            lines,
            menu,
            subtotal,
            taxPct,
            tax,
            discount,
            total
        );

        System.out.print(printable);

        store.save(invId, printable);
    
        System.out.println("Saved invoice: " + invId + " (lines=" + store.countLines(invId) + ")");
    
    }
}
