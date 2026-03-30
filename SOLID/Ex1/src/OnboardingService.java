import java.util.*;

public class OnboardingService {

    private final StudentRepository db;
    private final InputParser parser;
    private final Validator validator;
    private final OutputPrinter printer;

    public OnboardingService(StudentRepository db) {
        this.db = db;
        this.parser = new InputParser();
        this.validator = new Validator();
        this.printer = new OutputPrinter();
    }

    public void registerFromRawInput(String raw) {

        printer.printInput(raw);

        Map<String,String> kv = parser.parse(raw);

        ValidationResult result = validator.validate(kv);

        if (!result.isValid()) {
            printer.printErrors(result.getErrors());
            return;
        }

        String id = IdUtil.nextStudentId(db.count());

        StudentRecord rec = new StudentRecord(
                id,
                kv.get("name"),
                kv.get("email"),
                kv.get("phone"),
                kv.get("program")
        );

        db.save(rec);

        printer.printSuccess(rec, db.count());
    }
}