package hsf302.com.hiemmuon.repository;

import hsf302.com.hiemmuon.dto.responseDto.MedicineScheduleDTO;
import hsf302.com.hiemmuon.entity.MedicineSchedule;
import hsf302.com.hiemmuon.enums.StatusMedicineSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MedicineScheduleRepository extends JpaRepository<MedicineSchedule, Integer> {
    List<MedicineSchedule> findByCycleStep_StepId(int stepId);

    List<MedicineSchedule> findByStatus(StatusMedicineSchedule status);

    List<MedicineSchedule> findAllByCycleStep_Cycle_CycleIdAndCycleStep_StepOrderAndEventDateBetween(
            int cycleId,
            int stepOrder,
            LocalDateTime startDate,
            LocalDateTime endDate
    );

    @Query("""
    SELECT new hsf302.com.hiemmuon.dto.responseDto.MedicineScheduleDTO(
        ms.medicationId,
        cs.stepOrder,
        ms.medicineName,
        ms.time,
        ms.startDate,
        ms.endDate,
        ms.eventDate,
        ms.status,
        ms.note,
        ms.isReminded
    )
    FROM MedicineSchedule ms
    JOIN ms.cycleStep cs
    JOIN cs.cycle c
    WHERE c.customer.customerId = :customerId
    ORDER BY cs.stepOrder ASC, ms.eventDate ASC
""")
    List<MedicineScheduleDTO> findAllByCustomerId(@Param("customerId") int customerId);

    List<MedicineSchedule> findByStatusAndEventDateBetween(
            StatusMedicineSchedule status,
            LocalDateTime startDate,
            LocalDateTime endDate
    );
}