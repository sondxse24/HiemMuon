package hsf302.com.hiemmuon.repository;

import hsf302.com.hiemmuon.pojo.TreatmentService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TreatmentServiceRepository extends JpaRepository<TreatmentService, Integer> {
}
