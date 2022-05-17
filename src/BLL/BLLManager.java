package BLL;

import BE.*;
import BLL.Exceptions.BLLException;
import DAL.DALFacade;
import DAL.Manager;
import DAL.util.DalException;

import java.security.InvalidParameterException;
import java.util.List;

public class BLLManager implements BLLFacade {

    private DALFacade dalFacade;

    public BLLManager() {
        dalFacade = new Manager();
    }

    @Override
    public User checkCredentials(String email, String password) throws DalException, BLLException {
        User logedUser = dalFacade.verifyUsers(email, password);
        if (logedUser == null) {
            throw new BLLException("Wrong email or password, please try again", new InvalidParameterException());
        }
        return logedUser;
    }

    @Override
    public Case createCase(Case newCase) throws DalException {
        return dalFacade.createCase(newCase);
    }

    @Override
    public Patient createPatient(Patient patient) throws DalException {
        return dalFacade.createPatient(patient);
    }

    @Override
    public List<Group> getAllGroups(int schoolID) throws DalException {
        return dalFacade.getAllGroups(schoolID);
    }

    @Override
    public List<Case> getAllCases(int schoolID) throws DalException {
        return dalFacade.getAllCases(schoolID);
    }

    @Override
    public List<Patient> getAllPatients(int schoolID) throws DalException {
        return dalFacade.getAllPatients(schoolID);
    }

    @Override
    public void addNewStudent(User user) throws DalException {
        dalFacade.addUser(user);
    }

    @Override
    public void updateStudent(User student) throws DalException {
        dalFacade.updateuser(student);
    }

    @Override
    public void deleteStudent(User student) throws DalException {
        dalFacade.deleteuser(student);
    }

    @Override
    public List<User> getAllStudent(int schoolID) throws DalException {
        return dalFacade.getAllUsers(schoolID);
    }

    @Override
    public void updatePatient(Patient patient) throws DalException {
        dalFacade.updatePatient(patient);
    }

    @Override
    public Group createNewGroup(Group group) throws DalException {
        return dalFacade.createGroup(group);
    }

    @Override
    public void updateGroup(Group selectedGroup) throws DalException {
        dalFacade.updateGroup(selectedGroup);
    }


    @Override
    public void addStudentToGroup(Group group, User student) throws DalException {
        dalFacade.addUsertoGroup(group, student);
    }

    @Override
    public void deleteGroup(Group group) throws DalException {
        dalFacade.deleteGroup(group);
    }

    @Override
    public void removeParticipant(Group group, User user) throws DalException {
        dalFacade.removeUserAndGroup(user, group);
        //TODO Implement
    }

    @Override
    public void updateCase(Case newCase) throws DalException {
        dalFacade.updateCase(newCase);
        //TODO implement
    }

    @Override
    public void deleteCase(Case selectedCase) throws DalException {
        dalFacade.deleteCase(selectedCase);
    }

    @Override
    public void deletePatient(Patient selectedPatient) throws DalException {
        dalFacade.deletePatient(selectedPatient);
    }

    @Override
    public List<School> getAllSchools() throws DalException {
        return dalFacade.getAllSchhol();
    }

    @Override
    public void createSchool(School school) throws DalException {
        dalFacade.createSchool(school);
    }

    @Override
    public void updateSchool(School school) throws DalException {
        dalFacade.updateSchool(school);
    }

    @Override
    public void deleteSchool(School school) throws DalException {
        dalFacade.deleteSchool(school);
    }

    @Override
    public void assignCaseToGroup(Case selectedCase, Group group, Patient patient) throws DalException {
        dalFacade.assignCaseToPatientToGroup(patient, selectedCase, group);
    }

    @Override
    public List<Case> getCasesAssignedTo(Group group) throws DalException {
        return dalFacade.getCasesAssignedTo(group);
        //TODO IMPLEMENT

    }

    @Override
    public List<User> getALLUsers(int schoolid, String utype) throws DalException {
        return dalFacade.getALLUsers(schoolid, utype);
    }

    @Override
    public Group getGroupOf(User student) throws DalException {
        return dalFacade.getGroupOf(student);
    }

    @Override
    public StudentQuestionnaire getQuestionnaireOf(Group group) throws DalException {
        return dalFacade.getQuestionnaireOf(group);
    }

    public List<User> searchForUser(String query) throws DalException {
        return dalFacade.searchForUser(query);
    }

    @Override
    public void saveStudentQuestionAnswer(StudentQuestionnaireAnswer answer) throws DalException {
        dalFacade.addStudentQuestionAnswer(answer);
    }

    @Override
    public StudentQuestion getFirstQuestion() throws DalException {
        return dalFacade.getFirstStudentQuestion();
    }

    @Override
    public StudentQuestion getNextQuestion(StudentQuestion question) throws DalException, BLLException {
        StudentQuestion s = dalFacade.getNextStudentQuestion(question.getId());

        return s;
    }

    @Override
    public StudentQuestion getPreviousQuestion(int currentQuestionId) throws BLLException, DalException {
        StudentQuestion s = dalFacade.getPreviousQuestion(currentQuestionId);

        return s;
    }

    @Override
    public StudentQuestionnaireAnswer getQuestionaireAnswer(int questionId, int questionaireId) throws DalException {
        return dalFacade.getQuestionaireAnswer(questionId, questionaireId);
    }


}
