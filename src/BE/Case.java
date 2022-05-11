package BE;

public class Case {

    private int id ;
    private String name ;
    private String conditionDescription;
    private String cause;
    private String category;
    private String subCategory;

    public Case(int id, String name,String conditionDescription,String cause, String category, String subCategory) {
        this.id = id;
        this.name = name;
        this.conditionDescription = conditionDescription;
        this.cause = cause;
        this.category = category;
        this.subCategory = subCategory;
    }

    public Case(String name, String conditionDescription, String cause, String category, String subCategory) {
        this.name = name;
        this.conditionDescription = conditionDescription;
        this.cause = cause;
        this.category = category;
        this.subCategory = subCategory;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(String subCategory) {
        this.subCategory = subCategory;
    }

    @Override
    public String toString() {
        return "Case{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", conditionDescription='" + conditionDescription + '\'' +
                ", cause='" + cause + '\'' +
                ", category='" + category + '\'' +
                ", subCategory='" + subCategory + '\'' +
                '}';
    }
}
