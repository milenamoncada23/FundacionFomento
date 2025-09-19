package com.fundacionfomento;


import com.fundacionfomento.configuracion.DataBaseConfig;
import com.fundacionfomento.modelo.donacion.Donacion;
import com.fundacionfomento.modelo.enums.MetodoDonacion;
import com.fundacionfomento.repositorio.jdbc.JdbcDonacionRepository;
import com.fundacionfomento.repositorio.jdbc.JdbcPersonaRepository;
import com.fundacionfomento.repositorio.jdbc.JdbcProyectoRepository;
import com.fundacionfomento.servicio.implementacion.DonacionServiceImpl;
import com.fundacionfomento.utils.CsvWriter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
        DataBaseConfig db = new DataBaseConfig();

        JdbcPersonaRepository personaRepo  = new JdbcPersonaRepository(db);
        JdbcProyectoRepository proyectoRepo = new JdbcProyectoRepository(db);
        var donRepo      = new JdbcDonacionRepository(db);
        var donSvc       = new DonacionServiceImpl(donRepo, personaRepo, proyectoRepo);

        var in = new Scanner(System.in);
        System.out.println("=== Fundación Fomento CLI (sin login) ===");

        while (true) {
            System.out.println("\n1) Listar donaciones");
            System.out.println("2) Crear donación");
            System.out.println("3) Exportar donaciones a CSV");
            System.out.println("0) Salir");
            System.out.print("> ");
            String op = in.nextLine();

            try {
                switch (op) {
                    case "1" -> {
                        var filas = donRepo.findListadoDonacionesConNombres();
                        if (filas.isEmpty()) { System.out.println("(sin donaciones)"); break; }
                        for (var r : filas)
                            System.out.printf("#%s | %s | %s | %s | %s%n", r[0], r[1], r[2], r[3], r[4]);
                    }
                    case "2" -> {
                        System.out.print("persona_id (DONANTE): ");
                        int personaId = Integer.parseInt(in.nextLine());
                        System.out.print("proyecto_id (enter si no aplica): ");
                        String ptxt = in.nextLine();
                        Integer proyectoId = ptxt.isBlank() ? null : Integer.parseInt(ptxt);
                        System.out.print("monto: ");
                        BigDecimal monto = new BigDecimal(in.nextLine());
                        System.out.print("metodo [EFECTIVO|TRANSFERENCIA|TARJETA]: ");
                        MetodoDonacion metodo = MetodoDonacion.valueOf(in.nextLine().trim().toUpperCase());
                        System.out.print("fecha (YYYY-MM-DD): ");
                        LocalDate fecha = LocalDate.parse(in.nextLine());
                        System.out.print("comentario: ");
                        String c = in.nextLine();

                        var id = donSvc.crear(new Donacion(null, personaId, proyectoId, monto, metodo, fecha, c));
                        System.out.println("Donación creada id=" + id);
                    }
                    case "3" -> {
                        var path = CsvWriter.donaciones(donSvc.consultarTodos(), "donaciones.csv");
                        System.out.println("CSV generado: " + path);
                    }
                    case "0" -> { System.out.println("Adiós"); return; }
                    default -> System.out.println("Opción inválida");
                }
            } catch (Exception ex) {
                System.out.println("Error: " + ex.getMessage());
            }
        }
    }




}
