package nbg.spring.smdb.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "MOVIE")
@SequenceGenerator(name = "idGeneratorMovie", sequenceName = "MOVIE_SEQ", initialValue = 1, allocationSize = 1)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Movie extends BaseEntity {
    @NotNull
    @Column(length = 50, nullable = false, unique = false)
    private String title;

    @Column
    private Integer runtime;

    @Column
    private Integer year;
}
