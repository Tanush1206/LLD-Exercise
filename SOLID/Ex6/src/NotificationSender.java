public interface NotificationSender<T> {
    void send(T notification) ; 
}

class EmailNotification{
    public final String subject ; 
    public final String body ; 
    public final String email ; 

    public EmailNotification(String subject , String body , String email){
        this.subject = subject ; 
        this.body = body ; 
        this.email = email ; 
    }
}

class SmsNotification{
    public final String body ; 
    public final String phoneNumber ; 

    public SmsNotification(String body , String phoneNumber){
        this.body = body ; 
        this.phoneNumber = phoneNumber ; 
    }
}

class WhatsAppNotification{
    public final String body ; 
    public final String phoneNumber ; 

    public WhatsAppNotification(String body , String phoneNumber){
        this.body = body ; 
        this.phoneNumber = phoneNumber ; 
    }
}
