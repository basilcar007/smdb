package nbg.spring.smdb.service;

import java.util.List;

public interface BaseService<T, ID> {
    T save(T T);

    List<T> saveAll(List<T> categories);

    void update(T T);

    void delete(T T);

    void deleteById(ID id);

    boolean exists(T T);

    T findById(ID id);

    List<T> findAll();
}
