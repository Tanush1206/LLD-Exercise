public class EventLeadTool implements CreateEvent {
    private final EventPlanner planner;
    public EventLeadTool(EventPlanner planner) { this.planner = planner; }

    public void createEvent(String name, double budget) { planner.create(name, budget); }
    public int getEventsCount() { return planner.count(); }
}
