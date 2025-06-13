package hsf302.com.hiemmuon.controller;

import hsf302.com.hiemmuon.dto.ApiResponse;
import hsf302.com.hiemmuon.entity.TreatmentService;
import hsf302.com.hiemmuon.service.TreatmentServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/treatment-services")
public class TreatmentServiceController {

    @Autowired
    private TreatmentServiceService treatmentServiceServicee;

    @GetMapping("/all")
    public List<TreatmentService> getTreatmentServices() {
        return treatmentServiceServicee.findAll();
    }
}
