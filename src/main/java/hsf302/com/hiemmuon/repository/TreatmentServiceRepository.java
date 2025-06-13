package hsf302.com.hiemmuon.repository;

import hsf302.com.hiemmuon.entity.TreatmentService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TreatmentServiceRepository extends JpaRepository<TreatmentService, Integer> {
    List<TreatmentService> findAll();
}
