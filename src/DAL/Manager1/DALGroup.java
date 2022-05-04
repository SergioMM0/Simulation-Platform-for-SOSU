package DAL.Manager1;

import BE.Group;
import BE.User;
import DAL.DataAccess.DataAccess;
import DAL.Interface.DAOGroup;
import DAL.util.DalException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DALGroup implements DAOGroup {

    private final DataAccess dataAccess ;

    public DALGroup(){
        dataAccess = new DataAccess();
    }

    @Override
    public List<Group> getAllGroups() throws DalException{
        ArrayList<Group> getAllGruops = new ArrayList<>();
        try(Connection con = dataAccess.getConnection()) {
            String sql = "SELECT * FROM Groups";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");

                Group group = new Group(id , name , null);
                getAllGruops.add(group);
            }
            return getAllGruops;
        } catch (SQLException e) {
           throw new DalException("couldn't retrieve the groups " , e );
        }
    }

    @Override
    public Group createGroup(String name)throws DalException {
        ArrayList<User> users = new ArrayList<>();
        try(Connection con = dataAccess.getConnection()) {
            String sql = "INSERT INTO Groups (Title) VALUES (?)";
            PreparedStatement prs = con.prepareStatement(sql);
            prs.setString(1 , name);
            prs.executeUpdate();

            return new Group(newestidforGroup(), name , users);
        } catch (SQLException e) {
            throw new DalException("couldn't create new group " , e );
        }

    }

    @Override
    public void updateGroup( Group group , String name )throws DalException {
        try(Connection con = dataAccess.getConnection()){
            String sql = "UPDATE Groups SET name = ?  where id = ?";
            PreparedStatement prs = con.prepareStatement(sql);
            prs.setString(1 , name );
            prs.setInt(2 , group.getId());
            prs.executeUpdate();
        } catch (SQLException e) {
           throw new DalException("Couldn't create Group" , e);
        }
    }

    @Override
    public void deleteGroup(Group group) throws DalException{
        try(Connection con = dataAccess.getConnection()) {
            String sql = "Delete from Groups where id = ?";
            PreparedStatement prs =  con.prepareStatement(sql);
            prs.setInt(1 , group.getId());
            prs.executeUpdate();

        } catch (SQLException e) {
            throw new DalException("Couldn't delete a Group" ,e);
        }

    }

    @Override
    public List<User> getUsersInGroup(int id) throws DalException {
        ArrayList<User> usersinGroup = new ArrayList<>();

        try(Connection con = dataAccess.getConnection()) {
            String sql = "SELECT userid , username , email , usertype , schoolid FROM GroupUsers" +
                    "INNER JOIN users "+
                    "ON GroupUsers.studentid = userid WHERE GroupUsers.Groupid = ? ";
            PreparedStatement prs = con.prepareStatement(sql);
            prs.setInt(1, id);
            ResultSet rs = prs.getResultSet();
            while(rs.next()){
                int iD = rs.getInt("userid");
                String name = rs.getString("username");
                String email = rs.getString("email");
                String usertype = rs.getString("usertype");
                int schoolid = rs.getInt("schoolid");

                User user = new User(iD ,schoolid ,name ,email ,usertype);
                usersinGroup.add(user);
            }
            return usersinGroup;
        } catch (SQLException e) {
           throw new DalException("couldn't retrieve users in this group" , e);
        }
    }

    @Override
    public void addUsertoGroup(Group group, User user) throws DalException {
        try(Connection con = dataAccess.getConnection()) {
            String sql = "INSERT INTO GroupUsers(studentid,Groupid) VALUES (?,?)";
            PreparedStatement prs = con.prepareStatement(sql);
            prs.setInt(1,user.getId());
            prs.setInt(2 , group.getId());
            prs.executeUpdate();
        } catch (SQLException e) {
           throw new DalException("couldn't add user to this groups " , e);
        }
    }

    @Override
    public void removeUserFromGroup(User user) throws DalException {
        try (Connection con = dataAccess.getConnection()) {
            String sql = "DELETE FROM GroupUsers WHERE studentid = ? ";
            PreparedStatement prs = con.prepareStatement(sql);
            prs.setInt(1, user.getId());
            prs.executeUpdate();
        } catch (SQLException e) {
            throw new DalException("couldn't remove user from this groups " , e);
        }
    }

    private int newestidforGroup() throws DalException {
        int newid = -1;

        try (Connection con = dataAccess.getConnection()) {
            String sql = "SELECT TOP(1) * FROM Groups ORDER by id desc";
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
