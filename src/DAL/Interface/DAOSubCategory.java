package DAL.Interface;

import BE.Category;
import BE.SubCategory;
import DAL.util.DalException;

import java.util.List;

public interface DAOSubCategory {
    List<SubCategory> getAllSubCategories(Category category) throws DalException;

    SubCategory createCategory(String name)throws DalException;

    void updateCategory(Category category , String name );

    void deleteCategory(Category category);

}
