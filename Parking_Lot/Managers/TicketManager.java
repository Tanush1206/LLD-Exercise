package Parking_Lot.Managers;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import Parking_Lot.Models.Slot;
import Parking_Lot.Models.Ticket;
import Parking_Lot.Models.Vehicle;

public class TicketManager {
    private Map<String, Ticket> tickets = new HashMap<>();

    public Ticket createTicket(Vehicle vehicle, Slot slot) {
        String id = UUID.randomUUID().toString();
        Ticket ticket = new Ticket(id, vehicle, slot);
        tickets.put(id, ticket);
        return ticket;
    }

    public Ticket closeTicket(String id) {
        Ticket ticket = tickets.get(id);
        if (ticket != null) ticket.close();
        return ticket;
    }
}