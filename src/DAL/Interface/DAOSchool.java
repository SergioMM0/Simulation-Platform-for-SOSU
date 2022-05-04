package DAL.Interface;

import BE.School;
import DAL.util.DalException;

import java.util.List;

public interface DAOSchool {

    List<School> getAllSchhol() throws DalException;

    School createSchool(String name)throws DalException;

    void updateSchool(String name , School school)throws DalException;

    void deleteSchool(School school)throws DalException;

}
