package hsf302.com.hiemmuon.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "cycle_steps")
public class CycleStep {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "step_id")
    private int stepId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cycle_id", nullable = false)
    private Cycle cycle;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "treatment_step_id", nullable = false)
    private TreatmentStep treatmentStep; // 🔄 Thay vì service

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 20)
    private Status status;

    @Column(name = "description", columnDefinition = "NVARCHAR(MAX)")
    private String description;

    @Column(name = "eventdate", nullable = false)
    private LocalDate eventdate;

    public enum Status {
        ongoing, finished, stopped
    }

    public CycleStep() {
    }

    public CycleStep(Cycle cycle, TreatmentStep treatmentStep, Status status, String description, LocalDate eventdate) {
        this.cycle = cycle;
        this.treatmentStep = treatmentStep;
        this.status = status;
        this.description = description;
        this.eventdate = eventdate;
    }
}
