package DAL.Manager1;

import BE.Case;
import BE.Group;
import BE.Patient;
import DAL.DataAccess.DataAccess;
import DAL.Interface.DAOCasCatPat;
import DAL.util.DalException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DALCasCatPat implements DAOCasCatPat {

    private final DataAccess dataAccess;

    public DALCasCatPat() {
        dataAccess = new DataAccess();
    }

    @Override
    public void assignCasetoPatient(Patient patient, Case c) throws DalException {
        try(Connection con = dataAccess.getConnection()){
            String sql = "insert into SickPatient (patientid , caseid) values  (?,?)";
            PreparedStatement prs = con.prepareStatement(sql);
            prs.setInt(1 ,patient.getId());
            prs.setInt(2 , c.getId());
            prs.executeUpdate();
        } catch (SQLException e){
            throw new DalException("Connection Lost" , e );
        }
    }

    @Override
    public void assignCaseToPatientToGroup(Patient p, Case c, Group g) throws DalException {
        try(Connection con = dataAccess.getConnection()) {
            String sql = "insert into SickPatient (patientid , caseid , Groupid) values  (?,?,?)";
            PreparedStatement prs = con.prepareStatement(sql);
            prs.setInt(1 , p.getId());
            prs.setInt(2 ,c.getId());
            prs.setInt(3 ,g.getId());
            prs.executeUpdate();
        } catch (SQLException e) {
           throw new DalException("Cant preform this task at this moment " , e);
        }
    }

}
