package hsf302.com.hiemmuon.pojo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "doctors")
public class Doctor {

    @Id
    @Column(name = "doctor_id")
    private int doctorId;

    @OneToOne
    @MapsId
    @JoinColumn(name = "doctor_id")
    private User user;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "experience")
    private int experience;

    @Column(name = "rating_avg")
    private Float ratingAvg;

    @Column(name = "is_active")
    private Boolean isActive;

    public Doctor() {
    }

    public Doctor(User user, String description, int experience, Float ratingAvg, Boolean isActive) {
        this.user = user;
        this.description = description;
        this.experience = experience;
        this.ratingAvg = ratingAvg;
        this.isActive = isActive;
    }
}
