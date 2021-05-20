package nbg.spring.smdb.repository;

import nbg.spring.smdb.domain.MoviePersonCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MoviePersonCategoryRepository extends JpaRepository<MoviePersonCategory, Long> {
}
