package com.fundacionfomento.modelo.atencion;

import com.fundacionfomento.modelo.enums.TipoServicio;

import java.math.BigDecimal;
import java.time.LocalDate;

public record Atencion(Integer id, Integer beneficiarioId, Integer proyectoId,
                       TipoServicio tipo, String descripcion,
                       BigDecimal costo, LocalDate fecha) {}
