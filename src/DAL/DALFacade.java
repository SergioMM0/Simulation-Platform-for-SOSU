package DAL;

import BE.*;
import DAL.util.DalException;

import java.util.List;

public interface DALFacade {

    List<Case> getAllCases(int schoolid) throws DalException;

    void createCase(Case c)throws DalException;

    void updateCase(Case c)throws  DalException ;

    void deleteCase(Case c) throws DalException ;

    User verifyUsers(String useremail, String password) throws DalException;

    List<User> getAllUsers(int schoolid) throws DalException;

    void updateuser(User user) throws DalException;  //

    void deleteuser(User user) throws DalException;

    void addUser(User user ) throws DalException; //

    List<User> searchForUser (String query) throws DalException;

    List<Patient> getAllPatients(int schoolid) throws DalException;

    void createPatient(Patient patient ) throws DalException;
    //
    void updatePatient(Patient patient) throws DalException;
        //
    void deletePatient(Patient patient)throws DalException ;

    List<School> getAllSchhol() throws DalException;

    void createSchool(School school)throws DalException;
        //
    void updateSchool(School school)throws DalException;
        //
    void deleteSchool(School school)throws DalException;

    List<Group> getAllGroups(int schoolID)throws DalException;

    void createGroup(Group group)throws DalException;
        //
    void updateGroup(Group group)throws DalException;
        //
    void deleteGroup(Group group)throws DalException;

    List<User> getUsersInGroup(int id)throws DalException;

    void addUsertoGroup(Group group , User user)throws DalException;

    void removeUserFromGroup(User user)throws DalException;

    void assignCaseToPatientToGroup(Patient p , Case c , Group g) throws DalException;


    void addStudentQuestionAnswer(StudentQuestionnaireAnswer answer) throws DalException;

    StudentQuestion getFirstStudentQuestion() throws DalException;

    StudentQuestion getNextStudentQuestion(int id) throws DalException;

    StudentQuestion getPreviousQuestion(int currentQuestionId) throws DalException;

    StudentQuestionnaireAnswer getQuestionaireAnswer(int questionId, int questionaireId) throws DalException;

    public void removeUserAndGroup(User user , Group group)throws DalException;
}
