package DAL.Manager1;

import BE.User;
import DAL.DataAccess.DataAccess;
import DAL.util.DalException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAOUser {
    private final DataAccess dataAccess;

    public DAOUser() {
        dataAccess = new DataAccess();
    }


    public User verifyUsers(String useremail, String password)throws DalException {
        User user = null;

        try (Connection con = dataAccess.getConnection()) {
            String sql = " SELECT * FROM users where email = ? AND password = ?";
            PreparedStatement prs = con.prepareStatement(sql);
            prs.setString(1, useremail);
            prs.setString(2, password);
            ResultSet rs = prs.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("userid");
                String name = rs.getString("username");
                int schoolid = rs.getInt("schoolid");
                String usertype = rs.getString("usertype");
                user = new User(id , schoolid,name , useremail , usertype );
            }
            return user;


        } catch (SQLException e) {
            throw new DalException("Connection Lost " , e);
        }
    }


    public List<User> getAllUsers(int schoolId) throws DalException {
        ArrayList<User> users = new ArrayList<>();
        try (Connection con = dataAccess.getConnection()) {
            String sql = "SELECT * FROM users where schoolid = ? And usertype = 'STUDENT' ";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1 , schoolId);
            statement.execute();
            ResultSet rs = statement.getResultSet();
            while (rs.next()) {
                int id = rs.getInt("userid");
                String username = rs.getString("username");
                String email = rs.getString("email");
                String usertype = rs.getString("usertype");
                int schoolid = rs.getInt("schooid");
                User user = new User(id,schoolid , username, email, usertype);
                users.add(user);
            }
        } catch (SQLException e) {
            throw new DalException("Connection Lost " , e);
        }
        return users;
    }


    public void updateuser(User user ) throws DalException {
        try (Connection con = dataAccess.getConnection()) {
            String sql = "UPDATE users SET username = ?  , email = ?  , usertype = ?  WHERE userid = ? ";

            PreparedStatement prs = con.prepareStatement(sql);
            prs.setString(1, user.getName());
            prs.setString(2, user.getEmail());
            prs.setString(3, user.getUserType());
            prs.setInt(4 , user.getId());

            prs.executeUpdate();

        } catch (SQLException e) {
            throw new DalException("Connection Lost " , e);
        }
    }

    public void deleteuser(User user) throws DalException {
        try (Connection con = dataAccess.getConnection()) {

            String sql = "DELETE FROM users WHERE userid = ?";
            PreparedStatement prs = con.prepareStatement(sql);
            prs.setInt(1, user.getId());

            prs.executeUpdate();

        } catch (SQLException e) {
            throw new DalException("Connection Lost ", e);
        }

    }


    public void addUser(User user) throws DalException {

        try (Connection con = dataAccess.getConnection()) {
            String sql = "INSERT INTO users(username , password, email , usertype , schoolid)" +
                    "VALUES  (?,?,?,?,?)";
            PreparedStatement prs = con.prepareStatement(sql);
            prs.setString(1, user.getName());
            prs.setString(2, user.getName());
            prs.setString(3, user.getEmail());
            prs.setString(4, user.getUserType());
            prs.setInt(5 , user.getSchoolID());
            prs.executeUpdate();
        } catch (SQLException e) {
            throw new DalException("Connection Lost " , e);
        }

    }

    private int newestidforuser() throws DalException {
        int newid = -1;

        try (Connection con = dataAccess.getConnection()) {
            String sql = "SELECT TOP(1) * FROM users ORDER by userid desc";
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


    public List<User> searchForUser(String query) throws DalException {
        List<User> users = new ArrayList<>();

        String stringquery = "%" + query + "%";

        try (Connection con = dataAccess.getConnection()) {
            String command = "Select * from users where  username like ? or usertype like ? or email like ? ";
            PreparedStatement prs = con.prepareStatement(command);
            prs.setString(1, stringquery);
            prs.setString(2, stringquery);
            prs.setString(3, stringquery);
            prs.execute();
            ResultSet rs = prs.getResultSet();
            while (rs.next()) {

                int id = rs.getInt("id");
                String username = rs.getString("username");
                String email = rs.getString("email");
                String usertype = rs.getString("usertype");

                User user = new User(id,1 ,username, email, usertype);
                users.add(user);
            }

        } catch (SQLException e) {
            throw new DalException("Connection Lost " , e);
        }

        return users;
    }
}
