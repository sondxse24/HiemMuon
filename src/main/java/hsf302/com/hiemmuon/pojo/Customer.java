package hsf302.com.hiemmuon.pojo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "customers")
public class Customer {

    @Id
    @Column(name = "customer_id")
    private int customerId;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @MapsId
    @JoinColumn(name = "customer_id")
    private User user;

    @Column(name = "medical_history", columnDefinition = "TEXT")
    private String medicalHistory;

    @Column(name = "is_active")
    private Boolean isActive;

    public Customer() {
    }

    public Customer(User user, String medicalHistory, Boolean isActive) {
        this.user = user;
        this.medicalHistory = medicalHistory;
        this.isActive = isActive;
    }
}