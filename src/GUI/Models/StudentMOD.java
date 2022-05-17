package GUI.Models;

import BE.Group;
import BE.User;
import BLL.BLLFacade;
import BLL.BLLManager;
import DAL.util.DalException;

public class StudentMOD {
    private BLLFacade bll;

    public StudentMOD() {
        bll=new BLLManager();
    }
    public Group getGroupOf(User student){
        try {
            return bll.getGroupOf(student);
        } catch (DalException e) {
            e.printStackTrace();
        }
        return null;
    }
}
