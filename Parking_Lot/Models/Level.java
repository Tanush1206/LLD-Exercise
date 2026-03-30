package Parking_Lot.Models;

import java.util.List;

public class Level {
    private String id;
    private List<Slot> slots;

    public Level(String id, List<Slot> slots) {
        this.id = id;
        this.slots = slots;
    }

    public List<Slot> getSlots() { return slots; }
}