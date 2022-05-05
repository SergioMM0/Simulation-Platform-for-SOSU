package DAL.Interface;

import BE.Case;
import BE.Group;
import BE.Patient;
import DAL.util.DalException;

public interface DAOCasCatPat {
    void assignCasetoPatient(Patient patient , Case c ) throws DalException;

    void assignCaseToPatientToGroup(Patient p , Case c , Group g) throws DalException;
}
