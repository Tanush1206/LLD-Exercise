package Parking_Lot.Strategies;

import Parking_Lot.Models.Level;
import Parking_Lot.Models.Slot;
import Parking_Lot.Models.SlotType;
import Parking_Lot.Models.VehicleType;

import java.util.List;


public class NearestSlotStartegy implements SlotStrategy {

    @Override
    public Slot findSlot(List<Level> levels, VehicleType vehicleType) {
        for (Level level : levels) {
            for (Slot slot : level.getSlots()) {
                if (!slot.isOccupied() && isCompatible(vehicleType, slot.getType())) {
                    return slot;
                }
            }
        }
        return null;
    }

    private boolean isCompatible(VehicleType vType, SlotType sType) {
        switch (vType) {
            case BIKE:
                return true;
            case CAR:
                return sType == SlotType.MEDIUM || sType == SlotType.LARGE;
            case BUS:
                return sType == SlotType.LARGE;
        }
        return false;
    }
}