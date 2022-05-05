package DAL.Manager1;

import BE.Patient;
import DAL.DataAccess.DataAccess;
import DAL.Interface.DAOPatient;
import DAL.util.DalException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DALPatient implements DAOPatient {
    private final DataAccess dataAccess;

    public DALPatient() {
        dataAccess = new DataAccess();
    }
    @Override
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

    @Override
    public Patient createPatient( String first_name, String last_name, Timestamp dateofBirth, String gender, int weight, int height, String cpr, String phone_number, String blood_type, String exercise, String diet, String alcohol, String tobacco, String observations , int schoolid , int teacherid) throws DalException {

        try (Connection con = dataAccess.getConnection()){
            String sql = "INSERT INTO Patient (first_name, last_name, dateofBirth, gender,weight ,height ,cpr , phone_number ,blood_type ,exercise ,diet ,alcohol,tobacco ,observations,schoolid,teacherid ) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
            PreparedStatement prs = con.prepareStatement(sql);
            prs.setString(1 , first_name);
            prs.setString(2 , last_name);
            prs.setTimestamp(3,dateofBirth);
            prs.setString(4 , gender);
            prs.setInt(5 ,weight);
            prs.setInt(6,height);
            prs.setString(7 ,cpr);
            prs.setString(8 ,phone_number);
            prs.setString(9,blood_type);
            prs.setString(10,exercise);
            prs.setString(11, diet);
            prs.setString(12,alcohol);
            prs.setString(13,tobacco);
            prs.setString(14,observations);
            prs.setInt(15,schoolid);
            prs.setInt(16,teacherid);
            prs.executeUpdate();
            Patient patient = new Patient(newestidforPatient(),first_name,last_name,dateofBirth,gender,weight,height,cpr,phone_number,blood_type,exercise,diet,alcohol,tobacco,observations , schoolid , teacherid);
            return patient ;
        } catch (SQLException e) {
            throw new DalException("Connection Lost " , e);
        }
    }

    @Override
    public void updatepatient(Patient patient, String first_name, String last_name, Timestamp dateofBirth, String gender, int weight, int height, String cpr, String phone_number, String blood_type, String exercise, String diet, String alcohol, String tobacco, String observations) throws DalException {

        try (Connection con = dataAccess.getConnection()){
            String sql = "Update Patient set first_name = ? , last_name = ? , dateoBirth = ? , gender = ? , weight = ? , height = ? , cpr = ? , phone_number = ? , blood_type = ? , exercise = ?  , diet = ? ,alcohol = ?,tobacco = ? ,observations = ? where id = ? ";
            PreparedStatement prs = con.prepareStatement(sql);
            prs.setString(1 ,first_name);
            prs.setString(2 , last_name);
            prs.setTimestamp(3 , dateofBirth);
            prs.setString(4 , gender);
            prs.setInt(5 , weight);
            prs.setInt(6 ,height);
            prs.setString(7 ,phone_number);
            prs.setString(8 , blood_type);
            prs.setString(9 ,exercise);
            prs.setString(10 , diet);
            prs.setString(11 , alcohol);
            prs.setString(12 , tobacco);
            prs.setString(13 , observations);

            prs.executeUpdate();
        } catch (SQLException e) {
            throw new DalException("Connection Lost" , e);
        }
    }

    @Override
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
