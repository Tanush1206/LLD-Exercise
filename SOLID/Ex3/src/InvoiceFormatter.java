import java.util.List;
import java.util.Map;

public class InvoiceFormatter {
    // pointless wrapper (smell)
    public static String identityFormat(String invId,
            List<OrderLine> lines,
            Map<String, MenuItem> menu,
            double subtotal,
            double taxPct,
            double tax,
            double discount,
            double total) {
                
        StringBuilder out = new StringBuilder();

        out.append("Invoice# ").append(invId).append("\n");

        for (OrderLine l : lines) {
        MenuItem item = menu.get(l.itemId);
        double lineTotal = item.price * l.qty;

        out.append("- " 
            + item.name 
            + " x" 
            + l.qty 
            + " = " 
            + lineTotal 
            + "\n");
        }

        out.append(String.format("Subtotal: %.2f\n", subtotal));
        out.append(String.format("Tax(%.0f%%): %.2f\n", taxPct, tax));
        out.append(String.format("Discount: -%.2f\n", discount));
        out.append(String.format("TOTAL: %.2f\n", total));

        return out.toString();
    }
}
