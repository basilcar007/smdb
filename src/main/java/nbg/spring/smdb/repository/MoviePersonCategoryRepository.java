package nbg.spring.smdb.repository;

import nbg.spring.smdb.domain.MoviePersonCategory;
import nbg.spring.smdb.transfer.GlobalDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MoviePersonCategoryRepository extends JpaRepository<MoviePersonCategory, Long> {

    @Query("select mpc from MoviePersonCategory mpc where mpc.movie = ?1")
    List<MoviePersonCategory> findForMovie(Long id);

    @Query("select mpc from MoviePersonCategory mpc where mpc.person = ?1")
    List<MoviePersonCategory> findForPerson(Long id);

    @Query(value="select mpc.CATEGORY_ID as CategoryID, c.DESCRIPTION as CategoryDescription, mpc.MOVIE_ID as MovieID, m.TITLE as MovieTitle, m.RUNTIME as MovieRuntime, m.YEAR as MovieYear, mpc.PERSON_ID as PersonID, p.EMAIL as PersonEmail, p.FIRSTNAME as PersonFirstName, p.LASTNAME as PersonLastName, p.DATE_OF_BIRTH as PersonDOB from MOVIE_PERSON_CATEGORY mpc inner join MOVIE m  on m.id = mpc.movie_id inner join PERSON p on p.id = mpc.person_id inner join CATEGORY c on c.id = mpc.category_id",nativeQuery = true)
    List<GlobalDto> findAllRelations();

}
