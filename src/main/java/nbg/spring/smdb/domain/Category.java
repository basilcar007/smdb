package nbg.spring.smdb.domain;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "CATEGORY")
@SequenceGenerator(name = "idGeneratorCategory", sequenceName = "CATEGORY_SEQ", initialValue = 1, allocationSize = 1)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Category extends BaseEntity {
    @NotNull
    @Column(length = 50, nullable = false, unique = true)
    private String description; // example values: {"director", "producer", "screenwriter", "actor"}

    @JsonBackReference("categories")
    @ManyToMany(mappedBy = "categories")
    private Set<Person> persons;

}

