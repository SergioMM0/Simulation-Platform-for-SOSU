package BE;

public class Case {

    private int id ;
    private String name ;
    private String conditionDescription;
    private String category;
    private String subCategory;
    private int schoolID;
    private boolean isGraded;

    public Case(int id, String name,String conditionDescription, String category, String subCategory,int schoolID) {
        this.id = id;
        this.name = name;
        this.conditionDescription = conditionDescription;
        this.category = category;
        this.subCategory = subCategory;
        this.schoolID = schoolID;
    }

    public Case(String name, String conditionDescription, String category, String subCategory, int schoolID) {
        this.name = name;
        this.conditionDescription = conditionDescription;
        this.category = category;
        this.subCategory = subCategory;
        this.schoolID = schoolID;
    }

    public Case(int id, String name, String conditionDescription, String category, String subCategory, int schoolID, boolean isGraded) {
        this.id = id;
        this.name = name;
        this.conditionDescription = conditionDescription;
        this.category = category;
        this.subCategory = subCategory;
        this.schoolID = schoolID;
        this.isGraded = isGraded;
    }

    public Case(String name, String conditionDescription, String category, String subCategory, int schoolID, boolean isGraded) {
        this.name = name;
        this.conditionDescription = conditionDescription;
        this.category = category;
        this.subCategory = subCategory;
        this.schoolID = schoolID;
        this.isGraded = isGraded;
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

    public int getSchoolID() {
        return schoolID;
    }

    public void setSchoolID(int schoolID) {
        this.schoolID = schoolID;
    }

    public boolean isGraded() {
        return isGraded;
    }

    public void setGraded(boolean graded) {
        isGraded = graded;
    }

    @Override
    public String toString() {
        return "Case{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", conditionDescription='" + conditionDescription + '\'' +
                ", category='" + category + '\'' +
                ", subCategory='" + subCategory + '\'' +
                ", schoolID=" + schoolID +
                '}';
    }
}
