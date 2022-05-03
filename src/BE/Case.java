package BE;

public class Case {

    private int id ;
    private String name ;
    private String Description_of_the_condition;
    private String Cause_text;
    private String Causal_diagnose;
    private String Causal_condition;
    private String Citizens_want_goal;

    public Case(int id, String name, String description_of_the_condition, String cause_text, String causal_diagnose, String causal_condition, String citizens_want_goal) {
        this.id = id;
        this.name = name;
        Description_of_the_condition = description_of_the_condition;
        Cause_text = cause_text;
        Causal_diagnose = causal_diagnose;
        Causal_condition = causal_condition;
        Citizens_want_goal = citizens_want_goal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription_of_the_condition() {
        return Description_of_the_condition;
    }

    public void setDescription_of_the_condition(String description_of_the_condition) {
        Description_of_the_condition = description_of_the_condition;
    }

    public String getCause_text() {
        return Cause_text;
    }

    public void setCause_text(String cause_text) {
        Cause_text = cause_text;
    }

    public String getCausal_diagnose() {
        return Causal_diagnose;
    }

    public void setCausal_diagnose(String causal_diagnose) {
        Causal_diagnose = causal_diagnose;
    }

    public String getCausal_condition() {
        return Causal_condition;
    }

    public void setCausal_condition(String causal_condition) {
        Causal_condition = causal_condition;
    }

    public String getCitizens_want_goal() {
        return Citizens_want_goal;
    }

    public void setCitizens_want_goal(String citizens_want_goal) {
        Citizens_want_goal = citizens_want_goal;
    }

    @Override
    public String toString() {
        return "Case{" + "id=" + id + ", name='" + name + '}';
    }
}
