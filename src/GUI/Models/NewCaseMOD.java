package GUI.Models;

import BE.Case;
import BE.Category;
import BE.SubCategory;
import BLL.BLLFacade;
import BLL.BLLManager;
import DAL.util.DalException;
import GUI.Alerts.SoftAlert;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class NewCaseMOD {

    private BLLFacade bllFacade;
    private ObservableList<Category> allCategories;
    private ObservableList<SubCategory> allSubcategories;

    public NewCaseMOD(){
        bllFacade = new BLLManager();
        allCategories = FXCollections.observableArrayList();
        allSubcategories = FXCollections.observableArrayList();
    }

    public ObservableList<Category> getAllCategories() throws DalException {
        allCategories.addAll(bllFacade.getAllCategories());
        return allCategories;
    }

    public ObservableList<SubCategory> getAllSubcategories(Category category) throws DalException{
        allSubcategories.clear();
        allSubcategories.addAll(bllFacade.getAllSubcategories(category));
        for(SubCategory subCategory : allSubcategories){
            System.out.println(subCategory);
        }
        return allSubcategories;
    }

    public void createCase(Case newCase, Category category, SubCategory subCategory) throws DalException {
        bllFacade.createCase(newCase,category,subCategory);
    }

    public Category getChosenCategory(String catName) {
        for(Category cat : allCategories){
            if(cat.getName().equals(catName)){
                return cat;
            }
        }
        return null;
    }

    public SubCategory getChosenSubCategory(String subCatName) {
        for(SubCategory subCategory : allSubcategories){
            if(subCategory.getName().equals(subCatName)){
                return subCategory;
            }
            else new SoftAlert("hmmmmm");
        }
        return null;
    }
}
