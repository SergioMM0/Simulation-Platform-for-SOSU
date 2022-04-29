package DAL;

import BE.User;

public interface DALFacade {


    User verifyUsers(String username, String password);

}
