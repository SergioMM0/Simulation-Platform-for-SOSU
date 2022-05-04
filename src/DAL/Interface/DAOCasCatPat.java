package DAL.Interface;

import BE.Case;
import BE.Patient;
import DAL.util.DalException;

public interface DAOCasCatPat {
    void assignCasetoPatient(Patient patient , Case c ) throws DalException;
}
