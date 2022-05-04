package DAL.Interface;

import BE.User;
import DAL.util.DalException;

import java.util.List;

public interface DAOUser {
    User verifyUsers(String useremail, String password) throws DalException;

    List<User> getAllUsers() throws DalException;

    void updateuser(User user , String username , String email , String userType) throws DalException;

    void deleteuser(User user) throws DalException;

    User addUser(String username , int schoolid , String password , String email , String usertype) throws DalException;

    List<User> searchForUser (String query) throws DalException;
}
