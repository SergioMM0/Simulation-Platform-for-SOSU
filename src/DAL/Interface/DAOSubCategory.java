package DAL.Interface;

import BE.Category;
import BE.SubCategory;
import DAL.util.DalException;

import java.util.List;

public interface DAOSubCategory {
    List<SubCategory> getAllSubCategories(Category category) throws DalException;
}
