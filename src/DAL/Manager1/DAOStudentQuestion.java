package DAL.Manager1;

import BE.StudentQuestion;
import BE.StudentQuestionaireAnswer;
import DAL.DataAccess.DataAccess;
import DAL.util.DalException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAOStudentQuestion {
    private final DataAccess dataAccess;

    public DAOStudentQuestion() {
        dataAccess = new DataAccess();
    }

    public void addStudentQuestionAnswer(StudentQuestionaireAnswer answer) throws DalException {
        try (Connection con = dataAccess.getConnection()) {
            String sql = "insert  into [dbo].[StudentQuestionAnswer] (questionId,state,questionaireId )values  (?,?,?)";
            PreparedStatement prs = con.prepareStatement(sql);
            prs.setInt(1, answer.getQuestionId());
            prs.setInt(2, answer.getState());
            prs.setInt(3, answer.getQuestioanireId());
            prs.execute();

        } catch (SQLException e) {
            throw new DalException("Couldnot create a school ", e);
        }
    }

    public int addQuestionaire() throws DalException {
        try (Connection con = dataAccess.getConnection()) {
            String sql = "insert  into [dbo].[Questionaire] (date )values  (getdate())";
            PreparedStatement prs = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            prs.execute();
            ResultSet rs = prs.getGeneratedKeys();
            rs.next();
            return rs.getInt(1);

        } catch (SQLException e) {
            throw new DalException("Couldnot create a school ", e);
        }
    }

    public List<StudentQuestion> getAllQuestions() throws DalException {
        List<StudentQuestion> studentQuestions = new ArrayList<>();
        try (Connection con = dataAccess.getConnection()) {
            String sql = "Select * from StudentQuestion ";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                studentQuestions.add(new StudentQuestion(
                        rs.getInt("id"),
                        rs.getString("category"),
                        rs.getString("title"),
                        rs.getString("question"),
                        rs.getString("state0"),
                        rs.getString("state1"),
                        rs.getString("state2"),
                        rs.getString("state3"),
                        rs.getString("state4")
                ));
            }
            return studentQuestions;
        } catch (SQLException e) {
            throw new DalException("Couldn't retrieve a list of schools ", e);
        }
    }
}