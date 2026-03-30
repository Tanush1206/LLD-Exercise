public class CodeGrader implements CodeGraderservice {
    private final Rubric r;

    public CodeGrader(Rubric r) {
        this.r = r;
    }

    @Override
    public int grade(Submission s) {
        // fake scoring (but deterministic)
        int base = Math.min(80, 50 + s.code.length() % 40);
        return base + r.bonus;
    }
}
