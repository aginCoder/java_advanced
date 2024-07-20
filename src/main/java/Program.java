import entity.Circle;
import entity.Rectangle;
import entity.Shape;
import util.HibernateUtil;

public class Program {
    public static void main(String[] args) {
        try (var factory = HibernateUtil.buildSessionFactory()) {
            factory.inTransaction(session -> {
                var circle = new Circle();
                circle.setColor("red");
                circle.setRadius(5);
                session.persist(circle);

                var rectangle = new Rectangle();
                rectangle.setColor("blue");
                rectangle.setWidth(3);
                rectangle.setHeight(4);
                session.persist(rectangle);
            });

            factory.inSession(session -> {
                var hql = "FROM Circle";
                var shapes = session
                        .createSelectionQuery(hql, Circle.class)
                        .getResultList();
                for (var shape : shapes) {
                    System.out.println("👉 shape = " + shape.getColor());
                    System.out.println("✨ shape = " + shape.getRadius());
                }
            });
        }
    }
}