public interface RoomStrategy {
    double basePrice() ; 
}

class SingleRoom implements RoomStrategy {
   public double basePrice() { return 14000.0; }
}

class DoubleRoom implements RoomStrategy {
   public double basePrice() { return 15000.0; }
}

class TripleRoom implements RoomStrategy {
   public double basePrice() { return 12000.0; }
}

class DeluxeRoom implements RoomStrategy {
   public double basePrice() { return 16000.0; }
}