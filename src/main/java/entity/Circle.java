package entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "circle")
@PrimaryKeyJoinColumn(name = "id")
public class Circle extends Shape {
    @Column(name = "radius", nullable = false)
    private int radius;
}