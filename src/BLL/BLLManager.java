package BLL;

import BE.*;
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
        dalFacade.createCase(newCase,category,subCategory);
    }

    @Override
    public List<Category> getAllCategories() throws DalException {
        return dalFacade.getAllCategories();
    }

    @Override
    public List<SubCategory> getAllSubcategories(Category category) throws DalException {
        return dalFacade.getAllSubCategories(category);
    }

    @Override
    public void createPatient(Patient patient) throws DalException {
        //dalFacade.createPatient(patient);
    }

    @Override
    public void saveStudentQuestionAnswer(StudentQuestionaireAnswer answer) throws DalException {
        dalFacade.addStudentQuestionAnswer(answer);
    }

    @Override
    public StudentQuestion getFirstQuestion() throws DalException {
        return dalFacade.getFirstStudentQuestion();
    }

    @Override
    public StudentQuestion getNextQuestion(StudentQuestion question) throws DalException {
        return dalFacade.getNextStudentQuestion(question.getId());
    }


}
