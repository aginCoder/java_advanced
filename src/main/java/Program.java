import entity.Department;
import util.HibernateUtil;

public class Program {
    public static void main(String[] args) {
        try (var factory = HibernateUtil.buiSessionFactory()){
            factory.inTransaction(session -> {
                var department = new Department();
                department.setName("Giám đốc");
                session.persist(department);
            });

            factory.inSession(session -> {
                var department = new Department();
                department.setName("Bảo vệ");
                session.persist(department);
            });

            factory.inSession(session -> {
                // hibernate query language
                var hql = "FROM Department";
                var deppartments = session
                        .createSelectionQuery(hql, Department.class)
                        .getResultList();
                for (var department :deppartments){
                    System.out.println("department = " + department);
                }
            });

            factory.inSession(session -> {
                var department = session.get(Department.class, 1);
                System.out.println("department = " + department);
            });

            factory.inSession(session -> {
                var hql = "FROM Department WHERE name = :name";
                var department = session
                        .createSelectionQuery(hql, Department.class)
                        .setParameter("name", "Bảo vệ")
                        .uniqueResult();
                System.out.println("department = " + department);
            });

            factory.inTransaction(session -> {
                var department = session.get(Department.class, 2);
                department.setName("Kinh doanh");
                session.merge(department);
            });

            factory.inTransaction(session -> {
                var department = session.get(Department.class, 1);
                session.remove(department);
            });

        }
    }
}
