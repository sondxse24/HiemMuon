package hsf302.com.hiemmuon.service;

import hsf302.com.hiemmuon.dto.entityDto.CycleStepDTO;
import hsf302.com.hiemmuon.entity.Cycle;
import hsf302.com.hiemmuon.repository.CycleRepository;
import hsf302.com.hiemmuon.repository.CycleStepRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CycleStepService {

    @Autowired
    private CycleStepRepository cycleStepRepository;

    @Autowired
    private CycleRepository cycleRepository;
    @Autowired
    private CycleService cycleService;

    public List<CycleStepDTO> getCycleStepByMe(HttpServletRequest request) {
        Cycle myCycle = cycleService.getCycleByMe(request);
        return cycleStepRepository.findByCycle_CycleId(myCycle.getCycleId()).stream().map(
                cycleStep -> new CycleStepDTO(
                        cycleStep.getCycle().getService().getName(),
                        cycleStep.getDescription(),
                        cycleStep.getEventdate(),
                        cycleStep.getStatusCycleStep()
                )
        ).toList();
    }
}
