package DAL.Interface;

import BE.Case;
import BE.Category;
import BE.Patient;
import BE.SubCategory;
import DAL.util.DalException;

import java.util.List;

public interface DAOCase {

    List<Case> getAllCases(int schoolid) throws DalException;

    Case createCase(String name, String description_of_the_condition, String cause_text, String causal_diagnose, String causal_condition, String citizens_want_goal , Category category , SubCategory subCategory)throws DalException;

    void updateCase(Case c , String name, String description_of_the_condition, String cause_text, String causal_diagnose, String causal_condition, String citizens_want_goal)throws  DalException ;

    void deleteCase(Case c) throws DalException ;


}
