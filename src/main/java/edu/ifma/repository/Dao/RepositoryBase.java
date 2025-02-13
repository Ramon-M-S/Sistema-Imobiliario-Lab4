package edu.ifma.repository.Dao;

import java.util.List;
import java.util.Optional;

public class RepositoryBase<T> implements GenericRepository<T> {
    private final DaoGeneric<T> Daogeneric;

    public RepositoryBase(DaoGeneric<T> daogeneric) {
        Daogeneric = daogeneric;
    }

    @Override
    public void save(T entity) {
        Daogeneric.save(entity);
    }

    @Override
    public void update(T entity) {
        Daogeneric.update(entity);
    }

    @Override
    public void deleteById(int id) {
        Daogeneric.deletar(id);
    }

    @Override
    public List findAll() {
        return Daogeneric.findAll();
    }

    @Override
    public Optional<T> findById(int id) {
        T entity = Daogeneric.findById(id);
        return Optional.ofNullable(entity);
    }
}
