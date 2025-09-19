package com.fundacionfomento.repositorio.interfaces;

import com.fundacionfomento.modelo.donacion.Donacion;
import com.fundacionfomento.repositorio.base.GenericRepository;

import java.util.List;

public interface DonacionRepository extends GenericRepository<Donacion, Integer> {

    List<String[]> findListadoDonacionesConNombres() throws Exception;
}