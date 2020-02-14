package ir.maktab.java32.project.spring.bankingsystem.repositories;

import ir.maktab.java32.project.spring.bankingsystem.models.Branch;
import ir.maktab.java32.project.spring.bankingsystem.utils.HibernateUtil;

public class BranchRepository extends CrudRepository<Branch, Long> {
    private static BranchRepository branchRepository;

    private BranchRepository() {
        setSession(HibernateUtil.getSession());
    }

    public static BranchRepository getInstance() {
        if (branchRepository == null) {
            branchRepository = new BranchRepository();
        }
        return branchRepository;
    }

    @Override
    protected Class<Branch> getEntityClass() {
        return Branch.class;
    }
}
