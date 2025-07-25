package hsf302.com.hiemmuon.service;

import hsf302.com.hiemmuon.dto.testresult.CreateTestResultDTO;
import hsf302.com.hiemmuon.dto.testresult.TestResultViewDTO;
import hsf302.com.hiemmuon.dto.testresult.UpdateTestResultDTO;
import hsf302.com.hiemmuon.entity.*;
import hsf302.com.hiemmuon.repository.AppointmentRepository;
import hsf302.com.hiemmuon.repository.CustomerRepository;
import hsf302.com.hiemmuon.repository.CycleStepRepository;
import hsf302.com.hiemmuon.repository.TestResultRepository;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class TestResultService {

    @Autowired
    private TestResultRepository testResultRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CycleStepRepository cycleStepRepository;

    @Autowired
    private JwtService jwtService;

    public void createTestResult(CreateTestResultDTO dto) {
        Appointment appointment = appointmentRepository.findById(dto.getAppointmentId());
        if (appointment == null) {
            throw new RuntimeException("Không tìm thấy appointment với ID: " + dto.getAppointmentId());
        }

        CycleStep cycleStep = cycleStepRepository.findById(dto.getCycleStepId());

        TestResult result = new TestResult(
                0,
                dto.getName(),
                dto.getValue(),
                dto.getUnit(),
                dto.getReferenceRange(),
                dto.getTestDate(),
                dto.getNote(),
                cycleStep,
                appointment
        );

        testResultRepository.save(result);
    }


    public List<TestResultViewDTO> getResultsForCustomer(HttpServletRequest request) {
        final String authHeader = request.getHeader("Authorization");
        final String token = authHeader.substring(7);
        Claims claims = jwtService.extractAllClaims(token);

        Object customerIdObj = claims.get("userId");
        Integer customerId = Integer.parseInt(customerIdObj.toString());
        Customer customer = customerRepository.findById(customerId).orElse(null);
        if (customer == null) {
            System.out.println("Không tìm thấy thông tin khách hàng cho user: " + customerId);
            return Collections.emptyList(); // hoặc throw exception nếu cần
        }

        // Trả về danh sách kết quả xét nghiệm
        return testResultRepository.getAllByCustomerId(customer.getCustomerId());
    }

    public void updateTestResult(int id, UpdateTestResultDTO dto){
        TestResult result = testResultRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy kết quả xét nghiệm"));

        result.setName(dto.getName());
        result.setValue(dto.getValue());
        result.setUnit(dto.getUnit());
        result.setReferenceRange(dto.getReferenceRange());
        result.setTestDate(dto.getTestDate());
        result.setNote(dto.getNote());

        testResultRepository.save(result);
    }
}