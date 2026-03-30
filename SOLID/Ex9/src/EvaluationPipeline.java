public class EvaluationPipeline {
    // DIP violation: high-level module constructs concretes directly
    private final PlagiarismChecker plagiarismChecker;
    private final CodeGrader gradingService;
    private final ReportWriter reportService;

        public EvaluationPipeline(PlagiarismChecker pc, CodeGrader cg, ReportWriter rw) {
            this.plagiarismChecker = pc;
            this.gradingService = cg;
            this.reportService = rw;
        }

    public void evaluate(Submission sub) {

        int plag = plagiarismChecker.check(sub);
        System.out.println("PlagiarismScore=" + plag);

        int code = gradingService.grade(sub);
        System.out.println("CodeScore=" + code);

        String reportName = reportService.write(sub, plag, code);
        System.out.println("Report written: " + reportName);

        int total = plag + code;
        String result = (total >= 90) ? "PASS" : "FAIL";
        System.out.println("FINAL: " + result + " (total=" + total + ")");
    }
}
