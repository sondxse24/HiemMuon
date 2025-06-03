package hsf302.com.hiemmuon.repository;

import hsf302.com.hiemmuon.pojo.CycleStep;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CycleStepRepository extends JpaRepository<CycleStep, Integer> {
}
