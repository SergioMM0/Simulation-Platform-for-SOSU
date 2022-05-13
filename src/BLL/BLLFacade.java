package BLL;

import BE.*;
import BLL.Exceptions.BLLException;
import DAL.util.DalException;

import java.util.List;

public interface BLLFacade {

    User checkCredentials(String email, String password) throws DalException, BLLException;

    void createCase(Case newCase) throws DalException;

    void createPatient(Patient patient) throws DalException;

    void saveStudentQuestionAnswer(StudentQuestionnaireAnswer answer) throws DalException;

    StudentQuestion getFirstQuestion() throws DalException;

    StudentQuestion getNextQuestion(StudentQuestion question) throws DalException;

    List<Group> getAllGroups(int schoolID) throws DalException;

    List<Case> getAllCases(int schoolID) throws DalException;

    List<Patient> getAllPatients(int schoolID) throws DalException;

    void addNewStudent(User user) throws DalException;

    void updateStudent(User student)throws DalException;

    void deleteStudent(User student) throws DalException;

    List<User> getAllStudent(int schoolID) throws DalException;
}


