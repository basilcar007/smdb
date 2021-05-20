package nbg.spring.smdb.service;

import lombok.RequiredArgsConstructor;
import nbg.spring.smdb.domain.Movie;
import nbg.spring.smdb.domain.MoviePersonCategory;
import nbg.spring.smdb.repository.MoviePersonCategoryRepository;
import nbg.spring.smdb.transfer.GlobalDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MoviePersonCategoryServiceImpl extends AbstractServiceImpl<MoviePersonCategory> implements MoviePersonCategoryService {
    private final MoviePersonCategoryRepository moviePersonCategoryRepository;

    @Override
    public JpaRepository<MoviePersonCategory, Long> getRepository() {
        return moviePersonCategoryRepository;
    }

    @Override
    public List<GlobalDto> findAllRelations() {
        return moviePersonCategoryRepository.findAllRelations();
    }
}
