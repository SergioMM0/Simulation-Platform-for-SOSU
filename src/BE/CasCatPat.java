package BE;

import java.sql.Timestamp;

/*
    SELECT  [case].[id]  ,  [case].[name] , Description_of_the_condition , Cause_text , Causal_diagnose ,
     Causal_condition ,Citizens_want_goal , first_name , last_name,dateofBirth  ,gender , [Patient].[weight]  ,
     height,cpr,phone_number,blood_type,exercise,diet,alcohol,tobacco,observations , [Category].[name]
     ,[subcategory].[issue]
	fROM    Category join subcategory on Category.categoryid = subcategory.CategoryFid
				JOIN [Case] on subcategory.subcategoryID = [Case].subid
	             join SickPatient on [Case].[id] = [SickPatient].[caseid]
			     join [Patient]  on  SickPatient.patientid  = [Patient].[id]
			     join School  on  Patient.schoolid = School.id where School.id  =1
     */
public class CasCatPat {
    private int caseid ;
    private String casename;
    private String casedescription ;
    private String causetext ;
    private String casualdiagnose ;
    private String citizenwantgoal;
    private String firstname ;
    private String lastname ;
    private Timestamp dateofbirth ;
    private int height ;
    private int weight ;
    private String cpr ;
    private String phonenumber ;
    private String bloodtype ;
    private String exercise ;
    private String diet ;
    private String alcohol ;
    private String tobacco ;
    private String gender;
    private String observation ;
    private String catname ;
    private String subcatname ;

    public CasCatPat(int caseid, String casename, String casedescription, String causetext, String casualdiagnose, String citizenwantgoal, String firstname, String lastname, Timestamp dateofbirth, int height, int weight, String cpr, String phonenumber, String bloodtype, String exercise, String diet, String alcohol, String tobacco, String gender, String observation , String catname , String subcatname) {
        this.caseid = caseid;
        this.casename = casename;
        this.casedescription = casedescription;
        this.causetext = causetext;
        this.casualdiagnose = casualdiagnose;
        this.citizenwantgoal = citizenwantgoal;
        this.firstname = firstname;
        this.lastname = lastname;
        this.dateofbirth = dateofbirth;
        this.height = height;
        this.weight = weight;
        this.cpr = cpr;
        this.phonenumber = phonenumber;
        this.bloodtype = bloodtype;
        this.exercise = exercise;
        this.diet = diet;
        this.alcohol = alcohol;
        this.tobacco = tobacco;
        this.gender = gender;
        this.observation = observation;
        this.catname = catname ;
        this.subcatname = subcatname ;
    }

    public int getCaseid() {
        return caseid;
    }

    public void setCaseid(int caseid) {
        this.caseid = caseid;
    }

    public String getCasename() {
        return casename;
    }

    public void setCasename(String casename) {
        this.casename = casename;
    }

    public String getCasedescription() {
        return casedescription;
    }

    public void setCasedescription(String casedescription) {
        this.casedescription = casedescription;
    }

    public String getCausetext() {
        return causetext;
    }

    public void setCausetext(String causetext) {
        this.causetext = causetext;
    }

    public String getCasualdiagnose() {
        return casualdiagnose;
    }

    public void setCasualdiagnose(String casualdiagnose) {
        this.casualdiagnose = casualdiagnose;
    }

    public String getCitizenwantgoal() {
        return citizenwantgoal;
    }

    public void setCitizenwantgoal(String citizenwantgoal) {
        this.citizenwantgoal = citizenwantgoal;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Timestamp getDateofbirth() {
        return dateofbirth;
    }

    public void setDateofbirth(Timestamp dateofbirth) {
        this.dateofbirth = dateofbirth;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getCpr() {
        return cpr;
    }

    public void setCpr(String cpr) {
        this.cpr = cpr;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getBloodtype() {
        return bloodtype;
    }

    public void setBloodtype(String bloodtype) {
        this.bloodtype = bloodtype;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public String getCatname() {
        return catname;
    }

    public void setCatname(String catname) {
        this.catname = catname;
    }

    public String getSubcatname() {
        return subcatname;
    }

    public void setSubcatname(String subcatname) {
        this.subcatname = subcatname;
    }

    @Override
    public String toString() {
        return "CasCatPat{" +
                "caseid=" + caseid +
                ", casename='" + casename + '\'' +
                ", casedescription='" + casedescription + '\'' +
                ", causetext='" + causetext + '\'' +
                ", casualdiagnose='" + casualdiagnose + '\'' +
                ", citizenwantgoal='" + citizenwantgoal + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", dateofbirth=" + dateofbirth +
                ", height=" + height +
                ", weight=" + weight +
                ", cpr='" + cpr + '\'' +
                ", phonenumber='" + phonenumber + '\'' +
                ", bloodtype='" + bloodtype + '\'' +
                ", exercise='" + exercise + '\'' +
                ", diet='" + diet + '\'' +
                ", alcohol='" + alcohol + '\'' +
                ", tobacco='" + tobacco + '\'' +
                ", gender='" + gender + '\'' +
                ", observation='" + observation + '\'' +
                '}';
    }
}
