package BLL;

import BE.*;
import BLL.Exceptions.BLLException;
import DAL.util.DalException;
import com.sun.security.auth.module.LdapLoginModule;

import java.util.List;

public interface BLLFacade {

    User checkCredentials(String email, String password) throws DalException, BLLException;

    void createCase(Case newCase) throws DalException;

    void createPatient(Patient patient) throws DalException;

    void saveStudentQuestionAnswer(StudentQuestionnaireAnswer answer) throws DalException;

    StudentQuestion getFirstQuestion() throws DalException;

    StudentQuestion getNextQuestion(StudentQuestion question) throws DalException ,BLLException;

    List<Group> getAllGroups(int schoolID) throws DalException;

    List<Case> getAllCases(int schoolID) throws DalException;

    List<Patient> getAllPatients(int schoolID) throws DalException;

    void addNewStudent(User user) throws DalException;

    void updateStudent(User student)throws DalException;

    void deleteStudent(User student) throws DalException;

    List<User> getAllStudent(int schoolID) throws DalException;

    void updatePatient(Patient patient) throws DalException;

    void createNewGroup(Group group) throws DalException;

    void updateGroup(Group selectedGroup) throws DalException;


    StudentQuestion getPreviousQuestion(int currentQuestionId) throws BLLException, DalException;

    StudentQuestionnaireAnswer getQuestionaireAnswer(int questionId, int questionaireId) throws DalException;
    void addStudentToGroup(Group group, User student) throws DalException;

    void deleteGroup(Group group) throws DalException;

    void removeParticipant(Group group, User user) throws DalException;

    void updateCase(Case newCase) throws DalException;
}


