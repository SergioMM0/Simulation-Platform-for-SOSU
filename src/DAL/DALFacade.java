package DAL;

import BE.Category;
import BE.Patient;
import BE.SubCategory;
import BE.User;
import DAL.util.DalException;

import java.sql.Timestamp;
import java.util.List;

public interface DALFacade {


    User verifyUsers(String useremail, String password) throws DalException;

    List<User> getAllUsers() throws DalException;

    void updateuser(User user , String username , String email , String userType) throws DalException;

    void deleteuser(User user) throws DalException;

    User addUser(String username , String password , String email , String usertype) throws DalException;

    List<User> searchForUser (String query) throws DalException;

    //------------------ Category

    List<Category> getAllCategories() throws DalException ;

    List<SubCategory> getAllSubCategories(Category category) throws DalException ;

    //------------------- Patient

    List<Patient> getAllPatients() throws DalException ;

    Patient createPatient( String first_name, String last_name, Timestamp dateofBirth, String gender, int weight,
                          int height, String cpr, String phone_number, String blood_type, String exercise, String diet, boolean alcohol,
                          boolean tobacco, String observations) throws DalException;

    void updatepatient(Patient patient ,String first_name, String last_name, Timestamp dateofBirth, String gender, int weight,
                       int height, String cpr, String phone_number, String blood_type, String exercise, String diet, boolean alcohol,
                       boolean tobacco, String observations) throws DalException;

    void deletePatient(Patient patient)throws DalException ;

}
