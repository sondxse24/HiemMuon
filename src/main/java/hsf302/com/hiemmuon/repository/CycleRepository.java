package hsf302.com.hiemmuon.repository;

import hsf302.com.hiemmuon.pojo.Cycle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CycleRepository extends JpaRepository<Cycle, Integer> {
}
