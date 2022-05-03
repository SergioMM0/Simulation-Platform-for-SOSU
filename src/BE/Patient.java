package BE;

import java.sql.Timestamp;

public class Patient {
  private int id ;
  private String first_name ;
  private String last_name ;
  private Timestamp dateofBirth ;
  private String gender ;
  private int weight ;
  private int height;
  private String cpr ;
  private String phone_number ;
  private String blood_type ;
  private String exercise ;
  private String diet ;
  private boolean alcohol ;
  private boolean tobacco ;
  private String observations ;

    public Patient(int id, String first_name, String last_name, Timestamp dateofBirth, String gender, int weight,
                   int height, String cpr, String phone_number, String blood_type, String exercise, String diet, boolean alcohol,
                   boolean tobacco, String observations) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.dateofBirth = dateofBirth;
        this.gender = gender;
        this.weight = weight;
        this.height = height;
        this.cpr = cpr;
        this.phone_number = phone_number;
        this.blood_type = blood_type;
        this.exercise = exercise;
        this.diet = diet;
        this.alcohol = alcohol;
        this.tobacco = tobacco;
        this.observations = observations;
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

    public Timestamp getDateofBirth() {
        return dateofBirth;
    }

    public void setDateofBirth(Timestamp dateofBirth) {
        this.dateofBirth = dateofBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getCpr() {
        return cpr;
    }

    public void setCpr(String cpr) {
        this.cpr = cpr;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getBlood_type() {
        return blood_type;
    }

    public void setBlood_type(String blood_type) {
        this.blood_type = blood_type;
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

    public boolean isAlcohol() {
        return alcohol;
    }

    public void setAlcohol(boolean alcohol) {
        this.alcohol = alcohol;
    }

    public boolean isTobacco() {
        return tobacco;
    }

    public void setTobacco(boolean tobacco) {
        this.tobacco = tobacco;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id + ", first_name='" + first_name  + ", last_name='" + last_name + '}';
    }
}
