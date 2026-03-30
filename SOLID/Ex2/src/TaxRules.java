public interface TaxRules {
    double taxPercent();
}

// 👇 Student Tax
class StudentTax implements TaxRules {
    public double taxPercent() {
        return 5.0;
    }
}

// 👇 Staff Tax
class StaffTax implements TaxRules {
    public double taxPercent() {
        return 2.0;
    }
}

// 👇 Default Tax
class DefaultTax implements TaxRules {
    public double taxPercent() {
        return 8.0;
    }
}