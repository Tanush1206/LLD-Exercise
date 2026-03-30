package Parking_Lot.Managers;


import java.util.List;
import Parking_Lot.Models.Level;
import Parking_Lot.Models.Slot;
import Parking_Lot.Models.VehicleType;
import Parking_Lot.Strategies.SlotStrategy;

public class SlotManager {
    private List<Level> levels;
    private SlotStrategy strategy;

    public SlotManager(List<Level> levels, SlotStrategy strategy) {
        this.levels = levels;
        this.strategy = strategy;
    }

    public Slot assignSlot(VehicleType type) {
        Slot slot = strategy.findSlot(levels, type);
        if (slot != null) slot.occupy();
        return slot;
    }

    public void releaseSlot(Slot slot) {
        slot.release();
    }
}