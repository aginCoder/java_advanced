import dto.DepartmentDto;
import entity.Department;
import util.HibernateUtil;

public class Program {
    public static void main(String[] args) {
        try (var factory = HibernateUtil.buildSessionFactory()) {
            factory.inTransaction(session -> {
                var department = new Department();
                department.setName("Giám đốc");
                department.setType(Department.Type.PROJECT_MANAGER);
                session.persist(department);
            });

            factory.inTransaction(session -> {
                var department = new Department();
                department.setName("Bảo vệ");
                department.setType(Department.Type.TESTER);
                session.persist(department);
            });

            factory.inSession(session -> {
                var hql = "FROM Department";
                var departments = session
                        .createSelectionQuery(hql, Department.class)
                        .getResultList();
                for (var department : departments) {
                    System.out.println("1️⃣ department id = " + department.getId());
                    System.out.println("1️⃣ department name = " + department.getName());
                }
            });

            factory.inSession(session -> {
                // var hql = "FROM Department WHERE id = ?1";
                var hql = "FROM Department WHERE id = :id";
                var department = session
                        .createSelectionQuery(hql, Department.class)
                        // .setParameter(1, "VA000001")
                        .setParameter("id", "VA000001")
                        .uniqueResult();
                System.out.println("2️⃣ department id = " + department.getId());
                System.out.println("2️⃣ department name = " + department.getName());
            });

            factory.inSession(session -> {
                var hql = "SELECT COUNT(*) FROM Department";
                var count = session
                        .createSelectionQuery(hql, Long.class)
                        .uniqueResult();
                System.out.println("3️⃣ count = " + count);
            });

            factory.inSession(session -> {
                var hql = "SELECT new DepartmentDto(name) FROM Department";
                var departments = session
                        .createSelectionQuery(hql, DepartmentDto.class)
                        .getResultList();
                for (var department : departments) {
                    System.out.println("4️⃣ department name = " + department.getName());
                }
            });

            factory.inSession(session -> {
                var page = 2;
                var size = 1;
                var hql = "FROM Department";
                var departments = session
                        .createSelectionQuery(hql, Department.class)
                        .setMaxResults(size)
                        .setFirstResult((page - 1) * size)
                        .getResultList();
                for (var department : departments) {
                    System.out.println("1️⃣ department id = " + department.getId());
                    System.out.println("1️⃣ department name = " + department.getName());
                }
            });

            factory.inTransaction(session -> {
                var hql = "DELETE FROM Department WHERE id = :id";
                var result = session.createMutationQuery(hql)
                        .setParameter("id", "VA000001")
                        .executeUpdate();
                System.out.println("Xóa thành công: " + result);
            });
        }
    }
}