public class SmsSender implements NotificationSender<SmsNotification> {

    private final AuditService audit;

    public SmsSender(AuditService audit) {
        this.audit = audit;
    }

    public void send(SmsNotification n) {
            System.out.println("SMS -> to=" + n.phoneNumber +
                    " body=" + n.body);

            audit.log("SMS sent to " + n.phoneNumber);
    }
}