package repository;

import java.util.List;
import java.util.Optional;

public interface BaseRepository<T> {
    void save(T t);

    void delete(Long id);

    Optional<T> findById(Long id);

    List<T> findAll();
}
