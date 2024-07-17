package entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "shape")
@Inheritance(strategy = InheritanceType.TABLE_PER-CLASS)
public class Shape {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "color", length = 50, nullable = false)
    private String color;


}
