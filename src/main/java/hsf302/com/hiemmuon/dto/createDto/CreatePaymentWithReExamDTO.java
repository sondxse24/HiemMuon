package hsf302.com.hiemmuon.dto.createDto;

import hsf302.com.hiemmuon.enums.StatusPayment;
import hsf302.com.hiemmuon.enums.TypePayment;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreatePaymentWithReExamDTO {

    private int customerId;
    private int serviceId;
    private int appointmentId;
    private BigDecimal total;
    private LocalDateTime paidDate;
    private StatusPayment status;
    private TypePayment type;
    private LocalDateTime appointmentDate;
    private String note;
}