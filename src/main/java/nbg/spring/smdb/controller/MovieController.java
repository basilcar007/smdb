package nbg.spring.smdb.controller;

import lombok.RequiredArgsConstructor;
import nbg.spring.smdb.domain.Movie;
import nbg.spring.smdb.domain.Person;
import nbg.spring.smdb.service.BaseService;
import nbg.spring.smdb.service.MoviePersonCategoryService;
import nbg.spring.smdb.service.MovieService;
import nbg.spring.smdb.transfer.ApiResponse;
import nbg.spring.smdb.transfer.GlobalDto;
import nbg.spring.smdb.transfer.KeyValue;
import nbg.spring.smdb.transfer.MovieDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/movie")
public class MovieController extends AbstractController<Movie> {
    private final MovieService movieService;
    private final MoviePersonCategoryService moviePersonCategoryService;

    @Override
    public BaseService<Movie, Long> getBaseService() {
        return movieService;
    }

    @GetMapping(headers = {"action=findMovieInfo", "movie-title"})
    public ResponseEntity<ApiResponse<List<MovieDTO>>> findAllRelations(@RequestHeader("movie-title") String movieTitle) {
        logger.info("Searching for movie {}.", movieTitle);
        final List<GlobalDto> results = moviePersonCategoryService.findAllRelations();
        final List<MovieDTO> rl = convertToMovieDTO(results, movieTitle);

        return ResponseEntity.ok(ApiResponse.<List<MovieDTO>>builder().data(rl).build());
    }

    private List<MovieDTO> convertToMovieDTO(List<GlobalDto> tuples, String movieTitle) {
        List<MovieDTO> ldto = new LinkedList<MovieDTO>();
        Map<Long, MovieDTO> hmap = new HashMap<Long, MovieDTO>();
        for (GlobalDto gdto: tuples) {
            Long gdtoMovieID = gdto.getMovieID();
            String gdtoMovieTitle = gdto.getMovieTitle();
            if (gdtoMovieTitle.matches(movieTitle)) {
                if (hmap.containsKey(gdtoMovieID)) { // update existing DTO
                    MovieDTO odto = hmap.get(gdtoMovieID);
                    String desc = gdto.getCategoryDescription();
                    if (odto.getData().containsKey(desc)) {
                        String value = odto.getData().get(gdto.getCategoryDescription());
                        value += ", " + gdto.getPersonFirstName() + " " + gdto.getPersonLastName();
                        Map<String, String> data = new HashMap<>();
                        data.putAll(odto.getData());
                        data.replace(desc, value);
                        odto.setData(data);
                    }
                    else {
                        Map<String, String> data = odto.getData();
                        data.put(
                                desc,
                                gdto.getPersonFirstName() + " " + gdto.getPersonLastName()
                        );
                        odto.setData(data);
                    }
                }
                else { // create a new DTO
                    MovieDTO ndto = MovieDTO.builder().id(gdtoMovieID).title(gdto.getMovieTitle()).
                            runtime(gdto.getMovieRuntime()).year(gdto.getMovieYear()).build();
                    ndto.setData(Collections.singletonMap(
                            gdto.getCategoryDescription(),
                            gdto.getPersonFirstName() + " " + gdto.getPersonLastName()));
                    hmap.put(gdtoMovieID, ndto);
                }
            }
        }

        Iterator it = hmap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            ldto.add((MovieDTO) pair.getValue());
            it.remove();
        }

        return ldto;
    }
}
