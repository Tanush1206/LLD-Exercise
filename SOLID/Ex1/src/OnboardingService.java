import java.util.*;

public class OnboardingService {
    private final StudentRepo repo;
    private final ConsoleOutput console;

    public OnboardingService(StudentRepo repo) { 
        this.repo = repo;
        this.console = new ConsoleOutput(repo);
    }

    public void registerFromRawInput(String raw) {
        
        System.out.println("INPUT: " + raw);

        final StudentParser parser = new StudentParser();
        StudentInput input = parser.parse(raw);

        String name = input.getName();
        String email = input.getEmail();
        String phone = input.getPhone();
        String program = input.getProgram();

        final StudentValidator validator = new StudentValidator();
        List<String> errors = validator.validate(input);

        if (!errors.isEmpty()) {
            System.out.println("ERROR: invalid input");
            for (String e : errors) {
                System.out.println("  " + e);
            }
            return;
        }

        String id = IdUtil.nextStudentId(repo.count());
        StudentRecord rec = new StudentRecord(id, name, email, phone, program);

        repo.save(rec);
        
        console.printSuccessful(rec);
        console.printDbDump();
    }
}
