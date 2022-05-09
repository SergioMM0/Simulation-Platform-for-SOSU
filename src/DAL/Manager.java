package DAL;

import BE.*;
import DAL.Manager1.*;
import DAL.util.DalException;

import java.util.List;

public class Manager implements DALFacade {

    private final DAOCase daoCase ;
    private final DAOCategory daoCategory ;
    private final DAOSubCategory daoSubCategory;
    private final DAOUser daoUser;
    private final DAOPatient daoPatient ;
    private final DAOSchool daoSchool;
    private final DAOGroup daoGroup;
    private final DAOCasCatPat daoCasCatPat;
    public Manager() {
        daoCase = new DAOCase();
        daoCategory = new DAOCategory();
        daoSubCategory = new DAOSubCategory();
        daoUser = new DAOUser();
        daoPatient = new DAOPatient();
        daoSchool = new DAOSchool();
        daoGroup = new DAOGroup();
        daoCasCatPat = new DAOCasCatPat();
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
    public void updateCase(Case c) throws DalException {
        daoCase.updateCase(c);
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
            daoCategory.createCategory(category);
    }

    @Override
    public void updateCategory(Category category) throws DalException {
        daoCategory.updateCategory(category);
    }

    @Override
    public void deleteCategory(Category category) {
        daoCategory.deleteCategory(category);
    }

    @Override
    public List<SubCategory> getAllSubCategories(Category category) throws DalException {
        return daoSubCategory.getAllSubCategories(category);
    }

    @Override
    public void createSubCategory(SubCategory subCategory) throws DalException {
        daoSubCategory.createSubCategory(subCategory);
    }

    @Override
    public void updateSubCategory(SubCategory category) throws DalException {
        daoSubCategory.updateSubCategory(category);
    }

    @Override
    public void deleteSubCategory(SubCategory subCategory) throws DalException {
        daoSubCategory.deleteSubCategory(subCategory);
    }

    @Override
    public User verifyUsers(String useremail, String password) throws DalException {
        return daoUser.verifyUsers(useremail,password);
    }

    @Override
    public List<User> getAllUsers() throws DalException {
        return daoUser.getAllUsers();
    }

    @Override
    public void updateuser(User user) throws DalException {
        daoUser.updateuser(user);
    }

    @Override
    public void deleteuser(User user) throws DalException {
        daoUser.deleteuser(user);
    }

    @Override
    public void addUser(User user , int schoolid , String password) throws DalException {
         daoUser.addUser(user ,schoolid ,password);
    }

    @Override
    public List<User> searchForUser(String query) throws DalException {
        return daoUser.searchForUser(query);
    }

    @Override
    public List<Patient> getAllPatients(int schoolid) throws DalException {
        return daoPatient.getAllPatients(schoolid);
    }

    @Override
    public void createPatient( Patient patient) throws DalException {
       daoPatient.createPatient(patient  );
    }

    @Override
    public void updatepatient(Patient patient) throws DalException {
        daoPatient.updatepatient(patient);
    }

    @Override
    public void deletePatient(Patient patient) throws DalException {
        daoPatient.deletePatient(patient);
    }

    @Override
    public List<School> getAllSchhol() throws DalException {
        return daoSchool.getAllSchhol();
    }

    @Override
    public void createSchool(School school) throws DalException {
        daoSchool.createSchool(school);
    }

    @Override
    public void updateSchool(School school) throws DalException {
        daoSchool.updateSchool(school);
    }

    @Override
    public void deleteSchool(School school) throws DalException {
        daoSchool.deleteSchool(school);
    }

    @Override
    public List<Group> getAllGroups() throws DalException {
        return daoGroup.getAllGroups();
    }

    @Override
    public void createGroup(Group group) throws DalException {
      daoGroup.createGroup(group);
    }

    @Override
    public void updateGroup(Group group) throws DalException {
        daoGroup.updateGroup(group);
    }

    @Override
    public void deleteGroup(Group group) throws DalException {
        daoGroup.deleteGroup(group);
    }

    @Override
    public List<User> getUsersInGroup(int id) throws DalException {
        return daoGroup.getUsersInGroup(id);
    }

    @Override
    public void addUsertoGroup(Group group, User user) throws DalException {
        daoGroup.addUsertoGroup(group ,user);
    }

    @Override
    public void removeUserFromGroup(User user) throws DalException {
        daoGroup.removeUserFromGroup(user);
    }

    @Override
    public void assignCaseToPatientToGroup(Patient p, Case c, Group g) throws DalException {
        daoCasCatPat.assignCaseToPatientToGroup(p ,c ,g);
    }
}
