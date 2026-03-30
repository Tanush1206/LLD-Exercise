public interface DiscountRules {
    double discount(double subtotal , int lines) ;  ; 
}

// Student Discount: 10% off if subtotal >= 180.0
class StudentDiscount implements DiscountRules {
    public double discount(double subtotal, int lines) {
        if (subtotal >= 180.0) {
            return 10.0;
        }
        return 0.0;
    }
}

// Staff Discount: 15 off if 3 or more lines, else 5 off
class StaffDiscount implements DiscountRules {
    public double discount(double subtotal, int lines) {
        if (lines >= 3) {
            return 15.0;
        }
        return 5.0;
    }
}

// No Discount
class NoDiscount implements DiscountRules {
    public double discount(double subtotal, int lines) {
        return 0.0;
    }
}
