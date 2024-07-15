import entity.Account;
import entity.Department;
import entity.Group;
import entity.GroupAccount;
import util.HibernateUtil;

public class Program {
    public static void main(String[] args) {
        try (var factory = HibernateUtil.buiSessionFactory()){
            factory.inTransaction(session -> {
                        var group = new Group();
                        group.setName("Hibernate");
                        session.persist(group);

                        var account = new Account();
                        account.setName("Ân");
                        account.setEmail("an@gmail.com");
                        account.setGroup(group);
                        session.persist(account);
                    }

                factory.inSession(session -> {
                    var hql = "FROM Account";
                    var accounts = session
                            .createSelectionQuery(hql, Account.class)
                            .getResultList();
                    for (var account : accounts) {
                        System.out.println("👉 account = " + account.getName());
                        System.out.println("✨ group = " + account.getGroup().getName());
                    }
                });
            }
        }
    }