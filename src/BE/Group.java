package BE;

import java.util.List;

public class Group {

    private int id;
    private String name;
    private List<User> members;
    private int SchoolID ;

    public Group(int id, String name, List<User> members , int SchoolID) {
        this.id = id;
        this.name = name;
        this.members = members;
        this.SchoolID = SchoolID ;
    }

    public Group(String name, List<User> members, int schoolID) {
        this.name = name;
        this.members = members;
        SchoolID = schoolID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getMembers() {
        return members;
    }

    public void setMembers(List<User> members) {
        this.members = members;
    }

    public int getParticipants(){
        return this.members.size();
    }

    public int getSchoolID() {
        return SchoolID;
    }

    public void setSchoolID(int schoolID) {
        SchoolID = schoolID;
    }

    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", members=" + members +
                ", SchoolID=" + SchoolID +
                '}';
    }
}

