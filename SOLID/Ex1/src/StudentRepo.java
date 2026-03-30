import java.util.List;

public interface StudentRepo {
    void save(StudentRecord rec);
    int count();
    List<StudentRecord> findAll(); 
}
