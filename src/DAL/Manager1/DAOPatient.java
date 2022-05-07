package DAL.Manager1;

import BE.Patient;
import BE.School;
import BE.User;
import DAL.DataAccess.DataAccess;
import DAL.util.DalException;
import java.sql.*;
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
                Timestamp dateofbirth = rs.getTimestamp("dateofBirth");
                String gender = rs.getString("gender");
                int weight = rs.getInt("weight");
                int height = rs.getInt("height");
                String cpr = rs.getString("cpr");
                String phonenumber = rs.getString("phone_number");
                String blood_type = rs.getString("blood_type");
                String exercise = rs.getString("exercise");
                String diet = rs.getString("diet");
                String alcohol = rs.getString("alcohol");
                String tobacco = rs.getString("tobacco");
                String observation = rs.getString("observations");
                int teacherid = rs.getInt("teacherid");
                Patient patient = new Patient(id,first_name,lastname,dateofbirth,gender,weight,height,
                        cpr,phonenumber,blood_type ,exercise,diet,alcohol,tobacco,
                        observation, schoolid , teacherid);
                patients.add(patient);
            }
            return patients;
        } catch (SQLException e) {
            throw new DalException("Connection Lost" , e);
        }

    }


    public void createPatient(Patient patient , School school) throws DalException {

        try (Connection con = dataAccess.getConnection()){
            String sql = "INSERT INTO Patient (first_name, last_name, dateofBirth, gender,weight ,height ,cpr , phone_number ,blood_type ,exercise ,diet ,alcohol,tobacco ,observations,schoolid) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
            PreparedStatement prs = con.prepareStatement(sql);
            prs.setString(1 , patient.getFirst_name());
            prs.setString(2 , patient.getLast_name());
            prs.setTimestamp(3,patient.getDateOfBirth());
            prs.setString(4 , patient.getGender());
            prs.setInt(5 ,patient.getWeight());
            prs.setInt(6,patient.getHeight());
            prs.setString(7 ,patient.getCpr());
            prs.setString(8 , patient.getPhoneNumber());
            prs.setString(9,patient.getBloodType());
            prs.setString(10,patient.getExercise());
            prs.setString(11, patient.getDiet());
            prs.setString(12,patient.getAlcohol());
            prs.setString(13,patient.getTobacco());
            prs.setString(14,patient.getObservations());
            prs.setInt(15,school.getId());
            prs.executeUpdate();
        } catch (SQLException e) {
            throw new DalException("Connection Lost " , e);
        }
    }


    public void updatepatient(Patient patient) throws DalException {

        try (Connection con = dataAccess.getConnection()){
            String sql = "Update Patient set first_name = ? , last_name = ? , dateoBirth = ? , gender = ? " +
                    ", weight = ? , height = ? , cpr = ? , phone_number = ? , blood_type = ? , exercise = ?  " +
                    ", diet = ? ,alcohol = ?,tobacco = ? ,observations = ? where id = ? ";
            PreparedStatement prs = con.prepareStatement(sql);
            prs.setString(1 , patient.getFirst_name());
            prs.setString(2 , patient.getLast_name());
            prs.setTimestamp(3,patient.getDateOfBirth());
            prs.setString(4 , patient.getGender());
            prs.setInt(5 ,patient.getWeight());
            prs.setInt(6,patient.getHeight());
            prs.setString(7 ,patient.getCpr());
            prs.setString(8 , patient.getPhoneNumber());
            prs.setString(9,patient.getBloodType());
            prs.setString(10,patient.getExercise());
            prs.setString(11, patient.getDiet());
            prs.setString(12,patient.getAlcohol());
            prs.setString(13,patient.getTobacco());
            prs.setString(14,patient.getObservations());
            prs.setInt(15, patient.getId());
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
