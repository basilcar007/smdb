package nbg.spring.smdb.service;

import nbg.spring.smdb.domain.Category;
import nbg.spring.smdb.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl extends AbstractServiceImpl<Category> implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public JpaRepository<Category, Long> getRepository() {
        return categoryRepository;
    }
}
