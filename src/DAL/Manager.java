package DAL;

import BE.User;
import DAL.DataAccess.DataAccess;
import DAL.util.DalException;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Manager implements DALFacade {

    private final DataAccess dataAccess;

    public Manager() {
        dataAccess = new DataAccess();
    }

    @Override
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

                String usertype = rs.getString("usertype");
              user = new User(id , name , useremail , usertype );
            }
            return user;


        } catch (SQLException e) {
          throw new DalException("Connection Lost " , e);
        }
    }

    @Override
    public List<User> getAllUsers() throws DalException {
        ArrayList<User> users = new ArrayList<>();
        try (Connection con = dataAccess.getConnection()) {
            String sql = "SELECT * FROM users ";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("userid");
                String username = rs.getString("username");
                String email = rs.getString("email");
                String usertype = rs.getString("usertype");
                User user = new User(id, username, email, usertype);
                users.add(user);
            }

        } catch (SQLException e) {
            throw new DalException("Connection Lost " , e);
        }
        return users;
    }

    @Override
    public void updateuser(User user, String username, String email, String userType) throws DalException {
        try (Connection con = dataAccess.getConnection()) {
            String sql = "UPDATE users SET username = ?  , email = ?  , usertype = ?  WHERE userid = ? ";

            PreparedStatement prs = con.prepareStatement(sql);
            prs.setString(1, username);
            prs.setString(2, email);
            prs.setString(3, userType);
            prs.setInt(4, user.getId());

            prs.executeUpdate();

        } catch (SQLException e) {
            throw new DalException("Connection Lost " , e);
        }
    }

    @Override
    public void deleteuser(User user) throws DalException {
        try (Connection con = dataAccess.getConnection()) {

            String sql = "DELETE FROM users WHERE userid = ?";
            PreparedStatement prs = con.prepareStatement(sql);
            prs.setInt(1, user.getId());

            prs.executeUpdate();

        } catch (SQLException e) {
            throw new DalException("Connection Lost " , e);
        }
    }

    @Override
    public User addUser(String username, String password, String email, String usertype) throws DalException {
        User user ;
        try (Connection con = dataAccess.getConnection()) {
            String sql = "INSERT INTO users(username , password, email , usertype)" +
                    "VALUES  (?,?,?,?)";
            PreparedStatement prs = con.prepareStatement(sql);
            prs.setString(1, username);
            prs.setString(2, password);
            prs.setString(3, email);
            prs.setString(4, usertype);
            prs.executeUpdate();
            user = new User(newestid(), username, email, usertype);

        } catch (SQLException e) {
            throw new DalException("Connection Lost " , e);
        }
        return user;
    }

    private int newestid() throws DalException {
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

    @Override
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

                User user = new User(id, username, email, usertype);
                users.add(user);
            }

        } catch (SQLException e) {
            throw new DalException("Connection Lost " , e);
        }

        return users;
    }


}
