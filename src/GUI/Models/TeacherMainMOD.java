package GUI.Models;

import BE.Case;
import BE.Group;
import BE.Patient;
import BE.User;
import BLL.BLLFacade;
import BLL.BLLManager;
import DAL.util.DalException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Locale;

public class TeacherMainMOD {

    private BLLFacade bllFacade;
    private ObservableList<Group> allGroups;
    private ObservableList<Case> allCases;
    private ObservableList<Patient> allPatients;
    private ObservableList<User> allStudents;
    private ObservableList<User> groupParticipants;

    public TeacherMainMOD(){
        bllFacade = new BLLManager();
        allGroups = FXCollections.observableArrayList();
        allCases = FXCollections.observableArrayList();
        allPatients = FXCollections.observableArrayList();
        allStudents = FXCollections.observableArrayList();
        groupParticipants = FXCollections.observableArrayList();
    }


    public ObservableList<Group> getAllGroups(int schoolID) throws DalException {
        allGroups.addAll(bllFacade.getAllGroups(schoolID));
        return allGroups;
    }

    public ObservableList<Case> getAllCases(int schoolID) throws DalException{
        allCases.addAll(bllFacade.getAllCases(schoolID));
        return allCases;
    }

    public ObservableList<Patient> getAllPatients(int schoolID) throws DalException {
        allPatients.addAll(bllFacade.getAllPatients(schoolID));
        return allPatients;
    }

    public void addPatientToList(Patient patient) {
        allPatients.add(patient);
    }

    public ObservableList<Patient> getObservablePatients() {
        return allPatients;
    }

    public void addCaseToList(Case newCase) {
        allCases.add(newCase);
    }

    public ObservableList<Case> getObservableCases(){
        return allCases;
    }

    public void addObservableStudent(User user){
        allStudents.add(user);
    }

    public ObservableList<User> getObservableStudents(){
        return allStudents;
    }

    public void updateStudentInTable(User student) {
        for(User user : allStudents){
            if(user.getName().equals(student.getName())){
                user.setName(student.getName());
                user.setEmail(student.getEmail());
            }
        }
    }

    public void deleteStudent(User student) throws DalException {
        bllFacade.deleteStudent(student);
        allStudents.remove(student);
    }

    public ObservableList<User> getAllStudents(int schoolID) throws DalException {
        allStudents.addAll(bllFacade.getAllStudent(schoolID));
        return allStudents;
    }

    public void updatePatient(Patient patient) throws DalException{
        bllFacade.updatePatient(patient);
        updatePatientInTable(patient);
    }

    public void updatePatientInTable(Patient patient){
        for(Patient p : allPatients){
            if(p.getFirst_name().equals(patient.getFirst_name())){
                p = patient; //TODO Implement method to make sure name is not repeated
            }
        }
    }

    public void addObservableGroup(Group group){
        allGroups.add(group);
    }

    public ObservableList<Group> getObservableGroups() {
        return allGroups;
    }


    public void updateObservableGroup(Group group) {
        for(Group g : allGroups){
            if(g.getName().equals(group.getName())){
                g = group; //TODO implement a method to make sure name is not repeated
            }
        }
    }

    public ObservableList<User> groupIsSelected(Group group) {
        groupParticipants.clear();
        groupParticipants.addAll(group.getMembers());
        return groupParticipants;
    }


    public void addStudentToGroup(Group group, User student) throws DalException{
        bllFacade.addStudentToGroup(group,student);
    }

    //TODO DELETE THE FOLLOWING WHEN IMPLEMENTED TO READ FROM FILE

    public ObservableList<String> getGenders() {
        ObservableList<String> genders = FXCollections.observableArrayList();
        genders.add("Male");
        genders.add("Female");
        genders.add("Lockheed Martin F-16 Fighting Falcon lol");
        return genders;
    }
}
