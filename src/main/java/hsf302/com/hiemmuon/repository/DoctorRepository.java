package hsf302.com.hiemmuon.repository;

import hsf302.com.hiemmuon.pojo.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Integer> {
}
