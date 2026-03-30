package com.example.tickets;

import java.util.ArrayList;
import java.util.List;

/**
 * INTENTION: A ticket should be an immutable record-like object.
 *
 * CURRENT STATE (BROKEN ON PURPOSE):
 * - mutable fields
 * - multiple constructors
 * - public setters
 * - tags list can be modified from outside
 * - validation is scattered elsewhere
 *
 * TODO (student): refactor to immutable + Builder.
 */
public class IncidentTicket {

    private final String id;
    private final String reporterEmail;
    private final String title;

    private final String description;
    private final String priority;       // LOW, MEDIUM, HIGH, CRITICAL
    private final List<String> tags;     // mutable leak
    private final String assigneeEmail;
    private final boolean customerVisible;
    private final Integer slaMinutes;    // optional
    private final String source;         // e.g. "CLI", "WEBHOOK", "EMAIL"

    public IncidentTicket(Builder builder) {
        this.id = builder.id;
        this.reporterEmail = builder.reporterEmail;
        this.title = builder.title;
        this.description = builder.description;
        this.priority = builder.priority;
        this.tags = List.copyOf(builder.tags);  // BROKEN: retains external reference
        this.assigneeEmail = builder.assigneeEmail;
        this.customerVisible = builder.customerVisible;
        this.slaMinutes = builder.slaMinutes;
        this.source = builder.source;
    }

    // Getters
    public String getId() { return id; }
    public String getReporterEmail() { return reporterEmail; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getPriority() { return priority; }
    public List<String> getTags() { return tags; } // BROKEN: leaks internal list
    public String getAssigneeEmail() { return assigneeEmail; }
    public boolean isCustomerVisible() { return customerVisible; }
    public Integer getSlaMinutes() { return slaMinutes; }
    public String getSource() { return source; }

    public static class Builder {
        private final String id ; 
        private final String reporterEmail;
        private final String title;

        private String description;
        private String priority = "MEDIUM";
        private List<String> tags = new ArrayList<>();
        private String assigneeEmail;
        private boolean customerVisible = false;
        private Integer slaMinutes;
        private String source = "CLI";

        public Builder(String id , String reporterEmail , String title){
            Validation.requireTicketId(id);
            Validation.requireEmail(reporterEmail, "reporterEmail");
            Validation.requireNonBlank(title, "title");

            this.id = id ; 
            this.reporterEmail = reporterEmail;
            this.title = title;
        }

        public Builder description(String description){
            this.description = description ; 
            return this ; 
        }

        public Builder priority(String priority){
            Validation.requireOneOf(priority, "priority", "LOW", "MEDIUM", "HIGH", "CRITICAL");
            this.priority = priority ;
            return this ;
        }

        public Builder addTag(String tag){
            this.tags.add(tag) ; 
            return this ; 
        }

        public Builder assigneeEmail(String email){
            if(email != null){
                Validation.requireEmail(email, "assigneeEmail");
            }
            this.assigneeEmail = email ;
            return this ;
        }

        public Builder customerVisible(boolean visible){
            this.customerVisible = visible ;
            return this ;
        }

        public Builder slaMinutes(Integer slaMinutes){
            Validation.requireRange(slaMinutes, 1, 10000, "slaMinutes");
            this.slaMinutes = slaMinutes ;
            return this ;
        }

        public Builder source(String source){
            this.source = source ;
            return this ;
        }

        public IncidentTicket build(){
            return new IncidentTicket(this);
        }
    }

    public Builder toBuilder(){
        Builder b = new Builder(this.id , this.reporterEmail , this.title);
        b.description=this.description;
        b.priority=this.priority;
        b.tags = new ArrayList<>(this.tags);
        b.assigneeEmail=this.assigneeEmail;
        b.customerVisible=this.customerVisible;
        b.slaMinutes=this.slaMinutes;
        b.source=this.source;
        return b ;

    }

    @Override
    public String toString() {
        return "IncidentTicket{" +
                "id='" + id + '\'' +
                ", reporterEmail='" + reporterEmail + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", priority='" + priority + '\'' +
                ", tags=" + tags +
                ", assigneeEmail='" + assigneeEmail + '\'' +
                ", customerVisible=" + customerVisible +
                ", slaMinutes=" + slaMinutes +
                ", source='" + source + '\'' +
                '}';
    }
}
