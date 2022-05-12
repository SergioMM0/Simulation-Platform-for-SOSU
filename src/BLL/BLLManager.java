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
    public void createCase(Case newCase) throws DalException {
        dalFacade.createCase(newCase);
        //TODO collision with Category and Subcategory reformat
    }

    @Override
    public void createPatient(Patient patient) throws DalException {
        dalFacade.createPatient(patient);
    }

    @Override
    public List<Group> getAllGroups(int schoolID) throws DalException {
        return dalFacade.getAllGroups(schoolID);
    }

    @Override
    public List<Case> getAllCases(int schoolID) throws DalException {
        return dalFacade.getAllCases(schoolID);
    }

    @Override
    public List<Patient> getAllPatients(int schoolID) throws DalException {
        return dalFacade.getAllPatients(schoolID);
    }

    @Override
    public void addNewStudent(User user) throws DalException {
        dalFacade.addUser(user);
        //TODO Implement this :D
    }

    @Override
    public void updateStudent(User student) throws DalException {
        dalFacade.updateuser(student);
        //TODO implement this :D
        // The password of the student will be the name of the student so: student.getName() xd ||
        // Do not delete the following annotation, is for the exam:

        //The password of the student will be the name
    }

    @Override
    public void deleteStudent(User student) throws DalException {
        dalFacade.deleteuser(student);
    }

    @Override
    public void saveStudentQuestionAnswer(StudentQuestionnaireAnswer answer) throws DalException {
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
