package nbg.spring.smdb.service;

import nbg.spring.smdb.domain.MoviePersonCategory;
import nbg.spring.smdb.transfer.GlobalDto;

import java.util.List;

public interface MoviePersonCategoryService extends BaseService<MoviePersonCategory, Long> {

    List<GlobalDto> findAllRelations();

}
