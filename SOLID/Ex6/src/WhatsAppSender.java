public class WhatsAppSender implements NotificationSender<WhatsAppNotification> {

    private final AuditService audit;

    public WhatsAppSender(AuditService audit) {
        this.audit = audit;
    }

    public void send(WhatsAppNotification n) {
        System.out.println("WA -> to=" + n.phoneNumber +
                " body=" + n.body);

        audit.log("WA sent to " + n.phoneNumber);
    }
}