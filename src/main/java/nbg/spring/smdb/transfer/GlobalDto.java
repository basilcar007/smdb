package nbg.spring.smdb.transfer;

import java.time.LocalDate;

public interface GlobalDto {
    Long getCategoryID();
    String getCategoryDescription();

    Long getMovieID();
    String getMovieTitle();
    Long getMovieRuntime();
    Long getMovieYear();

    Long getPersonID();
    String getPersonEmail();
    String getPersonFirstName();
    String getPersonLastName();
    LocalDate getPersonDOB();

}
