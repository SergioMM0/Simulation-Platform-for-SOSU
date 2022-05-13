package DAL;

import BE.Case;
import BE.Group;
import DAL.Manager1.DAOCase;
import DAL.Manager1.DAOGroup;
import DAL.util.DalException;

public class testedmain {
    public static void main(String[] args) throws DalException {
        DAOCase daoCase = new DAOCase();

        for(Case d : daoCase.getAllCases(1)){

            System.out.println(d.getName());
        }
        DAOGroup d = new DAOGroup();

        for(Group c : d.getAllGroups(1)){
            System.out.println(c);
        }
    }
}
