package DAL.Interface;

import BE.Category;
import DAL.util.DalException;

import java.util.List;

public interface DAOCategory {

    List<Category> getAllCategories() throws DalException;

    Category createCategory() throws DalException;

    void updateCategory(Category category , String name) throws DalException ;

   void deleteCategory(Category category);

}
