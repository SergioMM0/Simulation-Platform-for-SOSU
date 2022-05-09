package BLL;

import BE.*;
import BLL.Exceptions.BLLException;
import DAL.util.DalException;
import javafx.collections.ObservableList;

import java.util.List;

public interface BLLFacade {

    User checkCredentials(String email, String password) throws DalException, BLLException;

    void createCase(Case newCase, Category category, SubCategory subCategory) throws DalException;

    List<Category> getAllCategories() throws DalException;

    List<SubCategory> getAllSubcategories(Category category) throws DalException;

    void createPatient(Patient patient) throws DalException;

    List<Group> getAllGroups(int schoolID) throws DalException;

    List<Case> getAllCases(int schoolID) throws DalException;

    List<Patient> getAllPatients(int schoolID) throws DalException;
}
