package Parking_Lot.Models;

public class Slot {
    private String id;
    private SlotType type;
    private boolean isOccupied;

    public Slot(String id, SlotType type) {
        this.id = id;
        this.type = type;
        this.isOccupied = false;
    }

    public String getId() { return id; }
    public SlotType getType() { return type; }
    public boolean isOccupied() { return isOccupied; }

    public void occupy() { this.isOccupied = true; }
    public void release() { this.isOccupied = false; }
}