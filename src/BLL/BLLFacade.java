package BLL;

import BE.Case;
import BE.Category;
import BE.SubCategory;
import BE.User;
import BLL.Exceptions.BLLException;
import DAL.util.DalException;

import java.util.List;

public interface BLLFacade {

    User checkCredentials(String email, String password) throws DalException, BLLException;

    void createCase(Case newCase, Category category, SubCategory subCategory) throws DalException;

    List<Category> getAllCategories() throws DalException;

    List<SubCategory> getAllSubcategories(Category category) throws DalException;
}
