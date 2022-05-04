package DAL.Manager1;

import BE.Case;
import BE.Category;
import BE.Patient;
import BE.SubCategory;
import DAL.DataAccess.DataAccess;
import DAL.Interface.DAOCase;
import DAL.util.DalException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DALCase implements DAOCase {

    private final DataAccess dataAccess;

    public DALCase() {
        dataAccess = new DataAccess();
    }
    @Override
    public List<Case> getAllCases(int schoolid) throws DalException {
        ArrayList<Case> cases = new ArrayList<>();
        try(Connection connection = dataAccess.getConnection()){
            String sql = "SELECT  [case].[id]  ,  [case].[name] , Description_of_the_condition , Cause_text , Causal_diagnose , Causal_condition ,Citizens_want_goal" +
                    "from [Case]" +
                    "join SickPatient on [Case].[id] = [SickPatient].[caseid] "+
                    "join [Patient]  on  SickPatient.patientid  = [Patient].[id]"+
                    "join School  on  Patient.schoolid = School.id" +
                    "where School.id  = ? ";
            PreparedStatement prs = connection.prepareStatement(sql);
            prs.setInt(1 , schoolid);
            prs.execute();
            ResultSet rs = prs.getResultSet();
            while(rs.next()){
                int id = rs.getInt("id");
                String name = ("name");
                String description_of_the_condition = rs.getString("Description_of_the_condition");
                String cause_text = rs.getString("Cause_text");
                String causal_diagnose = rs.getString("Causal_diagnose");
                String causal_condition = rs.getString("Causal_condition");
                String citizens_want_goal = rs.getString("Citizens_want_goal");
                int catid = rs.getInt("catid");
                int subcat = rs.getInt("subid");
                Case c = new Case(newestidforCases() , name ,description_of_the_condition,cause_text,causal_diagnose,causal_condition,citizens_want_goal , catid ,subcat);
                cases.add(c);
            }
            return cases;
        } catch (SQLException e) {
            throw new DalException("Connection Lost" , e);
        }
    }

    @Override
    public Case createCase(String name, String description_of_the_condition, String cause_text, String causal_diagnose, String causal_condition, String citizens_want_goal , Category category , SubCategory subCategory) throws DalException {
        try(Connection con = dataAccess.getConnection()) {
            String sql = "INSERT INTO [dbo].[Case] ( name ,Description_of_the_condition, Cause_text,Causal_diagnose, Causal_condition ,Citizens_want_goal,catid , subid) VALUES (?,?,?,?,?,?,?);" ;
            PreparedStatement prs = con.prepareStatement(sql);
            prs.setString(1 , name);
            prs.setString(2 ,description_of_the_condition);
            prs.setString(3 , cause_text);
            prs.setString(4 ,causal_diagnose);
            prs.setString(5 , causal_condition);
            prs.setString(6 ,citizens_want_goal);
            prs.setInt(7 ,category.getId());
            prs.setInt(8 ,subCategory.getId());
            prs.executeUpdate();
            Case c = new Case(newestidforCases(), name,description_of_the_condition,cause_text,causal_diagnose,causal_condition,citizens_want_goal ,category.getId() , subCategory.getId());
            return c ;
        } catch (SQLException e) {
            throw new DalException("Connection Lost" , e);
        }

    }

    @Override
    public void updateCase(Case c, String name, String description_of_the_condition, String cause_text, String causal_diagnose, String causal_condition, String citizens_want_goal) throws DalException {
        try(Connection con = dataAccess.getConnection()){
            String sql = "Update Case set name = ? , description_of_the_condition = ? , cause_text = ?  , causal_diagnose = ? , causal_condition = ? , citizens_want_goal = ?  where id = ? ";
            PreparedStatement prs = con.prepareStatement(sql);
            prs.setString(1 , name);
            prs.setString(2 ,description_of_the_condition);
            prs.setString(3 , cause_text);
            prs.setString(4 ,causal_diagnose);
            prs.setString(5 , causal_condition);
            prs.setString(6 ,citizens_want_goal);
            prs.setInt(7,c.getId());
            prs.executeUpdate();

        } catch (SQLException e) {
            throw new DalException("Connection Lost" , e);
        }
    }

    @Override
    public void deleteCase(Case c) throws DalException {
        try(Connection con = dataAccess.getConnection()){
            String sql = "DELETE FROM Case WHERE id = ?";
            PreparedStatement prs = con.prepareStatement(sql);
            prs.setInt(1 , c.getId());
            prs.executeUpdate();
        } catch (SQLException e) {
            throw new DalException("Connection Lost" , e);

        }
    }

    private int newestidforCases() throws DalException {
        int newid = -1;

        try (Connection con = dataAccess.getConnection()) {
            String sql = "SELECT TOP(1) * FROM Case ORDER by id desc";
            PreparedStatement prs = con.prepareStatement(sql);
            ResultSet rs = prs.executeQuery();
            while (rs.next()) {
                newid = rs.getInt("id");
            }
        } catch (SQLException e) {
            throw new DalException("Connection Lost " , e);
        }
        return newid;
    }

}
