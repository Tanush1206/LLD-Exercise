package Parking_Lot.Models;

import java.time.LocalDateTime;

public class Ticket {
    private String id;
    private Vehicle vehicle;
    private Slot slot;
    private LocalDateTime entryTime;
    private LocalDateTime exitTime;
    private TicketStatus status;

    public Ticket(String id, Vehicle vehicle, Slot slot) {
        this.id = id;
        this.vehicle = vehicle;
        this.slot = slot;
        this.entryTime = LocalDateTime.now();
        this.status = TicketStatus.ACTIVE;
    }

    public void close() {
        this.exitTime = LocalDateTime.now();
        this.status = TicketStatus.CLOSED;
    }
    public String getId() {return id;}
    public Vehicle getVehicle() { return vehicle; }
    public Slot getSlot() { return slot; }
    public LocalDateTime getEntryTime() { return entryTime; }
    public LocalDateTime getExitTime() { return exitTime; }
}