import entity.Account;
import entity.Group;
import util.HibernateUtil;

import java.util.Arrays;

public class Program {
    public static void main(String[] args) {
        try (var factory = HibernateUtil.buildSessionFactory()) {
            factory.inTransaction(session -> {
                var circle = new Circle();
            });

            factory.inSession(session -> {
                var hql = "FROM Group";
                var groups = session
                        .createSelectionQuery(hql, Group.class)
                        .getResultList();
                for (var group : groups) {
                    System.out.println("👉 group = " + group.getName());
                    var accounts = group.getAccounts();
                    for (var account : accounts) {
                        System.out.println("✨ account = " + account.getName());
                    }
                }
            });
        }
    }
}