package com.fundacionfomento.modelo.personas;

import com.fundacionfomento.modelo.base.Persona;
import com.fundacionfomento.modelo.enums.TipoPersona;

public class Donante extends Persona {
    public Donante(Integer id){ super(id); }
    @Override public TipoPersona tipo() { return TipoPersona.DONANTE; }
}