package com.fundacionfomento.modelo.donacion;

import com.fundacionfomento.modelo.enums.MetodoDonacion;

import java.math.BigDecimal;
import java.time.LocalDate;

public record Donacion(Integer id, Integer personaId, Integer proyectoId,
                       BigDecimal monto, MetodoDonacion metodo,
                       LocalDate fecha, String comentario) {}