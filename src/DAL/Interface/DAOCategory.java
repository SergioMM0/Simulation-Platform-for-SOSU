package DAL.Interface;

import BE.Category;
import DAL.util.DalException;

import java.util.List;

public interface DAOCategory {

    List<Category> getAllCategories() throws DalException;

}
