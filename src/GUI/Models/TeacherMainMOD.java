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
    private ObservableList<String> allCategories;
    private ObservableList<String> subCategories;
    private ObservableList<User> allStudents;

    public TeacherMainMOD(){
        bllFacade = new BLLManager();
        allGroups = FXCollections.observableArrayList();
        allCases = FXCollections.observableArrayList();
        allPatients = FXCollections.observableArrayList();
        allCategories = FXCollections.observableArrayList();
        subCategories = FXCollections.observableArrayList();
        allStudents = FXCollections.observableArrayList();
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
    }

    public ObservableList<User> getAllStudents(int schoolID) throws DalException {
        allStudents.addAll(bllFacade.getAllStudent(schoolID));
        return allStudents;
    }






    //TODO DELETE THE FOLLOWING WHEN IMPLEMENTED TO READ FROM FILE

    public ObservableList<String> getGenders() {
        ObservableList<String> genders = FXCollections.observableArrayList();
        genders.add("Male");
        genders.add("Female");
        genders.add("Lockheed Martin F-16 Fighting Falcon lol");
        return genders;
    }

    /*

    public ObservableList<String> getBloodTypes() {
        ObservableList<String> bloodTypes = FXCollections.observableArrayList();
        bloodTypes.add("A");
        bloodTypes.add("B");
        bloodTypes.add("AB");
        bloodTypes.add("0");
        return bloodTypes;
    }

    public ObservableList<String> getExerciseOptions() {
        ObservableList<String> exercise = FXCollections.observableArrayList();
        exercise.add("+5 days a week");
        exercise.add("2-3 days a week");
        exercise.add("1 day a week");
        exercise.add("I'm a lazy a$$ ****");
        return exercise;
    }

    public ObservableList<String> getDietOptions() {
        ObservableList<String> diet = FXCollections.observableArrayList();
        diet.add("Lose diet");
        diet.add("Strict diet");
        diet.add("Doesn't have a diet plan");
        return diet;
    }

    public ObservableList<String> getAlcoholOptions() {
        ObservableList<String> alcohol = FXCollections.observableArrayList();
        alcohol.add("Daily");
        alcohol.add("Weekends");
        alcohol.add("Sporadic");
        alcohol.add("Never");
        return alcohol;
    }

    public ObservableList<String> getTobaccoOptions() {
        ObservableList<String> tobacco = FXCollections.observableArrayList();
        tobacco.add("SnoopDog style");
        tobacco.add("10-20 cigarettes a day");
        tobacco.add("0-10 cigarettes a day");
        tobacco.add("Social smoker");
        tobacco.add("I got a ferrari bcs I don't smoke");
        return tobacco;
    }

     */
}
