package hsf302.com.hiemmuon.dto.responseDto;

import hsf302.com.hiemmuon.enums.StatusCycle;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CycleDTO {
    private int cycleId;
    private int customerId;
    private int doctorId;
    private int serviceId;
    private LocalDate startDate;
    private LocalDate endDate;
    private StatusCycle status;
    private String note;
    private List<CycleStepDTO> cycleStep;
}
