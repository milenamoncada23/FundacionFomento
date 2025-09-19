package com.fundacionfomento.repositorio.base;

import java.util.List;
import java.util.Optional;

public interface GenericRepository<T, ID> {
    ID create(T t) throws Exception;
    Optional<T> findById(ID id) throws Exception;
    List<T> findAll() throws Exception;
    boolean update(T t) throws Exception;
    boolean delete(ID id) throws Exception;
}