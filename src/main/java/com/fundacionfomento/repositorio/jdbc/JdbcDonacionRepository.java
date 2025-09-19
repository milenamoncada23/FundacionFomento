package com.fundacionfomento.repositorio.jdbc;

import com.fundacionfomento.configuracion.DataBaseConfig;
import com.fundacionfomento.modelo.donacion.Donacion;
import com.fundacionfomento.modelo.enums.MetodoDonacion;
import com.fundacionfomento.repositorio.interfaces.DonacionRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JdbcDonacionRepository implements DonacionRepository {

    private final DataBaseConfig db;

    public JdbcDonacionRepository(DataBaseConfig db) {
        this.db = db;
    }

    @Override public Integer create(Donacion donacion) throws Exception {
        String sql = """
      INSERT INTO donaciones (persona_id, proyecto_id, monto, metodo, fecha, comentario)
      VALUES (?, ?, ?, ?, ?, ?)
    """;
        try (Connection con = db.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, donacion.personaId());
            if (donacion.proyectoId()==null) ps.setNull(2, Types.INTEGER); else ps.setInt(2, donacion.proyectoId());
            ps.setBigDecimal(3, donacion.monto());
            ps.setString(4, donacion.metodo().name());
            ps.setDate(5, Date.valueOf(donacion.fecha()));
            ps.setString(6, donacion.comentario());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) { return rs.next() ? rs.getInt(1) : null; }
        }
    }

    @Override public Optional<Donacion> findById(Integer id) throws Exception {
        String sql = "SELECT id, persona_id, proyecto_id, monto, metodo, fecha, comentario FROM donaciones WHERE id=?";
        try (Connection con = db.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) return Optional.empty();
                return Optional.of(map(rs));
            }
        }
    }

    @Override public List<Donacion> findAll() throws Exception {
        String sql = "SELECT id, persona_id, proyecto_id, monto, metodo, fecha, comentario FROM donaciones";
        try (var con = db.getConnection(); var st = con.createStatement(); var rs = st.executeQuery(sql)) {
            var list = new ArrayList<Donacion>();
            while (rs.next()) list.add(map(rs));
            return list;
        }
    }

    @Override public boolean update(Donacion d) throws Exception {
        String sql = """
      UPDATE donaciones SET persona_id=?, proyecto_id=?, monto=?, metodo=?, fecha=?, comentario=? WHERE id=?
    """;
        try (var con = db.getConnection(); var ps = con.prepareStatement(sql)) {
            ps.setInt(1, d.personaId());
            if (d.proyectoId()==null) ps.setNull(2, Types.INTEGER); else ps.setInt(2, d.proyectoId());
            ps.setBigDecimal(3, d.monto());
            ps.setString(4, d.metodo().name());
            ps.setDate(5, Date.valueOf(d.fecha()));
            ps.setString(6, d.comentario());
            ps.setInt(7, d.id());
            return ps.executeUpdate() > 0;
        }
    }

    @Override public boolean delete(Integer id) throws Exception {
        try (var con = db.getConnection(); var ps = con.prepareStatement("DELETE FROM donaciones WHERE id=?")) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }

    @Override public List<String[]> findListadoDonacionesConNombres() throws Exception {
        String sql = """
      SELECT d.id, d.fecha, d.monto,
             p.nombres, p.apellidos,
             pr.nombre AS proyecto
      FROM donaciones d
      JOIN personas p ON p.id = d.persona_id
      LEFT JOIN proyectos pr ON pr.id = d.proyecto_id
      ORDER BY d.fecha DESC
    """;
        try (var con = db.getConnection(); var st = con.createStatement(); var rs = st.executeQuery(sql)) {
            var out = new ArrayList<String[]>();
            while (rs.next()) {
                out.add(new String[]{
                        String.valueOf(rs.getInt("id")),
                        rs.getDate("fecha").toString(),
                        rs.getBigDecimal("monto").toPlainString(),
                        rs.getString("nombres")+" "+rs.getString("apellidos"),
                        rs.getString("proyecto")
                });
            }
            return out;
        }
    }

    private Donacion map(ResultSet rs) throws Exception {
        return new Donacion(
                rs.getInt("id"),
                rs.getInt("persona_id"),
                (Integer)rs.getObject("proyecto_id"),
                rs.getBigDecimal("monto"),
                MetodoDonacion.valueOf(rs.getString("metodo")),
                rs.getDate("fecha").toLocalDate(),
                rs.getString("comentario")
        );
    }
}
