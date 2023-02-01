package trizu.havefun.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.Date;

@Data
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
@Entity
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private final long id;
    @NotBlank(message = "Description cannot be empty.")
    private String description;
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
