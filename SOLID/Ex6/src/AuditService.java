import java.util.*;

public interface AuditService {
    void log(String message) ; 
    int size() ; 
}

class AuditLog implements AuditService {
    private final List<String> logs = new ArrayList<>() ; 

    public void log(String message){
        logs.add(message) ; 
    }

    public int size(){
        return logs.size() ; 
    }
}
