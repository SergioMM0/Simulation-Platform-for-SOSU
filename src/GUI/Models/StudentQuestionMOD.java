package GUI.Models;

import BE.StudentQuestion;
import BE.StudentQuestionaireAnswer;
import BLL.BLLFacade;
import BLL.BLLManager;
import DAL.util.DalException;

public class StudentQuestionMOD {
    private BLLFacade bll;
    private int questionaireId;     //to store current questionaire Id to use in answers

    public StudentQuestionMOD() {
        bll = new BLLManager();
    }

    public StudentQuestion GetFirstQuestion() {
        try {
            StudentQuestion firstQuestion = bll.getFirstQuestion();
            this.questionaireId = firstQuestion.getQuestionaireId();        //set questionaire Id
            return firstQuestion;
        } catch (DalException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void saveStudentQuestionAnswer(StudentQuestionaireAnswer answer) {
        answer.setQuestioanireId(questionaireId);           //using questionaire Id in answers
        try {
            bll.saveStudentQuestionAnswer(answer);
        } catch (DalException e) {
            e.printStackTrace();
        }
    }

    public StudentQuestion getNextQuestion(StudentQuestion question) {
        try {
            return bll.getNextQuestion(question);       //getting next question
        } catch (DalException e) {
            e.printStackTrace();
        }
        return null;
    }
}
