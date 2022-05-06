package DAL.Manager1;

import BE.Category;
import DAL.DataAccess.DataAccess;
import DAL.util.DalException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DAOCategory {
    private final DataAccess dataAccess;

    public DAOCategory() {
        dataAccess = new DataAccess();
    }


    public List<Category> getAllCategories() throws DalException {
        ArrayList<Category> categorirs = new ArrayList<>();

        try (Connection con = dataAccess.getConnection()) {
            String sql = "Select * from [dbo].[Category]";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("categoryid");
                String name = rs.getString("name");
                Category category = new Category(id, name);
                categorirs.add(category);
            }
            return categorirs;
        } catch (SQLException e) {
            throw new DalException("Connection Lost ", e);
        }
    }


    public void createCategory(Category category) throws DalException {

    }


    public void updateCategory(Category category) throws DalException {

    }


    public void deleteCategory(Category category) {

    }
}
