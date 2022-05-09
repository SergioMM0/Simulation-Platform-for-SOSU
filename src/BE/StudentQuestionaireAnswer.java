package BE;

public class StudentQuestionaireAnswer {
    private int id;
    private int questionId;
    private int state;
    private int questioanireId;

    public StudentQuestionaireAnswer(int id, int questionId, int state, int questioanireId) {
        this.id = id;
        this.questionId = questionId;
        this.state = state;
        this.questioanireId = questioanireId;
    }

    public int getQuestioanireId() {
        return questioanireId;
    }

    public void setQuestioanireId(int questioanireId) {
        this.questioanireId = questioanireId;
    }

    public StudentQuestionaireAnswer() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
