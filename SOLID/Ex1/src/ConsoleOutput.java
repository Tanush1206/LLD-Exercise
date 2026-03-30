public class ConsoleOutput {
    private final StudentRepo repo;

    public ConsoleOutput(StudentRepo repo) {
        this.repo = repo;
    }

    public void printSuccessful(StudentRecord rec) {
        System.out.println("OK: created student " + rec.id);
        System.out.println("Saved. Total students: " + repo.count());
        System.out.println("CONFIRMATION:");
        System.out.println(rec);
    }

    public void printDbDump() {
        System.out.println();
        System.out.println("-- DB DUMP --");
        System.out.print(TextTable.render3(repo.findAll()));
    }
}
