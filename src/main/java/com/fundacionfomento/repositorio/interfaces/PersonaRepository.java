package com.fundacionfomento.repositorio.interfaces;

import com.fundacionfomento.modelo.enums.TipoPersona;

public interface PersonaRepository {
    boolean existsByIdAndTipo(int id, TipoPersona tipo) throws Exception;
}