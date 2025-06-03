package hsf302.com.hiemmuon.pojo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "feedbacks")
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "feedback_id")
    private int feedbackId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @Column(name = "rating", nullable = false)
    private int rating;

    @Nationalized
    @Column(name = "comment", columnDefinition = "TEXT")
    private String comment;

    @Column(name = "create_at", nullable = false)
    private LocalDateTime createAt;

    public Feedback() {
    }

    public Feedback(Doctor doctor, Customer customer, int rating, String comment, LocalDateTime createAt) {
        this.doctor = doctor;
        this.customer = customer;
        this.rating = rating;
        this.comment = comment;
        this.createAt = createAt;
    }
}