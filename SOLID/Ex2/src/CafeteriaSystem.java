import java.util.*;

public class CafeteriaSystem {

    private final Map<String, MenuItem> menu = new LinkedHashMap<>();
    private final InvoiceRepository repo = new FileStore();
    private final InvoiceGenerator generator = new InvoiceGenerator();

    private int invoiceSeq = 1000; // ✅ FIX

    public void addToMenu(MenuItem i) {
        menu.put(i.id, i);
    }

    public void checkout(String customerType, List<OrderLine> lines) {

        String invId = "INV-" + (++invoiceSeq);

        // 🔹 Tax strategy
        TaxRules tax;
        if ("student".equalsIgnoreCase(customerType)) {
            tax = new StudentTax();
        } else if ("staff".equalsIgnoreCase(customerType)) {
            tax = new StaffTax();
        } else {
            tax = new DefaultTax();
        }

        // 🔹 Discount strategy
        DiscountRules discount;
        if ("student".equalsIgnoreCase(customerType)) {
            discount = new StudentDiscount();
        } else if ("staff".equalsIgnoreCase(customerType)) {
            discount = new StaffDiscount();
        } else {
            discount = new NoDiscount();
        }

        double subtotal = lines.stream()
                .mapToDouble(l -> menu.get(l.itemId).price * l.qty)
                .sum();

        double taxPct = tax.taxPercent();              // ✅ FIX
        double taxAmount = subtotal * (taxPct / 100);  // ✅ FIX
        double discountAmount = discount.discount(subtotal, lines.size());

        double total = subtotal + taxAmount - discountAmount; // ✅ FIX

        String invoice = generator.generate(
                invId,
                lines,
                menu,
                taxPct,
                discountAmount
        );

        System.out.print(invoice); // ✅ FIX

        repo.save(invId, invoice);
        System.out.println("Saved invoice: " + invId +
                " (lines=" + repo.countLines(invId) + ")");
    }
}