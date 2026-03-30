public interface EligibilityRule {
   String check(StudentProfile s) ; 
}

class DisciplinaryRule implements EligibilityRule{
    public String check(StudentProfile s) {
        if(s.disciplinaryFlag != LegacyFlags.NONE){
            return "disciplinary flag present" ;
        }
        return null;
    }
}

class CgrRule implements EligibilityRule{
    public String check(StudentProfile s) {
        if(s.cgr < 8.0){
            return "CGR below 8.0" ;
        }
        return null;
    }
}

class AttendanceRule implements EligibilityRule{
    public String check(StudentProfile s) {
        if(s.attendancePct < 75){
            return "Attendance below 75%" ;
        }
        return null;
    }
}

class CreditsRule implements EligibilityRule{
    public String check(StudentProfile s) {
        if(s.earnedCredits < 20){
            return "credits below 20" ;
        }
        return null;
    }
}