import java.util.*;

public class Validator {

    private static final Set<String> VALID_PROGRAMS = Set.of("CSE","AI","SWE");

    public ValidationResult validate(Map<String,String> kv) {
        List<String> errors = new ArrayList<>();

        String name = kv.getOrDefault("name","");
        String email = kv.getOrDefault("email","");
        String phone = kv.getOrDefault("phone","");
        String program = kv.getOrDefault("program","");

        if (name.isBlank()) errors.add("name is required");
        if (email.isBlank() || !email.contains("@")) errors.add("email is invalid");
        if (phone.isBlank() || !phone.chars().allMatch(Character::isDigit)) errors.add("phone is invalid");
        if (!VALID_PROGRAMS.contains(program)) errors.add("program is invalid");

        return new ValidationResult(errors);
    }
}