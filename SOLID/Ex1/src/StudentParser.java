public class StudentParser {
    public StudentInput parse(String raw){
        String[] parts = raw.split(";");

        String name = "";
        String email = "";
        String phone = "";
        String program = "";

        for(String p : parts){
            String[] t = p.split("=", 2);
            if(t.length == 2){
                String key = t[0].trim();
                String value = t[1].trim();
                switch(key){
                    case "name": name = value; break;
                    case "email": email = value; break;
                    case "phone": phone = value; break;
                    case "program": program = value; break;
                }
            }
        }
        return new StudentInput(name, email, phone, program);
    }
}
