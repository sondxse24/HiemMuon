package hsf302.com.hiemmuon.repository;

import hsf302.com.hiemmuon.pojo.Reminder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReminderRepository extends JpaRepository<Reminder, Integer> {
}
