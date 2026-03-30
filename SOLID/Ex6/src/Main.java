public class Main {
    public static void main(String[] args) {
        System.out.println("=== Notification Demo ===");

        AuditService audit = new AuditLog() ; 

        EmailNotification email = new EmailNotification(
            "Welcome" , "Hello and Welcome to SST!" , "riya@sst.edu" 
        );

        SmsNotification sms = new SmsNotification(
            "Welcome Hello and Welcome to SST!" , "+91-9876543210" 
        );

        WhatsAppNotification wa = new WhatsAppNotification(
            "Welcome Hello and Welcome to SST!" , "+91-9876543210"  
        );


        NotificationValidator.validateEmail(email) ; 
        NotificationValidator.validatePhone(sms.phoneNumber) ; 
        NotificationValidator.validatePhone(wa.phoneNumber) ; 

        NotificationSender<EmailNotification> emailSender = new EmailSender(audit) ; 
        NotificationSender<SmsNotification> smsSender = new SmsSender(audit) ; 
        NotificationSender<WhatsAppNotification> waSender = new WhatsAppSender(audit) ;

        emailSender.send(email) ; 
        smsSender.send(sms) ; 
        waSender.send(wa) ;

        System.out.println("Audit entries: " + audit.size());

    }
}
