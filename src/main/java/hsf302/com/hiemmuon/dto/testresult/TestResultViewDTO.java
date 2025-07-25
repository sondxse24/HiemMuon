package hsf302.com.hiemmuon.dto.testresult;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class TestResultViewDTO {
    private int resultId;
    private String name;
    private Float value;
    private String unit;
    private String referenceRange;
    private LocalDate testDate;
    private String note;
    private int appointmentId;
    private int cycleStepId;
    private int stepOrder;

    public TestResultViewDTO(int resultId, String name, Float value, String unit, String referenceRange, LocalDate testDate, String note, int appointmentId, int cycleStepId, int stepOrder) {
        this.resultId = resultId;
        this.name = name;
        this.value = value;
        this.unit = unit;
        this.referenceRange = referenceRange;
        this.testDate = testDate;
        this.note = note;
        this.appointmentId = appointmentId;
        this.cycleStepId = cycleStepId;
        this.stepOrder = stepOrder;
    }

    public TestResultViewDTO(int resultId, String name, Float value, String unit, String referenceRange, LocalDate testDate, String note) {
        this.resultId = resultId;
        this.name = name;
        this.value = value;
        this.unit = unit;
        this.referenceRange = referenceRange;
        this.testDate = testDate;
        this.note = note;
    }
}


