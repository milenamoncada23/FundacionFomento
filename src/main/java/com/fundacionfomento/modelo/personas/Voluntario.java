package com.fundacionfomento.modelo.personas;

import com.fundacionfomento.modelo.base.Persona;
import com.fundacionfomento.modelo.enums.TipoPersona;

public class Voluntario extends Persona {
    public Voluntario(Integer id){ super(id); }
    @Override public TipoPersona tipo() { return TipoPersona.VOLUNTARIO; }
}