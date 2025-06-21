package hsf302.com.hiemmuon.dto.createDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateCycleDTO {
    private int customerId;
    private int serviceId;
    private LocalDate startDate;
    private LocalDate endDate;
    private String note;
}