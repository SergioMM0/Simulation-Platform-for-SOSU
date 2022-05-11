package GUI.Models;

import BE.User;
import BLL.BLLFacade;
import BLL.BLLManager;
import DAL.util.DalException;

public class ManageStudentMOD {

    private BLLFacade bllFacade;

    public ManageStudentMOD(){
        bllFacade = new BLLManager();
    }

    public void addNewStudent(User user) throws DalException {
        bllFacade.addNewStudent(user);
    }

    public void updateStudent(User student) throws DalException {
        bllFacade.updateStudent(student);
    }
}
