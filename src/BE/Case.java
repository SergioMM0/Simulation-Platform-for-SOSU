package BE;

public class Case {

    private int id ;
    private String name ;
    private String conditionDescription;
    private String cause;
    private String causalDiagnose;
    private String causalCondition;
    private String citizenGoal;
    private int categoryid ;
    private int subcategoryid ;
    public Case(int id, String name,String conditionDescription,String cause, String causalDiagnose,String causalCondition,String citizenGoal , int categoryid , int subcategoryid) {
        this.id = id;
        this.name = name;
        this.conditionDescription = conditionDescription;
        this.cause = cause;
        this.causalDiagnose = causalDiagnose;
        this.causalCondition = causalCondition;
        this.citizenGoal = citizenGoal;
        this.categoryid = categoryid ;
        this.subcategoryid = categoryid;
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

    public String getConditionDescription() {
        return conditionDescription;
    }

    public void setConditionDescription(String conditionDescription) {
        this.conditionDescription = conditionDescription;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public String getCausalDiagnose() {
        return causalDiagnose;
    }

    public void setCausalDiagnose(String causalDiagnose) {
        this.causalDiagnose = causalDiagnose;
    }

    public String getCausalCondition() {
        return causalCondition;
    }

    public void setCausalCondition(String causalCondition) {
        this.causalCondition = causalCondition;
    }

    public String getCitizenGoal() {
        return citizenGoal;
    }

    public void setCitizenGoal(String citizenGoal) {
        this.citizenGoal = citizenGoal;
    }

    public int getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(int categoryid) {
        this.categoryid = categoryid;
    }

    public int getSubcategoryid() {
        return subcategoryid;
    }

    public void setSubcategoryid(int subcategoryid) {
        this.subcategoryid = subcategoryid;
    }

    @Override
    public String toString() {
        return "Case{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", conditionDescription='" + conditionDescription + '\'' +
                ", cause='" + cause + '\'' +
                ", causalDiagnose='" + causalDiagnose + '\'' +
                ", causalCondition='" + causalCondition + '\'' +
                ", citizenGoal='" + citizenGoal + '\'' +
                '}';
    }
}
