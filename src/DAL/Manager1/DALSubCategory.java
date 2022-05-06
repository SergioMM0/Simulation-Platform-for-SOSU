package DAL.Manager1;

import BE.Category;
import BE.SubCategory;
import DAL.DataAccess.DataAccess;
import DAL.util.DalException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DALSubCategory {
    private final DataAccess dataAccess;

    public DALSubCategory() {
        dataAccess = new DataAccess();
    }


    public List<SubCategory> getAllSubCategories(Category category) throws DalException {
        ArrayList<SubCategory> subCategories = new ArrayList<>();
        try(Connection connection = dataAccess.getConnection()) {
            String sql = "select * from [dbo].[subcategory] where CategoryFid = ?";
            PreparedStatement prs = connection.prepareStatement(sql);
            prs.setInt(1 , category.getId());
            prs.execute();
            ResultSet rs = prs.getResultSet();
            while (rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                SubCategory subCategory = new SubCategory(id , name);
                subCategories.add(subCategory);
            }
            return subCategories;
        } catch (SQLException e) {
            throw new DalException("Connection Lost" , e);
        }
    }


    public void createSubCategory(SubCategory subCategory) throws DalException {

    }


    public void updateSubCategory(Category category, String name)throws DalException  {

    }


    public void deleteSubCategory(Category category)throws DalException  {

    }
}
