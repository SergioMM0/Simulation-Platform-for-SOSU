package DAL.Interface;

import BE.Patient;
import DAL.util.DalException;

import java.sql.Timestamp;
import java.util.List;

public interface DAOPatient {
    List<Patient> getAllPatients(int schoolid) throws DalException;

    Patient createPatient(String first_name, String last_name, Timestamp dateofBirth, String gender, int weight, int height, String cpr, String phone_number, String blood_type, String exercise, String diet, String alcohol, String tobacco, String observations , int schoolid , int teacherid) throws DalException;

    void updatepatient(Patient patient ,String first_name, String last_name, Timestamp dateofBirth, String gender, int weight, int height, String cpr, String phone_number, String blood_type, String exercise, String diet, String alcohol, String tobacco, String observations) throws DalException;

    void deletePatient(Patient patient)throws DalException ;
}
