package Parking_Lot.Models;

public class Vehicle {
    private String id;
    private VehicleType type;

    public Vehicle(String id, VehicleType type) {
        this.id = id;
        this.type = type;
    }

    public String getId() { return id; }
    public VehicleType getType() { return type; }
}