public class PricingFactory {

    public PricingComponent room(int roomType) {
        return switch (roomType) {
            case LegacyRoomTypes.SINGLE -> new SingleRoom();
            case LegacyRoomTypes.DOUBLE -> new DoubleRoom();
            case LegacyRoomTypes.TRIPLE -> new TripleRoom();
            case LegacyRoomTypes.DELUXE -> new DeluxeRoom();
            default -> throw new IllegalArgumentException("Unknown room type");
        };
    }

    public PricingComponent addOn(AddOn addOn) {
        return switch (addOn) {
            case MESS -> new MessAdd();
            case LAUNDRY -> new LaundryAdd();
            case GYM -> new GymAdd();
        };
    }
}