package nbg.spring.smdb.bootstrap;

import lombok.RequiredArgsConstructor;
import nbg.spring.smdb.base.AbstractLogEntity;
import nbg.spring.smdb.domain.Category;
import nbg.spring.smdb.domain.Movie;
import nbg.spring.smdb.domain.MoviePersonCategory;
import nbg.spring.smdb.domain.Person;
import nbg.spring.smdb.service.CategoryService;
import nbg.spring.smdb.service.MoviePersonCategoryService;
import nbg.spring.smdb.service.MovieService;
import nbg.spring.smdb.service.PersonService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Component
@Profile("dev")
@RequiredArgsConstructor
public class SampleContentRunner extends AbstractLogEntity implements CommandLineRunner {
    private final CategoryService categoryService;
    private final MovieService movieService;
    private final PersonService personService;
    private final MoviePersonCategoryService moviePersonCategoryService;

    @Override
    public void run(String... args) {
        //@formatter:off
        try {
            // first create some sane categories
            Category director  = Category.builder().description("director").build();
            Category screenwriter = Category.builder().description("screenwriter").build();
            Category producer = Category.builder().description("producer").build();
            Category actor = Category.builder().description("actor").build();

            // then create some persons
            Person QuentinTarantino = Person.builder().email("tarantino@gmail.com").firstName("Quentin").
                    lastName("Tarantino").birthDate(LocalDate.parse("1963-03-27")).
                    categories(Stream.of(director, screenwriter, producer, actor).
                    collect(Collectors.toCollection(HashSet::new))).build();
            Person RobertRodriguez = Person.builder().email("rodriguez@hotmail.com").firstName("Robert").
                    lastName("Rodriguez").birthDate(LocalDate.parse("1968-06-20")).
                    categories(Stream.of(director, screenwriter, producer).
                            collect(Collectors.toCollection(HashSet::new))).build();
            Person FrankMiller = Person.builder().email("fmiller@hotmail.com").firstName("Frank").
                    lastName("Miller").birthDate(LocalDate.parse("1957-01-27")).
                    categories(Stream.of(screenwriter, producer, actor).
                            collect(Collectors.toCollection(HashSet::new))).build();
            Person JulieTaymor = Person.builder().email("jtaymor@yahoo.com").firstName("Julie").
                    lastName("Taymor").birthDate(LocalDate.parse("1952-12-15")).
                    categories(Stream.of(director, producer).
                            collect(Collectors.toCollection(HashSet::new))).build();
            Person ClancySigal = Person.builder().email("csigal@gmail.com").firstName("Clancy").
                    lastName("Sigal").birthDate(LocalDate.parse("1926-09-06")).
                    categories(Stream.of(screenwriter).
                            collect(Collectors.toCollection(HashSet::new))).build();
            Person SalmaHayek = Person.builder().email("salma@hotmail.com").firstName("Salma").
                    lastName("Hayek").birthDate(LocalDate.parse("1966-09-02")).
                    categories(Stream.of(actor, producer).
                            collect(Collectors.toCollection(HashSet::new))).build();
            Person JessicaAlba = Person.builder().email("jalba@yahoo.com").firstName("Jessica").
                    lastName("Alba").birthDate(LocalDate.parse("1981-04-28")).
                    categories(Stream.of(actor).
                            collect(Collectors.toCollection(HashSet::new))).build();
            Person BruceWillis = Person.builder().email("bwillis@hotmail.com").firstName("Bruce").
                    lastName("Willis").birthDate(LocalDate.parse("1955-03-19")).
                    categories(Stream.of(actor, producer).
                            collect(Collectors.toCollection(HashSet::new))).build();

            // some movies
            Movie FromDuskTillDawn = Movie.builder().title("From Dusk Till Dawn").runtime(108).year(1996).build();
            Movie SinCity = Movie.builder().title("Sin City").runtime(124).year(2005).build();
            Movie FrankMillersSinCity = Movie.builder().title("Frank Miller's Sin City: A Dame to Kill For").
                    runtime(102).year(2014).build();
            Movie Frida = Movie.builder().title("Frida").runtime(123).year(2002).build();

            // and finally add the relations among them
            List<MoviePersonCategory> relations = List.of(
                    // FromDuskTillDawn
                    MoviePersonCategory.builder().movie(FromDuskTillDawn).
                            category(director).person(RobertRodriguez).build(),
                    MoviePersonCategory.builder().movie(FromDuskTillDawn).
                            category(screenwriter).person(QuentinTarantino).build(),
                    MoviePersonCategory.builder().movie(FromDuskTillDawn).
                            category(producer).person(RobertRodriguez).build(),
                    MoviePersonCategory.builder().movie(FromDuskTillDawn).
                            category(producer).person(QuentinTarantino).build(),
                    MoviePersonCategory.builder().movie(FromDuskTillDawn).
                            category(actor).person(SalmaHayek).build(),
                    MoviePersonCategory.builder().movie(FromDuskTillDawn).
                            category(actor).person(QuentinTarantino).build(),

                    // SinCity
                    MoviePersonCategory.builder().movie(SinCity).
                            category(director).person(RobertRodriguez).build(),
                    MoviePersonCategory.builder().movie(SinCity).
                            category(director).person(QuentinTarantino).build(),
                    MoviePersonCategory.builder().movie(SinCity).
                            category(director).person(FrankMiller).build(),
                    MoviePersonCategory.builder().movie(SinCity).
                            category(screenwriter).person(RobertRodriguez).build(),
                    MoviePersonCategory.builder().movie(SinCity).
                            category(screenwriter).person(FrankMiller).build(),
                    MoviePersonCategory.builder().movie(SinCity).
                            category(actor).person(JessicaAlba).build(),
                    MoviePersonCategory.builder().movie(SinCity).
                            category(actor).person(BruceWillis).build(),

                    // FrankMillersSinCity
                    MoviePersonCategory.builder().movie(FrankMillersSinCity).
                            category(director).person(RobertRodriguez).build(),
                    MoviePersonCategory.builder().movie(FrankMillersSinCity).
                            category(director).person(FrankMiller).build(),
                    MoviePersonCategory.builder().movie(FrankMillersSinCity).
                            category(screenwriter).person(FrankMiller).build(),
                    MoviePersonCategory.builder().movie(FrankMillersSinCity).
                            category(producer).person(RobertRodriguez).build(),
                    MoviePersonCategory.builder().movie(FrankMillersSinCity).
                            category(actor).person(JessicaAlba).build(),
                    MoviePersonCategory.builder().movie(FrankMillersSinCity).
                            category(actor).person(BruceWillis).build(),

                    // Frida
                    MoviePersonCategory.builder().movie(Frida).
                            category(director).person(JulieTaymor).build(),
                    MoviePersonCategory.builder().movie(Frida).
                            category(screenwriter).person(ClancySigal).build(),
                    MoviePersonCategory.builder().movie(Frida).
                            category(producer).person(SalmaHayek).build(),
                    MoviePersonCategory.builder().movie(Frida).
                            category(actor).person(SalmaHayek).build()
                    );

            logger.info("Saving categories: {} {} {} {}",
                    director.getDescription(), screenwriter.getDescription(),
                    producer.getDescription(),  actor.getDescription());
            categoryService.save(director);
            categoryService.save(screenwriter);
            categoryService.save(producer);
            categoryService.save(actor);

            logger.info("Saving persons");
            personService.save(QuentinTarantino);
            personService.save(RobertRodriguez);
            personService.save(FrankMiller);
            personService.save(JulieTaymor);
            personService.save(ClancySigal);
            personService.save(SalmaHayek);
            personService.save(JessicaAlba);
            personService.save(BruceWillis);

            logger.info("Saving movies");
            movieService.save(FromDuskTillDawn);
            movieService.save(SinCity);
            movieService.save(FrankMillersSinCity);
            movieService.save(Frida);

            logger.info("Saving relations");
            moviePersonCategoryService.saveAll(relations);

        } catch (DataIntegrityViolationException dive) {
            logger.warn("Unable to persist sample data as they probably already exist!");
        } catch (DataAccessException dae) {
            logger.error("Error occurred while interacting with underlying database, see the details", dae);
        }
        //@formatter:on

    }
}
