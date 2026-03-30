package Parking_Lot.Strategies;
import java.util.List;
import Parking_Lot.Models.Level;
import Parking_Lot.Models.Slot;
import Parking_Lot.Models.VehicleType;

public interface SlotStrategy {
    Slot findSlot(List<Level> levels, VehicleType vehicleType);
}