package DAL.Interface;

import BE.Group;
import BE.User;
import DAL.util.DalException;

import java.util.List;

public interface DAOGroup {

    List<Group> getAllGroups()throws DalException;

    Group createGroup(String name)throws DalException;

    void updateGroup(Group group , String name )throws DalException;

    void deleteGroup(Group group)throws DalException;

    List<User> getUsersInGroup(int id)throws DalException;

    void addUsertoGroup(Group group , User user)throws DalException;

    void removeUserFromGroup(User user)throws DalException;
}
