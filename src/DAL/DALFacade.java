package DAL;

import BE.*;
import DAL.util.DalException;

import java.util.List;

public interface DALFacade {

    List<Case> getAllCases(int schoolid) throws DalException;

    void createCase(Case c , Category category , SubCategory subCategory)throws DalException;

    void updateCase(Case c)throws  DalException ;

    void deleteCase(Case c) throws DalException ;

    List<Category> getAllCategories() throws DalException;

    void createCategory(Category category) throws DalException;

    void updateCategory(Category category) throws DalException ;

    void deleteCategory(Category category)throws DalException;

    List<SubCategory> getAllSubCategories(Category category) throws DalException;

    void createSubCategory(SubCategory subCategory)throws DalException;

    void updateSubCategory(SubCategory subCategory)throws DalException;

    void deleteSubCategory(SubCategory subCategory)throws DalException;

    User verifyUsers(String useremail, String password) throws DalException;

    List<User> getAllUsers() throws DalException;

    void updateuser(User user ) throws DalException;  //

    void deleteuser(User user) throws DalException;

    void addUser(User user , int schoolid , String password) throws DalException; //

    List<User> searchForUser (String query) throws DalException;

    List<Patient> getAllPatients(int schoolid) throws DalException;

    void createPatient(Patient patient ) throws DalException;
    //
    void updatepatient(Patient patient) throws DalException;
        //
    void deletePatient(Patient patient)throws DalException ;

    List<School> getAllSchhol() throws DalException;

    void createSchool(School school)throws DalException;
        //
    void updateSchool(School school)throws DalException;
        //
    void deleteSchool(School school)throws DalException;

    List<Group> getAllGroups()throws DalException;

    void createGroup(Group group)throws DalException;
        //
    void updateGroup(Group group)throws DalException;
        //
    void deleteGroup(Group group)throws DalException;

    List<User> getUsersInGroup(int id)throws DalException;

    void addUsertoGroup(Group group , User user)throws DalException;

    void removeUserFromGroup(User user)throws DalException;

    void assignCaseToPatientToGroup(Patient p , Case c , Group g) throws DalException;


    void addStudentQuestionAnswer(StudentQuestionaireAnswer answer) throws DalException;

    StudentQuestion getFirstStudentQuestion() throws DalException;

    StudentQuestion getNextStudentQuestion(int id) throws DalException;
}
