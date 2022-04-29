package DAL;

import BE.User;
import DAL.DataAccess.DataAccess;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Manager implements DALFacade {

    private final DataAccess dataAccess;

    public Manager() {
        dataAccess = new DataAccess();
    }

    @Override
    public User verifyUsers(String useremail, String password) {
        User user = null;

        try (Connection con = dataAccess.getConnection()) {
            String sql = " SELECT * FROM users where users.email = ? AND password = ?";
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


        } catch (SQLServerException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
}
