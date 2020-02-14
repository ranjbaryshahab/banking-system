package ir.maktab.java32.project.spring.bankingsystem.repositories;

import ir.maktab.java32.project.spring.bankingsystem.models.Boss;
import ir.maktab.java32.project.spring.bankingsystem.utils.HibernateUtil;

public class BossRepository extends CrudRepository<Boss, Long> {
    private static BossRepository bossRepository;

    private BossRepository() {
        setSession(HibernateUtil.getSession());
    }

    public static BossRepository getInstance() {
        if (bossRepository == null) {
            bossRepository = new BossRepository();
        }
        return bossRepository;
    }

    @Override
    protected Class<Boss> getEntityClass() {
        return Boss.class;
    }
}
