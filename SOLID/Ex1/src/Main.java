public class Main {
    public static void main(String[] args) {
        System.out.println("=== Student Onboarding ===");
        
        StudentRepo repo = new FakeDb();
        OnboardingService svc = new OnboardingService(repo);

        String raw = "name=Riya;email=riya@sst.edu;phone=9876543210;program=CSE";
        svc.registerFromRawInput(raw);

    }
}
