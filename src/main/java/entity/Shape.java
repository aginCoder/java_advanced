package entity;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public class Shape {
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "color", length = 50, nullable = false)
    private String color;

}