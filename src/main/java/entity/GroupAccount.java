package entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "group_account")
public class GroupAccount {
    @EmbeddedId
    private PrimaryKey pk;

    @ManyToOne
    @JoinColumn(
            name = "group_id",
            referencedColumnName = "id",
            nullable = false
    )
    @MapsId("group_id")
    private Group group;

    @ManyToOne
    @JoinColumn(
            name = "account_id",
            referencedColumnName = "id",
            nullable = false
    )
    @MapsId("account_id")
    private Account account;

    @Column(name = "joined_at", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime joinedAt;

    @Getter
    @Setter
    @Embeddable
    public static class PrimaryKey implements Serializable {
        @Column(name = "group_id", nullable = false)
        private int groupId;

        @Column(name = "account_id", nullable = false)
        private int accountId;
    }
}