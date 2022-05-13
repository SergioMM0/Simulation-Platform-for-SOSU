package DAL;

import BE.Case;
import DAL.Manager1.DAOCase;
import DAL.util.DalException;

public class testedmain {
    public static void main(String[] args) throws DalException {
        DAOCase daoCase = new DAOCase();

        for(Case d : daoCase.getAllCases(1)){

            System.out.println(d.getName());
        }
    }
}
