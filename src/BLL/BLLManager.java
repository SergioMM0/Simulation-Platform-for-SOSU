package BLL;

import BE.Case;
import BE.Category;
import BE.SubCategory;
import BE.User;
import BLL.Exceptions.BLLException;
import DAL.DALFacade;
import DAL.Manager;
import DAL.util.DalException;

import java.security.InvalidParameterException;
import java.util.List;

public class BLLManager implements BLLFacade{

    private DALFacade dalFacade;

    public BLLManager(){
        dalFacade = new Manager();
    }

    @Override
    public User checkCredentials(String email, String password) throws DalException, BLLException {
        User logedUser = dalFacade.verifyUsers(email,password);
        if(logedUser == null) {
            throw new BLLException("Wrong email or password, please try again", new InvalidParameterException());
        }
        return logedUser;
    }

    @Override
    public void createCase(Case newCase, Category category, SubCategory subCategory) throws DalException {
        //TODO change parameters in DAL so a Case BE is parsed instead of split CASE variables && void return type
        //dalFacade.createCase(newCase,category,subCategory);
    }

    @Override
    public List<Category> getAllCategories() throws DalException {
        return dalFacade.getAllCategories();
    }

    @Override
    public List<SubCategory> getAllSubcategories(Category category) throws DalException {
        return dalFacade.getAllSubCategories(category);
    }


}
