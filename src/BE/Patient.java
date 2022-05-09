package BE;

import java.time.LocalDate;

public class Patient {
    private int id;
    private String first_name;
    private String last_name;
    private LocalDate dateOfBirth;
    private String gender;
    private String weight;
    private String height;
    private String cpr;
    private String phoneNumber;
    private String bloodType;
    private String exercise;
    private String diet;
    private String alcohol;
    private String tobacco;
    private String observations;
    private int schoolid;

    public Patient(int id, String first_name, String last_name, LocalDate dateOfBirth, String gender, String weight,
                   String height, String cpr, String phoneNumber, String bloodType, String exercise, String diet, String alcohol,
                   String tobacco, String observations, int schoolid) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.weight = weight;
        this.height = height;
        this.cpr = cpr;
        this.phoneNumber = phoneNumber;
        this.bloodType = bloodType;
        this.exercise = exercise;
        this.diet = diet;
        this.alcohol = alcohol;
        this.tobacco = tobacco;
        this.observations = observations;
        this.schoolid = schoolid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getCpr() {
        return cpr;
    }

    public void setCpr(String cpr) {
        this.cpr = cpr;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public String getExercise() {
        return exercise;
    }

    public void setExercise(String exercise) {
        this.exercise = exercise;
    }

    public String getDiet() {
        return diet;
    }

    public void setDiet(String diet) {
        this.diet = diet;
    }

    public String getAlcohol() {
        return alcohol;
    }

    public void setAlcohol(String alcohol) {
        this.alcohol = alcohol;
    }

    public String getTobacco() {
        return tobacco;
    }

    public void setTobacco(String tobacco) {
        this.tobacco = tobacco;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    public int getSchoolid() {
        return schoolid;
    }

    public void setSchoolid(int schoolid) {
        this.schoolid = schoolid;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", gender='" + gender + '\'' +
                ", weight=" + weight +
                ", height=" + height +
                ", cpr='" + cpr + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", bloodType='" + bloodType + '\'' +
                ", exercise='" + exercise + '\'' +
                ", diet='" + diet + '\'' +
                ", alcohol='" + alcohol + '\'' +
                ", tobacco='" + tobacco + '\'' +
                ", observations='" + observations + '\'' +
                ", schoolid=" + schoolid +
                '}';
    }
}
