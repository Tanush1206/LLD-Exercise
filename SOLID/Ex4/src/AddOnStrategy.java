public interface AddOnStrategy {
    double cost() ; 
}

class MessAddOn implements AddOnStrategy {
   public double cost() { return 1000.0; }
}

class LaundryAddOn implements AddOnStrategy {
   public double cost() { return 500.0; }
}

class GymAddOn implements AddOnStrategy {
   public double cost() { return 300.0; }
}