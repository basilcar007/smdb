package nbg.spring.smdb.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Set;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "PERSON")
@SequenceGenerator(name = "idGeneratorPerson", sequenceName = "PERSON_SEQ", initialValue = 1, allocationSize = 1)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Person extends BaseEntity {
    @NotNull
    @Column(length = 50, nullable = false, unique = true)
    private String email;

    @NotNull
    @Column(length = 20, nullable = false)
    private String firstName;

    @NotNull
    @Column(length = 30, nullable = false)
    private String lastName;

    @Column(name = "DATE_OF_BIRTH", columnDefinition = "DATE")
    private LocalDate birthDate;

    @JsonManagedReference("categories")
    @ToString.Exclude
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "Person_Category",
            joinColumns = @JoinColumn(name = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories;

}
