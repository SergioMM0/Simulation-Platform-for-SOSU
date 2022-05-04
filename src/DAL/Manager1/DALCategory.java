package DAL.Manager1;

import BE.Category;
import DAL.DataAccess.DataAccess;
import DAL.Interface.DAOCategory;
import DAL.util.DalException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DALCategory implements DAOCategory {
    private final DataAccess dataAccess;

    public DALCategory() {
        dataAccess = new DataAccess();
    }

    @Override
    public List<Category> getAllCategories() throws DalException {
        ArrayList<Category> categorirs = new ArrayList<>();

        try (Connection con = dataAccess.getConnection()) {
            String sql = "Select * from [dbo].[Category]";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                Category category = new Category(id, name);
                categorirs.add(category);
            }
            return categorirs;
        } catch (SQLException e) {
            throw new DalException("Connection Lost ", e);
        }
    }
}
