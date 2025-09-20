package com.fundacionfomento.servicio.interfaces;


import java.util.List;
import java.util.Optional;

public interface GenericService<T, ID> {
    ID crear(T t) throws Exception;
    Optional<T> consultarPorId(ID id) throws Exception;
    List<T> consultarTodos() throws Exception;
    boolean actualizar(T t) throws Exception;
    boolean borrar(ID id) throws Exception;
}