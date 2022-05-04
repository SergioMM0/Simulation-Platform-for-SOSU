package DAL;

import BE.*;
import DAL.util.DalException;

import java.sql.Timestamp;
import java.util.List;

public interface DALFacade {

    User verifyUsers(String useremail, String password) throws DalException;

    List<User> getAllUsers() throws DalException;

    void updateuser(User user , String username , String email , String userType) throws DalException;

    void deleteuser(User user) throws DalException;

    User addUser(String username , int schoolid , String password , String email , String usertype) throws DalException;

    List<User> searchForUser (String query) throws DalException;

    //------------------ Category

    List<Category> getAllCategories() throws DalException ;

    List<SubCategory> getAllSubCategories(Category category) throws DalException ;

    //------------------- Patient

    List<Patient> getAllPatients(int schoolid) throws DalException ;

    Patient createPatient( String first_name, String last_name, Timestamp dateofBirth, String gender, int weight, int height, String cpr, String phone_number, String blood_type, String exercise, String diet, String alcohol, String tobacco, String observations , int schoolid , int teacherid) throws DalException;

    void updatepatient(Patient patient ,String first_name, String last_name, Timestamp dateofBirth, String gender, int weight, int height, String cpr, String phone_number, String blood_type, String exercise, String diet, String alcohol, String tobacco, String observations) throws DalException;

    void deletePatient(Patient patient)throws DalException ;

    //-------------------Case

    List<Case> getAllCases(int schoolid) throws DalException;

    Case createCase(String name, String description_of_the_condition, String cause_text, String causal_diagnose, String causal_condition, String citizens_want_goal , Category category , SubCategory subCategory)throws DalException;

    void updateCase(Case c , String name, String description_of_the_condition, String cause_text, String causal_diagnose, String causal_condition, String citizens_want_goal)throws  DalException ;

    void deleteCase(Case c) throws DalException ;

    //-------------------sick patient

    void assignCasetoPatient(Patient patient , Case c ) throws DalException;
    /*
    SELECT  [case].[id]  ,  [case].[name] , Description_of_the_condition , Cause_text , Causal_diagnose , Causal_condition ,Citizens_want_goal , first_name , last_name
	fROM [Case]  join SickPatient on [Case].[id] = [SickPatient].[caseid]
			     join [Patient]  on  SickPatient.patientid  = [Patient].[id]
			     join School  on  Patient.schoolid = School.id where School.id  =1
     */

    /*
    SELECT  [case].[id]  ,  [case].[name] , Description_of_the_condition , Cause_text , Causal_diagnose , Causal_condition ,Citizens_want_goal , first_name , last_name,dateofBirth  ,gender , [Patient].[weight]  ,height,cpr,phone_number,blood_type,exercise,diet,alcohol,tobacco,observations , [Category].[name] ,[subcategory].[issue]
	fROM    Category join subcategory on Category.categoryid = subcategory.CategoryFid
				JOIN [Case] on subcategory.subcategoryID = [Case].subid
	             join SickPatient on [Case].[id] = [SickPatient].[caseid]
			     join [Patient]  on  SickPatient.patientid  = [Patient].[id]
			     join School  on  Patient.schoolid = School.id where School.id  =1
     */
}
