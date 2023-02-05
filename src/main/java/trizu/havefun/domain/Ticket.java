package trizu.havefun.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Date;

@Data
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
@Entity
@Table(name = "tickets")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final long id;

    @NotBlank(message = "Description cannot be empty.")
    private String description;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User createdBy;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User owner;

    private Date cratedAt;
    private Status status = Status.New;

    @PrePersist
    public void createdAt() {
        this.cratedAt = new Date();
    }

    public enum Status{
        New, Opened, Answered, Resolved, Closed
    }

}
