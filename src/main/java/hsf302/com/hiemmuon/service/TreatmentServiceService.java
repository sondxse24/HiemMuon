package hsf302.com.hiemmuon.service;

import hsf302.com.hiemmuon.entity.TreatmentService;
import hsf302.com.hiemmuon.repository.TreatmentServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TreatmentServiceService {

    @Autowired
    private TreatmentServiceRepository treatmentServiceRepository;

    public List<TreatmentService> findAll() {
        return treatmentServiceRepository.findAll();
    }
}