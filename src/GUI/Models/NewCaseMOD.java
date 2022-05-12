package GUI.Models;

import BE.Case;
import BLL.BLLFacade;
import BLL.BLLManager;
import DAL.util.DalException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class NewCaseMOD {

    private BLLFacade bllFacade;
    private ObservableList<String> allCategories;
    private ObservableList<String> allSubcategories;

    public NewCaseMOD(){
        bllFacade = new BLLManager();
        allCategories = FXCollections.observableArrayList();
        allSubcategories = FXCollections.observableArrayList();
    }

    public ObservableList<String> getAllCategories() throws DalException {
        allCategories.addAll(bllFacade.getAllCategories());
        return allCategories;
    }

    public ObservableList<String> getAllSubcategories(String category) throws DalException{
        allSubcategories.clear();
        allSubcategories.addAll(bllFacade.getAllSubcategories(category));
        return allSubcategories;
    }

    public void createCase(Case newCase) throws DalException {
        bllFacade.createCase(newCase);
    }
}
