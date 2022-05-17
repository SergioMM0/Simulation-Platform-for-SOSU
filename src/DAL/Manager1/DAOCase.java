package DAL.Manager1;

import BE.Case;
import BE.Group;
import BE.Patient;
import DAL.DataAccess.DataAccess;
import DAL.util.DalException;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOCase {

    private final DataAccess dataAccess;

    public DAOCase() {
        dataAccess = new DataAccess();
    }

    public List<Case> getAllCases(int schoolid) throws DalException {
        ArrayList<Case> cases = new ArrayList<>();
        try(Connection connection = dataAccess.getConnection()){
            String sql = "select * from [Case] where schoolid  = ? ";
            PreparedStatement prs = connection.prepareStatement(sql);
            prs.setInt(1 , schoolid);
            prs.execute();
            ResultSet rs = prs.getResultSet();
            while(rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String description_of_the_condition = rs.getString("Description_of_the_condition");
                String category = rs.getString("CategoryName");
                String subcategory = rs.getString("SubCategoryName");
                int schoolID = rs.getInt("schoolid");

                Case c = new Case(id,name,description_of_the_condition,category,subcategory,schoolID);

                cases.add(c);

            }
            return cases;
        } catch (SQLException e) {
            throw new DalException("Connection Lost" , e);
        }
    }

    public List<Case> getCasesAssignedTo(Group group)throws DalException{
        ArrayList<Case> cases = new ArrayList<>();
        try(Connection con = dataAccess.getConnection()) {
            String sql = "select [Case].id , [Case].Description_of_the_condition , [Case].[name] , [Case].CategoryName , [Case].SubCategoryName , [Case].[schoolid] from [Case] join [SickPatient] on [Case].id = [SickPatient].[caseid]" +
                    "where [SickPatient].[Groupid] = ?";
            PreparedStatement prs = con.prepareStatement(sql);
            prs.setInt(1 , group.getId());
            prs.execute();
            ResultSet rs = prs.getResultSet();
            while (rs.next()){
                int id = rs.getInt("[Case].id");
                String name = rs.getString("[Case].[name]");
                String condition = rs.getString("[Case].Description_of_the_condition");
                String cat = rs.getString("[Case].CategoryName");
                String subcat = rs.getString("[Case].SubCategoryName");
                int schoolid = rs.getInt("[Case].[schoolid]");
                Case c = new Case(id ,name ,condition , cat ,subcat , schoolid );
                cases.add(c);
            }
            return cases;
        } catch (SQLException e) {
           throw new DalException("Couldnot retrive list of cases from the database " , e);
        }
    }

    public void assignCasetoPatient(Patient patient, Case c) throws DalException {
        try(Connection con = dataAccess.getConnection()){
            String sql = "insert into SickPatient (patientid , caseid) values  (?,?)";
            PreparedStatement prs = con.prepareStatement(sql);
            prs.setInt(1 ,patient.getId());
            prs.setInt(2 , c.getId());
            prs.executeUpdate();
        } catch (SQLException e){
            throw new DalException("Connection Lost" , e );
        }
    }


    public void assignCaseToPatientToGroup(Patient p, Case c, Group g) throws DalException {
        try(Connection con = dataAccess.getConnection()) {
            String sql = "insert into SickPatient (patientid , caseid , Groupid) values  (?,?,?)";
            PreparedStatement prs = con.prepareStatement(sql);
            prs.setInt(1 , p.getId());
            prs.setInt(2 ,c.getId());
            prs.setInt(3 ,g.getId());
            prs.execute();
        } catch (SQLException e) {
            throw new DalException("Cant preform this task at this moment " , e);
        }
    }


    public Case createCase(Case newCase) throws DalException {
        try(Connection con = dataAccess.getConnection()) {
            String sql = "INSERT INTO [dbo].[Case] ( name ,Description_of_the_condition,CategoryName , SubCategoryName ,schoolid) " +
                    "VALUES (?,?,?,? , ?);" ;
            String sql2 = "SELECT [id] FROM [dbo].[Case] WHERE [name] = ? AND [Description_of_the_condition] = ? AND [CategoryName] = ? AND [SubCategoryName] = ? AND [schoolid] = ?";
            PreparedStatement prs = con.prepareStatement(sql);
            PreparedStatement prs2 = con.prepareStatement(sql2);
            prs.setString(1 , newCase.getName());
            prs.setString(2 ,newCase.getConditionDescription());
            prs.setString(3,newCase.getCategory());
            prs.setString(4 , newCase.getSubCategory());
            prs.setInt(5 ,newCase.getSchoolID());
            prs.executeUpdate();
            prs2.setString(1,newCase.getName());
            prs2.setString(2,newCase.getConditionDescription());
            prs2.setString(3,newCase.getCategory());
            prs2.setString(4,newCase.getSubCategory());
            prs2.setInt(5,newCase.getSchoolID());
            prs2.execute();
            ResultSet rs = prs2.getResultSet();
            while(rs.next()){
                newCase.setId(rs.getInt("id"));
            }
            return newCase;
        } catch (SQLException e) {
            throw new DalException("Connection Lost" , e);
        }

    }


    public void updateCase(Case c) throws DalException {
        try(Connection con = dataAccess.getConnection()){
            String sql = "Update [Case] set name = ? , description_of_the_condition = ? , CategoryName = ? , SubCategoryName = ? " +
                    " where id = ? ";
            PreparedStatement prs = con.prepareStatement(sql);
            prs.setString(1 , c.getName());
            prs.setString(2 ,c.getConditionDescription());
            prs.setString(3 , c.getCategory());
            prs.setString(4 , c.getSubCategory());
            prs.setInt(5,c.getId());
            prs.executeUpdate();

        } catch (SQLException e) {
            throw new DalException("Connection Lost" , e);
        }
    }


    public void deleteCase(Case c) throws DalException {
        try(Connection con = dataAccess.getConnection()){
            String sql = " DELETE FROM [Case] WHERE id = ?";
            PreparedStatement prs = con.prepareStatement(sql);
            prs.setInt(1 , c.getId());
            prs.executeUpdate();
        } catch (SQLException e) {
            throw new DalException("Connection Lost" , e);

        }
    }

}
