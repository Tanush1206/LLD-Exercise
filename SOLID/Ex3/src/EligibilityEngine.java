import java.util.*;

public class EligibilityEngine {
    private final FakeEligibilityStore store;

    public EligibilityEngine(FakeEligibilityStore store) {
        this.store = store;
    }
    public void runAndPrint(StudentProfile s){
        ReportPrinter p = new ReportPrinter() ; 
        EligibilityEngineResult r = evaluate(s);
        p.print(s, r);
        store.save(s.rollNo, r.status) ; 
    }

    public EligibilityEngineResult evaluate(StudentProfile s){
        List<EligibilityRule> rules = List.of(
            new DisciplinaryRule() , 
            new CgrRule() ,
            new AttendanceRule() ,
            new CreditsRule()
        ) ; 

        List<String> reasons = new ArrayList<>() ;

        for(EligibilityRule r : rules){
            String res = r.check(s); 
            if(res!= null) reasons.add(res) ;
        }

        String status = reasons.isEmpty() ? "ELIGIBLE" : "NOT_ELIGIBLE" ;
        return new EligibilityEngineResult(status, reasons);
    }
}

class EligibilityEngineResult {
    public final String status;
    public final List<String> reasons;
    public EligibilityEngineResult(String status, List<String> reasons) {
        this.status = status;
        this.reasons = reasons;
    }
}
