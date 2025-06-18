package hsf302.com.hiemmuon.controller;

import hsf302.com.hiemmuon.dto.ApiResponse;
import hsf302.com.hiemmuon.dto.entityDto.CycleStepDTO;
import hsf302.com.hiemmuon.service.CycleService;
import hsf302.com.hiemmuon.service.CycleStepService;
import hsf302.com.hiemmuon.service.DoctorService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController("/api/cycle")
public class CycleController {

    @Autowired
    private CycleService cycleService;

    @Autowired
    private DoctorService doctorService;
    @Autowired
    private CycleStepService cycleStepService;

    @GetMapping("/me")
    public ResponseEntity<ApiResponse<?>> getMyCycleStep(HttpServletRequest request) {
        List<CycleStepDTO> steps = cycleStepService.getCycleStepByMe(request);

        ApiResponse<List<CycleStepDTO>> response = new ApiResponse<>(
                200,
                "Get my cycle steps successfully",
                steps);
        return ResponseEntity.ok(response);
    }

}
