package nbg.spring.smdb.controller;

import lombok.RequiredArgsConstructor;
import nbg.spring.smdb.domain.Movie;
import nbg.spring.smdb.service.BaseService;
import nbg.spring.smdb.service.MovieService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/movie")
public class MovieController extends AbstractController<Movie> {
    private final MovieService movieService;

    @Override
    public BaseService<Movie, Long> getBaseService() {
        return movieService;
    }
}
