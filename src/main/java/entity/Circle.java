package entity;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "circle")
@AttributeOverrides({

})
public class Circle extends Shape{
    @Column(name = "radius")
    private int radius;
}
