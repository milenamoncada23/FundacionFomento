package com.fundacionfomento.modelo.proyecto;

import com.fundacionfomento.modelo.enums.EstadoProyecto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record Proyecto(Integer id, String nombre, String descripcion,
                       BigDecimal presupuesto, EstadoProyecto estado,
                       LocalDate inicio, LocalDate fin) {}