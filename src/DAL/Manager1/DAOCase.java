package DAL.Manager1;

import BE.Case;
import DAL.DataAccess.DataAccess;
import DAL.util.DalException;
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


    public void createCase(Case c ) throws DalException {
        try(Connection con = dataAccess.getConnection()) {
            String sql = "INSERT INTO [dbo].[Case] ( name ,Description_of_the_condition,CategoryName , SubCategoryName ,schoolid) " +
                    "VALUES (?,?,?,? , ?);" ;
            PreparedStatement prs = con.prepareStatement(sql);
            prs.setString(1 , c.getName());
            prs.setString(2 ,c.getConditionDescription());
            prs.setString(3,c.getCategory());
            prs.setString(4 , c.getSubCategory());
            prs.setInt(5 ,c.getSchoolID());
            prs.executeUpdate();
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
