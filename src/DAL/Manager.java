package DAL;

import BE.*;
import DAL.DataAccess.DataAccess;
import DAL.util.DalException;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Manager implements DALFacade {

    private final DataAccess dataAccess;

    public Manager() {
        dataAccess = new DataAccess();
    }

    @Override
    public User verifyUsers(String useremail, String password)throws DalException {
        User user = null;

        try (Connection con = dataAccess.getConnection()) {
            String sql = " SELECT * FROM users where email = ? AND password = ?";
            PreparedStatement prs = con.prepareStatement(sql);
            prs.setString(1, useremail);
            prs.setString(2, password);
            ResultSet rs = prs.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("userid");
                String name = rs.getString("username");

                String usertype = rs.getString("usertype");
              user = new User(id , name , useremail , usertype );
            }
            return user;


        } catch (SQLException e) {
          throw new DalException("Connection Lost " , e);
        }
    }

    @Override
    public List<User> getAllUsers() throws DalException {
        ArrayList<User> users = new ArrayList<>();
        try (Connection con = dataAccess.getConnection()) {
            String sql = "SELECT * FROM users ";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("userid");
                String username = rs.getString("username");
                String email = rs.getString("email");
                String usertype = rs.getString("usertype");
                User user = new User(id, username, email, usertype);
                users.add(user);
            }
        } catch (SQLException e) {
            throw new DalException("Connection Lost " , e);
        }
        return users;
    }

    @Override
    public void updateuser(User user, String username, String email, String userType) throws DalException {
        try (Connection con = dataAccess.getConnection()) {
            String sql = "UPDATE users SET username = ?  , email = ?  , usertype = ?  WHERE userid = ? ";

            PreparedStatement prs = con.prepareStatement(sql);
            prs.setString(1, username);
            prs.setString(2, email);
            prs.setString(3, userType);
            prs.setInt(4, user.getId());

            prs.executeUpdate();

        } catch (SQLException e) {
            throw new DalException("Connection Lost " , e);
        }
    }

    @Override
    public void deleteuser(User user) throws DalException {
        try (Connection con = dataAccess.getConnection()) {

            String sql = "DELETE FROM users WHERE userid = ?";
            PreparedStatement prs = con.prepareStatement(sql);
            prs.setInt(1, user.getId());

            prs.executeUpdate();

        } catch (SQLException e) {
            throw new DalException("Connection Lost " , e);
        }
    }

    @Override
    public User addUser(String username, String password, String email, String usertype) throws DalException {
        User user ;
        try (Connection con = dataAccess.getConnection()) {
            String sql = "INSERT INTO users(username , password, email , usertype)" +
                    "VALUES  (?,?,?,?)";
            PreparedStatement prs = con.prepareStatement(sql);
            prs.setString(1, username);
            prs.setString(2, password);
            prs.setString(3, email);
            prs.setString(4, usertype);
            prs.executeUpdate();
            user = new User(newestidforuser(), username, email, usertype);

        } catch (SQLException e) {
            throw new DalException("Connection Lost " , e);
        }
        return user;
    }

    private int newestidforuser() throws DalException {
        int newid = -1;

        try (Connection con = dataAccess.getConnection()) {
            String sql = "SELECT TOP(1) * FROM users ORDER by userid desc";
            PreparedStatement prs = con.prepareStatement(sql);
            ResultSet rs = prs.executeQuery();
            while (rs.next()) {
                newid = rs.getInt("id");
            }
        } catch (SQLException e) {
            throw new DalException("Connection Lost " , e);
        }
        return newid;
    }

    private int newestidforPatient() throws DalException {
        int newid = -1;

        try (Connection con = dataAccess.getConnection()) {
            String sql = "SELECT TOP(1) * FROM Patient ORDER by id desc";
            PreparedStatement prs = con.prepareStatement(sql);
            ResultSet rs = prs.executeQuery();
            while (rs.next()) {
                newid = rs.getInt("id");
            }
        } catch (SQLException e) {
            throw new DalException("Connection Lost " , e);
        }
        return newid;
    }

    private int newestidforCases() throws DalException {
        int newid = -1;

        try (Connection con = dataAccess.getConnection()) {
            String sql = "SELECT TOP(1) * FROM Case ORDER by id desc";
            PreparedStatement prs = con.prepareStatement(sql);
            ResultSet rs = prs.executeQuery();
            while (rs.next()) {
                newid = rs.getInt("id");
            }
        } catch (SQLException e) {
            throw new DalException("Connection Lost " , e);
        }
        return newid;
    }

    @Override
    public List<User> searchForUser(String query) throws DalException {
        List<User> users = new ArrayList<>();

        String stringquery = "%" + query + "%";

        try (Connection con = dataAccess.getConnection()) {
            String command = "Select * from users where  username like ? or usertype like ? or email like ? ";
            PreparedStatement prs = con.prepareStatement(command);
            prs.setString(1, stringquery);
            prs.setString(2, stringquery);
            prs.setString(3, stringquery);
            prs.execute();
            ResultSet rs = prs.getResultSet();
            while (rs.next()) {

                int id = rs.getInt("id");
                String username = rs.getString("username");
                String email = rs.getString("email");
                String usertype = rs.getString("usertype");

                User user = new User(id, username, email, usertype);
                users.add(user);
            }

        } catch (SQLException e) {
            throw new DalException("Connection Lost " , e);
        }

        return users;
    }

    @Override
    public List<Category> getAllCategories() throws DalException {
        ArrayList<Category> categorirs = new ArrayList<>();

        try (Connection con = dataAccess.getConnection()) {
            String sql = "Select * from [dbo].[Category]";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                Category category = new Category(id, name);
                categorirs.add(category);
            }
            return categorirs;
        } catch (SQLException e) {
            throw new DalException("Connection Lost ", e);
        }
    }

    @Override
    public List<SubCategory> getAllSubCategories(Category category) throws DalException {
        ArrayList<SubCategory> subCategories = new ArrayList<>();
        try(Connection connection = dataAccess.getConnection()) {
            String sql = "select * from [dbo].[subcategory] where CategoryFid = ?";
            PreparedStatement prs = connection.prepareStatement(sql);
            prs.setInt(1 , category.getId());
            prs.execute();
            ResultSet rs = prs.getResultSet();
            while (rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                SubCategory subCategory = new SubCategory(id , name);
                subCategories.add(subCategory);
            }
            return subCategories;
        } catch (SQLException e) {
            throw new DalException("Connection Lost" , e);
        }
    }

    @Override
    public List<Patient> getAllPatients() throws DalException {
        ArrayList<Patient> patients = new ArrayList<>();
        try(Connection con = dataAccess.getConnection()) {
            String sql = "SELECT * from Patient";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()){
                int id = rs.getInt("id");
                String first_name = rs.getString("first_name");
                String lastname = rs.getString("");
                Timestamp dateofbirth = rs.getTimestamp("");
                String gender = rs.getString("");
                int weight = rs.getInt("");
                int height = rs.getInt("");
                String cpr = rs.getString("");
                String phonenumber = rs.getString("");
                String blood_type = rs.getString("");
                String exercise = rs.getString("");
                String diet = rs.getString("");
                boolean alcohol = rs.getBoolean("");
                boolean tobacco = rs.getBoolean("");
                String observation = rs.getString("");

                Patient patient = new Patient(id,first_name,lastname,dateofbirth,gender,weight,height,
                                            cpr,phonenumber,blood_type ,exercise,diet,alcohol,tobacco,
                                            observation);
                patients.add(patient);
            }
            return patients;
        } catch (SQLException e) {
           throw new DalException("Connection Lost" , e);
        }

    }

    @Override
    public Patient createPatient( String first_name, String last_name, Timestamp dateofBirth, String gender, int weight, int height, String cpr, String phone_number, String blood_type, String exercise, String diet, boolean alcohol, boolean tobacco, String observations) throws DalException {

        try (Connection con = dataAccess.getConnection()){
            String sql = "INSERT INTO Patient (first_name, last_name, dateofBirth, gender,weight ,height ,cpr , phone_number ,blood_type ,exercise ,diet ,alcohol,tobacco ,observations ) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
            PreparedStatement prs = con.prepareStatement(sql);
            prs.setString(1 , first_name);
            prs.setString(2 , last_name);
            prs.setTimestamp(3,dateofBirth);
            prs.setString(4 , gender);
            prs.setInt(5 ,weight);
            prs.setInt(6,height);
            prs.setString(7 ,cpr);
            prs.setString(8 ,phone_number);
            prs.setString(9,blood_type);
            prs.setString(10,exercise);
            prs.setString(11, diet);
            prs.setBoolean(12,alcohol);
            prs.setBoolean(13,tobacco);
            prs.setString(14,observations);
            prs.executeUpdate();
            Patient patient = new Patient(newestidforPatient(),first_name,last_name,dateofBirth,gender,weight,height,cpr,phone_number,blood_type,exercise,diet,alcohol,tobacco,observations);
            return patient ;
        } catch (SQLException e) {
           throw new DalException("Connectin Lost " , e);
        }
    }

    @Override
    public void updatepatient(Patient patient, String first_name, String last_name, Timestamp dateofBirth, String gender, int weight, int height, String cpr, String phone_number, String blood_type, String exercise, String diet, boolean alcohol, boolean tobacco, String observations) throws DalException {

        try (Connection con = dataAccess.getConnection()){
            String sql = "Update Patient set first_name = ? , last_name = ? , dateoBirth = ? , gender = ? , weight = ? , height = ? , cpr = ? , phone_number = ? , blood_type = ? , exercise = ?  , diet = ? ,alcohol = ?,tobacco = ? ,observations = ? where id = ? ";
            PreparedStatement prs = con.prepareStatement(sql);
            prs.setString(1 ,first_name);
            prs.setString(2 , last_name);
            prs.setTimestamp(3 , dateofBirth);
            prs.setString(4 , gender);
            prs.setInt(5 , weight);
            prs.setInt(6 ,height);
            prs.setString(7 ,phone_number);
            prs.setString(8 , blood_type);
            prs.setString(9 ,exercise);
            prs.setString(10 , diet);
            prs.setBoolean(11 , alcohol);
            prs.setBoolean(12 , tobacco);
            prs.setString(13 , observations);

            prs.executeUpdate();
        } catch (SQLException e) {
           throw new DalException("Connection Lost" , e);
        }
    }

    @Override
    public void deletePatient(Patient patient) throws DalException {
        try(Connection con = dataAccess.getConnection()){
            String sql = "DELETE FROM Patient WHERE id = ?";
            PreparedStatement prs = con.prepareStatement(sql);
            prs.setInt(1 , patient.getId());
            prs.executeUpdate();
        } catch (SQLException e) {
           throw new DalException("Connection Lost" , e);
        }
    }

    @Override
    public List<Case> getAllCases() throws DalException {
        ArrayList<Case> cases = new ArrayList<>();
        try(Connection connection = dataAccess.getConnection()){
            String sql = "SELECT * from Case";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()){
                int id = rs.getInt("id");
                String name = ("name");
                String description_of_the_condition = rs.getString("Description_of_the_condition");
                String cause_text = rs.getString("Cause_text");
                String causal_diagnose = rs.getString("Causal_diagnose");
                String causal_condition = rs.getString("Causal_condition");
                String citizens_want_goal = rs.getString("Citizens_want_goal");

                Case c = new Case(newestidforCases() , name ,description_of_the_condition,cause_text,causal_diagnose,causal_condition,citizens_want_goal);
                cases.add(c);
            }
            return cases;
        } catch (SQLException e) {
           throw new DalException("Connection Lost" , e);
        }
    }

    @Override
    public Case createCase(String name, String description_of_the_condition, String cause_text, String causal_diagnose, String causal_condition, String citizens_want_goal , Category category , SubCategory subCategory) throws DalException {
       try(Connection con = dataAccess.getConnection()) {
           String sql = "INSERT INTO [dbo].[Case] ( name ,Description_of_the_condition, Cause_text,Causal_diagnose, Causal_condition ,Citizens_want_goal,catid , subid) VALUES (?,?,?,?,?,?,?);" ;
           PreparedStatement prs = con.prepareStatement(sql);
           prs.setString(1 , name);
           prs.setString(2 ,description_of_the_condition);
           prs.setString(3 , cause_text);
           prs.setString(4 ,causal_diagnose);
           prs.setString(5 , causal_condition);
           prs.setString(6 ,citizens_want_goal);
           prs.setInt(7 ,category.getId());
           prs.setInt(8 ,subCategory.getId());
            prs.executeUpdate();
           Case c = new Case(newestidforCases(), name,description_of_the_condition,cause_text,causal_diagnose,causal_condition,citizens_want_goal);
           return c ;
       } catch (SQLException e) {
          throw new DalException("Connection Lost" , e);
       }

    }

    @Override
    public void updateCase(Case c, String name, String description_of_the_condition, String cause_text, String causal_diagnose, String causal_condition, String citizens_want_goal) throws DalException {
        try(Connection con = dataAccess.getConnection()){
            String sql = "Update Case set name = ? , description_of_the_condition = ? , cause_text = ?  , causal_diagnose = ? , causal_condition = ? , citizens_want_goal = ?  where id = ? ";
            PreparedStatement prs = con.prepareStatement(sql);
            prs.setString(1 , name);
            prs.setString(2 ,description_of_the_condition);
            prs.setString(3 , cause_text);
            prs.setString(4 ,causal_diagnose);
            prs.setString(5 , causal_condition);
            prs.setString(6 ,citizens_want_goal);
            prs.setInt(7,c.getId());
            prs.executeUpdate();

        } catch (SQLException e) {
           throw new DalException("Connection Lost" , e);
        }
    }

    @Override
    public void deleteCase(Case c) throws DalException {
       try(Connection con = dataAccess.getConnection()){
         String sql = "DELETE FROM Case WHERE id = ?";
         PreparedStatement prs = con.prepareStatement(sql);
         prs.setInt(1 , c.getId());
         prs.executeUpdate();
       } catch (SQLException e) {
         throw new DalException("Connection Lost" , e);

       }
    }

    @Override
    public void assignCasetoPatient(Patient patient, Case c) throws DalException {
        try(Connection con = dataAccess.getConnection()){
            String sql = "insert into SickPatient (patientid , caseid) values  (?,?)";
            PreparedStatement prs = con.prepareStatement(sql);
            prs.setInt(1 ,patient.getId());
            prs.setInt(2 , c.getId());
            prs.executeUpdate();
        } catch (SQLException e){
            throw new DalException("Connection Lost" , e );
        }
    }


}
