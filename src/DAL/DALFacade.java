package DAL;

import BE.*;
import DAL.util.DalException;

import java.sql.Timestamp;
import java.util.List;

public interface DALFacade {

    List<Case> getAllCases(int schoolid) throws DalException;

    void createCase(Case c , Category category , SubCategory subCategory)throws DalException;

    void updateCase(Case c , String name, String description_of_the_condition, String cause_text, String causal_diagnose, String causal_condition, String citizens_want_goal)throws  DalException ;

    void deleteCase(Case c) throws DalException ;

    List<Category> getAllCategories() throws DalException;

    void createCategory(Category category) throws DalException;

    void updateCategory(Category category , String name) throws DalException ;

    void deleteCategory(Category category)throws DalException;

    List<SubCategory> getAllSubCategories(Category category) throws DalException;

    void createSubCategory(SubCategory subCategory)throws DalException;

    void updateSubCategory(Category category , String name )throws DalException;

    void deleteSubCategory(Category category)throws DalException;

    User verifyUsers(String useremail, String password) throws DalException;

    List<User> getAllUsers() throws DalException;

    void updateuser(User user , String username , String email , String userType) throws DalException;

    void deleteuser(User user) throws DalException;

    User addUser(String username , int schoolid , String password , String email , String usertype) throws DalException;

    List<User> searchForUser (String query) throws DalException;

    List<Patient> getAllPatients(int schoolid) throws DalException;

    Patient createPatient(String first_name, String last_name, Timestamp dateofBirth, String gender, int weight, int height, String cpr, String phone_number, String blood_type, String exercise, String diet, String alcohol, String tobacco, String observations , int schoolid , int teacherid) throws DalException;

    void updatepatient(Patient patient ,String first_name, String last_name, Timestamp dateofBirth, String gender, int weight, int height, String cpr, String phone_number, String blood_type, String exercise, String diet, String alcohol, String tobacco, String observations) throws DalException;

    void deletePatient(Patient patient)throws DalException ;

    List<School> getAllSchhol() throws DalException;

    School createSchool(String name)throws DalException;

    void updateSchool(String name , School school)throws DalException;

    void deleteSchool(School school)throws DalException;

    List<Group> getAllGroups()throws DalException;

    Group createGroup(String name)throws DalException;

    void updateGroup(Group group , String name )throws DalException;

    void deleteGroup(Group group)throws DalException;

    List<User> getUsersInGroup(int id)throws DalException;

    void addUsertoGroup(Group group , User user)throws DalException;

    void removeUserFromGroup(User user)throws DalException;

    void assignCaseToPatientToGroup(Patient p , Case c , Group g) throws DalException;




}
