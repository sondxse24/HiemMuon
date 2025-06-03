package hsf302.com.hiemmuon.repository;

import hsf302.com.hiemmuon.pojo.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicineRepository extends JpaRepository<Medicine, Integer> {
}
