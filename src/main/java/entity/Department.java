package entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString        // in ra thông tin cho đơn giản hơn
@Entity
@Table(name = "department")
public class Department {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", length = 50, unique = true, nullable = false)
    private String name;

    @Column(name = "type", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Type type;

    @Column(name = "created_at", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime createAt;

    @Column(name = "updated_at", nullable = false)
    @UpdateTimestamp
    private LocalDateTime updateAt;

    @PrePersist
    public void prePersist(){
        System.out.println("Trước khi thêm vào database");
    }

    @PostPersist
    public void postPersist(){
        System.out.println("Sau khi thêm vào database");
    }

    public enum Type{
        DEVELOPER, TESTER, SCRUM_MASTER, PROJECT_MANAGER
    }
}
