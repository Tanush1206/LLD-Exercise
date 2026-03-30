public class EmailSender implements NotificationSender<EmailNotification> {
    private final AuditService audit ; 

    public EmailSender(AuditService audit){
        this.audit = audit ; 
    }

    public void send(EmailNotification n){
        System.out.println("EMAIL -> to=" + n.email +
                " subject=" + n.subject +
                " body=" + n.body);
        audit.log("EMAIL sent to:" + n.email);
    }
}
