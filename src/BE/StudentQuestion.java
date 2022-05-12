package BE;

public class StudentQuestion {
    private int id;
    private String category;
    private String title;
    private String question;
    private String state0;
    private String state1;
    private String state2;
    private String state3;
    private String state4;
    private String state5 ;
    private int questionaireId;

    public StudentQuestion(int id, String category, String title, String question, String state0, String state1, String state2, String state3, String state4 , String state5) {
        this.id = id;
        this.category = category;
        this.title = title;
        this.question = question;
        this.state0 = state0;
        this.state1 = state1;
        this.state2 = state2;
        this.state3 = state3;
        this.state4 = state4;
        this.state5 = state5 ;
    }

    public StudentQuestion() {
    }

    public int getQuestionaireId() {
        return questionaireId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getState0() {
        return state0;
    }

    public void setState0(String state0) {
        this.state0 = state0;
    }

    public String getState1() {
        return state1;
    }

    public void setState1(String state1) {
        this.state1 = state1;
    }

    public String getState2() {
        return state2;
    }

    public void setState2(String state2) {
        this.state2 = state2;
    }

    public String getState3() {
        return state3;
    }

    public void setState3(String state3) {
        this.state3 = state3;
    }

    public String getState4() {
        return state4;
    }

    public void setState4(String state4) {
        this.state4 = state4;
    }

    public void setQuestionaireId(int questionaireId) {
        this.questionaireId=questionaireId;
    }

    public String getState5() {
        return state5;
    }

    public void setState5(String state5) {
        this.state5 = state5;
    }

    @Override
    public String toString() {
        return "StudentQuestion{" +
                "id=" + id +
                ", category='" + category + '\'' +
                ", title='" + title + '\'' +
                ", question='" + question + '\'' +
                ", state0='" + state0 + '\'' +
                ", state1='" + state1 + '\'' +
                ", state2='" + state2 + '\'' +
                ", state3='" + state3 + '\'' +
                ", state4='" + state4 + '\'' +
                ", state5='" + state5 + '\'' +
                ", questionaireId=" + questionaireId +
                '}';
    }
}
