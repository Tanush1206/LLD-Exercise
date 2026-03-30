public class NotificationValidator {

    public static void validateEmail(EmailNotification n) {
        if (n.email == null || !n.email.contains("@")) {
            throw new IllegalArgumentException("Invalid email");
        }
    }

    public static void validatePhone(String phone) {
        if (phone == null || phone.isBlank()) {
            throw new IllegalArgumentException("Invalid phone");
        }
    }
}