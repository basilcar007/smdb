package nbg.spring.smdb.service;

import lombok.RequiredArgsConstructor;
import nbg.spring.smdb.domain.Movie;
import nbg.spring.smdb.domain.MoviePersonCategory;
import nbg.spring.smdb.repository.MoviePersonCategoryRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MoviePersonCategoryServiceImpl extends AbstractServiceImpl<MoviePersonCategory> implements MoviePersonCategoryService {
    private final MoviePersonCategoryRepository moviePersonCategoryRepository;

    @Override
    public JpaRepository<MoviePersonCategory, Long> getRepository() {
        return moviePersonCategoryRepository;
    }
}
