package Parking_Lot;

import java.util.List;

import Parking_Lot.Managers.SlotManager;
import Parking_Lot.Managers.TicketManager;
import Parking_Lot.Models.Level;
import Parking_Lot.Models.Slot;
import Parking_Lot.Models.Ticket;
import Parking_Lot.Models.Vehicle;
import Parking_Lot.Services.PricingService;

public class Parking_Lot {
    private List<Level> levels;
    private List<Gate> gates;
    private SlotManager slotManager;
    private TicketManager ticketManager;
    private PricingService pricingService;

    public Parking_Lot(List<Level> levels,
                      List<Gate> gates,
                      SlotManager slotManager,
                      TicketManager ticketManager,
                      PricingService pricingService) {

        this.levels = levels;
        this.gates = gates;
        this.slotManager = slotManager;
        this.ticketManager = ticketManager;
        this.pricingService = pricingService;
    }

    public Ticket parkVehicle(Vehicle vehicle) {
        Slot slot = slotManager.assignSlot(vehicle.getType());
        if (slot == null) throw new RuntimeException("No slot available");
        return ticketManager.createTicket(vehicle, slot);
    }

    public double exitVehicle(String ticketId) {
        Ticket ticket = ticketManager.closeTicket(ticketId);
        double amount = pricingService.calculate(ticket);
        slotManager.releaseSlot(ticket.getSlot());
        return amount;
    }
}