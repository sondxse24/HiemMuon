package hsf302.com.hiemmuon.repository;

import hsf302.com.hiemmuon.pojo.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Integer> {
}
