package edu.ifma.repository.Dao;

import java.util.List;
import java.util.Optional;

public interface GenericRepository<T> {
    void save(T entity);
    void update(T entity);
    void deleteById(int id); // delete based on id
    List<T> findAll();
    Optional<T> findById(int id); // search based on id

}
