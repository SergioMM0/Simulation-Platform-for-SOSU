package DAL;

import BE.*;
import DAL.Manager1.DALCase;
import DAL.Manager1.DALCategory;
import DAL.Manager1.DALSubCategory;
import DAL.Manager1.DALUser;
import DAL.util.DalException;

import java.sql.*;
import java.util.List;

public class Manager implements DALFacade {

    private final DALCase daoCase ;
    private final DALCategory daoCategory ;
    private final DALSubCategory daoSubCategory;
    private final DALUser daoUser;
    public Manager() {
        daoCase = new DALCase();
        daoCategory = new DALCategory();
        daoSubCategory = new DALSubCategory();
        daoUser = new DALUser();
    }


    @Override
    public List<Case> getAllCases(int schoolid) throws DalException {
      return   daoCase.getAllCases(schoolid);
    }

    @Override
    public void createCase(Case c, Category category, SubCategory subCategory) throws DalException {
        daoCase.createCase(c ,category ,subCategory);
    }

    @Override
    public void updateCase(Case c, String name, String description_of_the_condition, String cause_text, String causal_diagnose, String causal_condition, String citizens_want_goal) throws DalException {
        daoCase.updateCase(c , name ,description_of_the_condition,cause_text,causal_diagnose,causal_condition,citizens_want_goal);
    }

    @Override
    public void deleteCase(Case c) throws DalException {
        daoCase.deleteCase(c);
    }

    @Override
    public List<Category> getAllCategories() throws DalException {
        return daoCategory.getAllCategories();
    }

    @Override
    public void createCategory(Category category) throws DalException {

    }

    @Override
    public void updateCategory(Category category, String name) throws DalException {

    }

    @Override
    public void deleteCategory(Category category) {

    }

    @Override
    public List<SubCategory> getAllSubCategories(Category category) throws DalException {
        return daoSubCategory.getAllSubCategories(category);
    }

    @Override
    public void createSubCategory(SubCategory subCategory) throws DalException {

    }

    @Override
    public void updateSubCategory(Category category, String name) throws DalException {

    }

    @Override
    public void deleteSubCategory(Category category) throws DalException {

    }

    @Override
    public User verifyUsers(String useremail, String password) throws DalException {
        return daoUser.verifyUsers(useremail,password);
    }

    @Override
    public List<User> getAllUsers() throws DalException {
        return null;
    }

    @Override
    public void updateuser(User user, String username, String email, String userType) throws DalException {

    }

    @Override
    public void deleteuser(User user) throws DalException {

    }

    @Override
    public User addUser(String username, int schoolid, String password, String email, String usertype) throws DalException {
        return null;
    }

    @Override
    public List<User> searchForUser(String query) throws DalException {
        return null;
    }

    @Override
    public List<Patient> getAllPatients(int schoolid) throws DalException {
        return null;
    }

    @Override
    public Patient createPatient(String first_name, String last_name, Timestamp dateofBirth, String gender, int weight, int height, String cpr, String phone_number, String blood_type, String exercise, String diet, String alcohol, String tobacco, String observations, int schoolid, int teacherid) throws DalException {
        return null;
    }

    @Override
    public void updatepatient(Patient patient, String first_name, String last_name, Timestamp dateofBirth, String gender, int weight, int height, String cpr, String phone_number, String blood_type, String exercise, String diet, String alcohol, String tobacco, String observations) throws DalException {

    }

    @Override
    public void deletePatient(Patient patient) throws DalException {

    }

    @Override
    public List<School> getAllSchhol() throws DalException {
        return null;
    }

    @Override
    public School createSchool(String name) throws DalException {
        return null;
    }

    @Override
    public void updateSchool(String name, School school) throws DalException {

    }

    @Override
    public void deleteSchool(School school) throws DalException {

    }

    @Override
    public List<Group> getAllGroups() throws DalException {
        return null;
    }

    @Override
    public Group createGroup(String name) throws DalException {
        return null;
    }

    @Override
    public void updateGroup(Group group, String name) throws DalException {

    }

    @Override
    public void deleteGroup(Group group) throws DalException {

    }

    @Override
    public List<User> getUsersInGroup(int id) throws DalException {
        return null;
    }

    @Override
    public void addUsertoGroup(Group group, User user) throws DalException {

    }

    @Override
    public void removeUserFromGroup(User user) throws DalException {

    }

    @Override
    public void assignCaseToPatientToGroup(Patient p, Case c, Group g) throws DalException {

    }
}
