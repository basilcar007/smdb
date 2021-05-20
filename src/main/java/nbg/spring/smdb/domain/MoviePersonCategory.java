package nbg.spring.smdb.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "MOVIE_PERSON_CATEGORY")
@SequenceGenerator(name = "idGeneratorMoviePersonCategory", sequenceName = "MOVIE_HAS_PERSON_IN_CATEGORY_SEQ",
        initialValue = 1, allocationSize = 1)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class MoviePersonCategory extends BaseEntity {
    @NotNull
    @ManyToOne(optional=false)
    @JoinColumn(name = "movie_id", nullable=false)
    Movie movie;

    @NotNull
    @ManyToOne(optional=false)
    @JoinColumn(name = "person_id", nullable=false)
    Person person;

    @NotNull
    @ManyToOne(optional=false)
    @JoinColumn(name = "category_id", nullable=false)
    Category category;

}

