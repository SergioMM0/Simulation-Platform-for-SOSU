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

public class DAOSubCategory {
    private final DataAccess dataAccess;

    public DAOSubCategory() {
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
                int id = rs.getInt("subcategoryID");
                String name = rs.getString("issue");
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


    public void updateSubCategory(SubCategory subCategory)throws DalException  {

    }


    public void deleteSubCategory(SubCategory subCategory)throws DalException  {

    }
}
