package DAL.Manager1;

import BE.Patient;
import DAL.DataAccess.DataAccess;
import DAL.util.DalException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DAOPatient {
    private final DataAccess dataAccess;

    public DAOPatient() {
        dataAccess = new DataAccess();
    }

    public List<Patient> getAllPatients(int schoolid) throws DalException {
        ArrayList<Patient> patients = new ArrayList<>();
        try(Connection con = dataAccess.getConnection()) {
            String sql = "SELECT * from Patient where schoolid = ? ";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, schoolid);
            statement.execute();
            ResultSet rs = statement.getResultSet();
            while (rs.next()){
                int id = rs.getInt("id");
                String first_name = rs.getString("first_name");
                String lastname = rs.getString("last_name");
                Date dateofbirth = rs.getDate("dateofBirth");
                String gender = rs.getString("gender");
                String  weight = rs.getString("weight");
                String  height = rs.getString("height");
                String cpr = rs.getString("cpr");
                String phonenumber = rs.getString("phone_number");
                ArrayList<String> observations = getObservationsOf(id);
                Patient patient = new Patient(id,first_name,lastname,convertToLocalDateViaSqlDate(dateofbirth),gender,weight,height,
                        cpr,phonenumber, observations, schoolid );
                patients.add(patient);
            }
            return patients;
        } catch (SQLException e) {
            throw new DalException("Connection Lost" , e);
        }

    }

    public LocalDate convertToLocalDateViaSqlDate(Date dateToConvert) {
        return new java.sql.Date(dateToConvert.getTime()).toLocalDate();
    }

    public void createPatient(Patient patient ) throws DalException {

        try (Connection con = dataAccess.getConnection()){
            String sql = "INSERT INTO Patient (first_name, last_name, dateofBirth, gender,weight ,height ,cpr ," +
                    " phone_number ,observations,schoolid) " +
                    "VALUES (?,?,?,?,?,?,?,?,?,?);";
            PreparedStatement prs = con.prepareStatement(sql);
            prs.setString(1 , patient.getFirst_name());
            prs.setString(2 , patient.getLast_name());
            prs.setDate(3, Date.valueOf(patient.getDateOfBirth()));
            prs.setString(4 , patient.getGender());
            prs.setString(5 ,patient.getWeight());
            prs.setString(6,patient.getHeight());
            prs.setString(7 ,patient.getCpr());
            prs.setString(8 , patient.getPhoneNumber());
            prs.setInt(10,patient.getSchoolId());
            prs.executeUpdate();
        } catch (SQLException e) {
            throw new DalException("Connection Lost " , e);
        }
    }


    public void updatepatient(Patient patient) throws DalException {

        try (Connection con = dataAccess.getConnection()){
            String sql = "Update Patient set first_name = ? , last_name = ? , dateofBirth = ? , gender = ? " +
                    ", weight = ? , height = ? , cpr = ? , phone_number = ? , observations = ? where id = ? ";
            PreparedStatement prs = con.prepareStatement(sql);
            prs.setString(1 , patient.getFirst_name());
            prs.setString(2 , patient.getLast_name());
            prs.setDate(3,Date.valueOf(patient.getDateOfBirth()));
            prs.setString(4 , patient.getGender());
            prs.setString(5 ,patient.getWeight());
            prs.setString(6,patient.getHeight());
            prs.setString(7 ,patient.getCpr());
            prs.setString(8 , patient.getPhoneNumber());
            prs.setInt(10, patient.getId());
            prs.executeUpdate();
        } catch (SQLException e) {
            throw new DalException("Connection Lost" , e);
        }
    }


    public void deletePatient(Patient patient) throws DalException {
        try(Connection con = dataAccess.getConnection()){
            String sql = "DELETE FROM Patient WHERE id = ?";
            PreparedStatement prs = con.prepareStatement(sql);
            prs.setInt(1 , patient.getId());
            prs.executeUpdate();
        } catch (SQLException e) {
            throw new DalException("Connection Lost" , e);
        }
    }

    private ArrayList<String> getObservationsOf(int id) throws DalException{
        ArrayList<String> observations = new ArrayList<>();
        try(Connection connection = dataAccess.getConnection()){
            String sql = "SELECT [content] FROM observationstable WHERE [patientid] = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1,id);
            st.execute();
            ResultSet rs = st.getResultSet();
            while(rs.next()){
                observations.add(rs.getString("content"));
            }
            return observations;
        }catch(SQLException sqlException){
            throw new DalException("Not able to retrieve the Observations",sqlException);
        }
    }

    private int newestidforPatient() throws DalException {
        int newid = -1;

        try (Connection con = dataAccess.getConnection()) {
            String sql = "SELECT TOP(1) * FROM Patient ORDER by id desc";
            PreparedStatement prs = con.prepareStatement(sql);
            ResultSet rs = prs.executeQuery();
            while (rs.next()) {
                newid = rs.getInt("id");
            }
        } catch (SQLException e) {
            throw new DalException("Connection Lost " , e);
        }
        return newid;
    }
}
