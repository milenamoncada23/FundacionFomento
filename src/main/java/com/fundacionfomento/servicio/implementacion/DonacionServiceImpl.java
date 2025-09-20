package com.fundacionfomento.servicio.implementacion;

import com.fundacionfomento.modelo.donacion.Donacion;
import com.fundacionfomento.modelo.enums.TipoPersona;
import com.fundacionfomento.repositorio.interfaces.DonacionRepository;
import com.fundacionfomento.repositorio.interfaces.PersonaRepository;
import com.fundacionfomento.repositorio.interfaces.ProyectoRepository;
import com.fundacionfomento.servicio.interfaces.DonacionService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


public class DonacionServiceImpl implements DonacionService {
    private final DonacionRepository repo;
    private final PersonaRepository personaRepo;
    private final ProyectoRepository proyectoRepo;

    public DonacionServiceImpl(DonacionRepository repo, PersonaRepository personaRepo, ProyectoRepository proyectoRepo) {
        this.repo = repo;
        this.personaRepo = personaRepo;
        this.proyectoRepo = proyectoRepo;
    }

    private void validar(Donacion d) throws Exception {
        if (d == null) throw new IllegalArgumentException("Donación requerida");
        if (d.personaId() == null || d.personaId() <= 0) throw new IllegalArgumentException("Donante inválido");
        if (!personaRepo.existsByIdAndTipo(d.personaId(), TipoPersona.DONANTE))
            throw new IllegalArgumentException("El persona_id no corresponde a un DONANTE");

        if (d.monto() == null || d.monto().compareTo(new BigDecimal("1.00")) < 0)
            throw new IllegalArgumentException("Monto mínimo 1.00");
        if (d.fecha() == null || d.fecha().isAfter(LocalDate.now()))
            throw new IllegalArgumentException("Fecha inválida");
        if (d.proyectoId()!=null && !proyectoRepo.existsById(d.proyectoId()))
            throw new IllegalArgumentException("Proyecto no existe");
    }

    @Override public Integer crear(Donacion d) throws Exception { validar(d); return repo.create(d); }
    @Override public Optional<Donacion> consultarPorId(Integer id) throws Exception { return repo.findById(id); }
    @Override public List<Donacion> consultarTodos() throws Exception { return repo.findAll(); }
    @Override public boolean actualizar(Donacion d) throws Exception {
        if (d.id()==null) throw new IllegalArgumentException("ID requerido");
        validar(d); return repo.update(d);
    }
    @Override public boolean borrar(Integer id) throws Exception { return repo.delete(id); }
}