package BLL;

import BE.*;
import BLL.Exceptions.BLLException;
import DAL.util.DalException;

import java.util.List;

public interface BLLFacade {

    User checkCredentials(String email, String password) throws DalException, BLLException;

    void createCase(Case newCase) throws DalException;

    List<String> getAllCategories() throws DalException;

    List<String> getAllSubcategories(String category) throws DalException;

    void createPatient(Patient patient) throws DalException;

    void saveStudentQuestionAnswer(StudentQuestionaireAnswer answer) throws DalException;

    StudentQuestion getFirstQuestion() throws DalException;

    StudentQuestion getNextQuestion(StudentQuestion question) throws DalException;

    List<Group> getAllGroups(int schoolID) throws DalException;

    List<Case> getAllCases(int schoolID) throws DalException;

    List<Patient> getAllPatients(int schoolID) throws DalException;
}


