package gribanov.lab1.repository.interfaces;

import java.util.List;
import java.util.Optional;

public interface DaoInterface<T> {
    Optional<T> getById(Integer id);
    List<T> getAll();

    void save(T t);
    void update(T t);
    void delete(T t);
}
