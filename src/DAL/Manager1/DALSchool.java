package DAL.Manager1;

import BE.School;
import DAL.DataAccess.DataAccess;
import DAL.Interface.DAOSchool;
import DAL.util.DalException;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DALSchool implements DAOSchool {

    private final DataAccess dataAccess;

    public DALSchool() {
        dataAccess = new DataAccess();
    }

    @Override
    public List<School> getAllSchhol()throws DalException {
        ArrayList<School> getAllSchools = new ArrayList<>();
        try(Connection con = dataAccess.getConnection()){
            String sql = "Select * from School ";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()){
                int id = rs.getInt("");
                String name = rs.getString("");
                School school = new School(id , name );
                getAllSchools.add(school);
            }
            return getAllSchools ;
        } catch (SQLException e) {
          throw new DalException("Couldn't retrieve a list of schools " ,e);
        }
    }

    @Override
    public School createSchool(String name) throws DalException{
        try(Connection con = dataAccess.getConnection()) {
            String sql = "insert  into [dbo].[School] (name ) values  (?)";
            PreparedStatement prs = con.prepareStatement(sql);
            prs.setString(1 , name);
            prs.execute();

        } catch (SQLException e) {
           throw new DalException("Couldnot create a school " , e );
        }
        return null;
    }

    @Override
    public void updateSchool(String name , School school )throws DalException {
      try(Connection con = dataAccess.getConnection()) {
        String sql = "UPDATE School SET name = ?  where id = ? ";
        PreparedStatement prs = con.prepareStatement(sql);
        prs.setString(1, name );
        prs.setInt(2 , school.getId());
        prs.executeUpdate();
      } catch (SQLException e) {
        throw new DalException("couldnot update school at this moment please try again later " , e );
      }
    }

    @Override
    public void deleteSchool(School school ) throws DalException{
    try(Connection con = dataAccess.getConnection()) {
        String sql ="Delete from School where id = ?";
        PreparedStatement prs = con.prepareStatement(sql);
        prs.setInt(1 , school.getId() );
    } catch (SQLException e) {
      throw new DalException("Couldnot delete this school at this moment " , e);
    }
    }
}
