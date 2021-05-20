package nbg.spring.smdb.controller;

import lombok.RequiredArgsConstructor;
import nbg.spring.smdb.domain.Category;
import nbg.spring.smdb.service.BaseService;
import nbg.spring.smdb.service.CategoryService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/category")
public class CategoryController extends AbstractController<Category> {
    private final CategoryService categoryService;

    @Override
    public BaseService<Category, Long> getBaseService() {
        return categoryService;
    }
}
