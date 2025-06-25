package hsf302.com.hiemmuon.dto.responseDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CycleDTO {
    private int customerId;
    private int serviceId;
    private LocalDate startDate;
    private String note;
    private List<CycleStepDTO> cycleStep;
}
