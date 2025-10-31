package org.pablo.controllers;


// Importamos clases de diferentes packages para controlar la logica del programa desde el "Controller"

import org.pablo.entities.Cliente;
import org.pablo.exceptions.InformacionVaciaException;
import org.pablo.persistence.ClienteRepositoryJPA;
import org.pablo.utils.ColorANSI;

// Importamos utilidades propias de Java

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

public class ClienteController {

    private static final DateTimeFormatter ES_UPDATE = DateTimeFormatter.ofPattern("dd-MM-uuuu");

    public static boolean registrarCliente(String nombre,
                                           String apellido,
                                           Cliente.Sexo sexo,
                                           String ciudad,
                                           String mail,
                                           String telefono,
                                           LocalDate fechaNacimiento) {
        if (nombre == null || nombre.isBlank() ||
                apellido == null || apellido.isBlank() ||
                sexo == null ||
                ciudad == null || ciudad.isBlank() ||
                mail == null || mail.isBlank() ||
                telefono == null || telefono.isBlank() ||
                fechaNacimiento == null) {
            // Usamos la excepcion personalizada llamada "InformacionVaciaException" la cual permite mantener el programa en pie.
            throw new InformacionVaciaException(ColorANSI.ROJO.pintar("\n Ningun campo puede estar vacio, Intente de nuevo."));
        }

        ClienteRepositoryJPA.registrarCliente(new Cliente(nombre, apellido, sexo, ciudad, mail, telefono, fechaNacimiento));
        return true;
    }

    public static List<Cliente> listarCliente() {
        return ClienteRepositoryJPA.listarCliente();
    }

    public static Cliente consultarCliente(Long idUsuario) {
        return ClienteRepositoryJPA.consultarCliente(idUsuario);

    }

    public static List<Cliente> filtrarCiudadCliente(String ciudad) {
        return ClienteRepositoryJPA.filtrarCiudadCliente(ciudad);
    }

    public static void actualizarCliente(Long idUsuario,
                                         String nombre,
                                         String apellido,
                                         String sexo,
                                         String ciudad,
                                         String mail,
                                         String telefono,
                                         String fechaNacimiento) {

        Cliente usuario = ClienteRepositoryJPA.consultarCliente(idUsuario);

        // Use un booleano para crear una variable llamada "DatoCambiado" la cual permite dejar campos vacios
        // al actualizar un usuario, ¿Para que?, para poder dar ENTER y dejar el nombre anterior a la modificación.

        boolean datoCambiado = false;
        if (nombre != null && !nombre.isBlank()) {
            usuario.setNombre(nombre.trim());
            datoCambiado = true;
        }
        if (apellido != null && !apellido.isBlank()) {
            usuario.setApellido(apellido.trim());
            datoCambiado = true;
        }
        if (sexo != null && !sexo.isBlank()) {
            usuario.setSexo(Cliente.Sexo.valueOf(sexo.trim()));
            datoCambiado = true;
        }
        if (sexo != null && !sexo.isBlank()) {
            try {
                usuario.setSexo(Cliente.Sexo.valueOf(sexo.trim().toUpperCase()));
                datoCambiado = true;
            } catch (IllegalArgumentException e) {
                throw new InformacionVaciaException(ColorANSI.ROJO.pintar("\n Sexo inválido. Usa M, F o X."));
            }
        }
        if (mail != null && !mail.isBlank()) {
            usuario.setMail(mail.trim());
            datoCambiado = true;
        }
        if (telefono != null && !telefono.isBlank()) {
            usuario.setTelefono(telefono.trim());
            datoCambiado = true;
        }
        if (fechaNacimiento != null && !fechaNacimiento.isBlank()) {
            try {
                usuario.setFechaNacimiento(LocalDate.parse(fechaNacimiento.trim(), ES_UPDATE));
                datoCambiado = true;
            } catch (DateTimeParseException e) {
                throw new InformacionVaciaException(ColorANSI.ROJO.pintar("\n Fecha inválida. Usa DD-MM-UUUU."));
            }
        }

        // Si no se ha hecho ningun cambio, sale un mensaje en ROJO "No se realizo ningun cambio"
        // Si almenos un dato ha sido cambiado, sale el mensaje en VERDE, "Cliente actualizado"

        if (!datoCambiado) {
            throw new InformacionVaciaException(ColorANSI.ROJO.pintar("\n No se realizo ningún cambio."));
        }
        ClienteRepositoryJPA.actualizarCliente(usuario);
        System.out.println(ColorANSI.VERDE.pintar("\n Cliente actualizado."));
    }

    public static boolean eliminarCliente(Long idUsuario) {
        return ClienteRepositoryJPA.eliminarCliente(idUsuario);

    }
}