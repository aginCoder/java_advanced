import entity.Department;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Root;
import util.HibernateUtil;

public class Program {
    public static void main(String[] args) {
        try (var factory = HibernateUtil.buildSessionFactory()) {
            factory.inTransaction(session -> {
                var sql = "INSERT INTO department(id, name, type, created_at, updated_at)" +
                        " VALUES ('VA000001', :name, :type, NOW(), NOW())";
                var result = session.createNativeMutationQuery(sql)
                        .setParameter("name", "Kỹ thuật")
                        .setParameter("type", 'D')
                        .executeUpdate();
                System.out.println("1️⃣ Thêm thành công: " + result);
            });

            factory.inSession(session -> {
                var sql = "SELECT * FROM department";
                var departments = session
                        .createNativeQuery(sql, Department.class)
                        .getResultList();
                for (var department : departments) {
                    System.out.println("2️⃣ department id = " + department.getId());
                    System.out.println("2️⃣ department name = " + department.getName());
                }
            });

            factory.inSession(session -> {
                CriteriaBuilder builder = session.getCriteriaBuilder();
                CriteriaQuery<Department> query = builder.createQuery(Department.class);
                Root<Department> root = query.from(Department.class);
                Expression<Boolean> expression = builder.equal(root.get("name"), "Kỹ thuật");
                query.select(root).where(expression);
                var departments = session
                        .createSelectionQuery(query)
                        .getResultList();
                for (var department : departments) {
                    System.out.println("3️⃣ department id = " + department.getId());
                    System.out.println("3️⃣ department name = " + department.getName());
                }
            });
        }
    }
}