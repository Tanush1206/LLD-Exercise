import java.util.*;

public class FakeDb implements StudentRepo {
    private final List<StudentRecord> rows = new ArrayList<>();

    public void save(StudentRecord r) { rows.add(r); }
    public int count() { return rows.size(); }
    public List<StudentRecord> findAll() { return Collections.unmodifiableList(rows); }
}
