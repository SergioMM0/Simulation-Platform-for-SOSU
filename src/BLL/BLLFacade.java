package BLL;

import BE.User;
import BLL.Exceptions.BLLException;
import DAL.util.DalException;

public interface BLLFacade {
    User checkCredentials(String email, String password) throws DalException, BLLException;
}
