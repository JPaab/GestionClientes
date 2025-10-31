package org.pablo.controllers;


// Importamos clases de diferentes packages para controlar la logica del progama desde el "Controller"

import org.pablo.entities.Cliente;
import org.pablo.exceptions.InformacionVaciaException;
import org.pablo.persistence.ClienteRepositoryJPA;
import org.pablo.utils.ColorANSI;

// Importamos utilidades propias de Java
import java.time.LocalDate;
import java.util.List;

public class ClienteController {


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

            // Usamos excepcion personalizada llamada "InformacionVaciaException" la cual permite seguir en el programa
            // Sin que este se rompa por la condicional "If"
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
        if (ciudad != null && !ciudad.isBlank()) {
            usuario.setCiudad(ciudad.trim());
            datoCambiado = true;
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
            usuario.setFechaNacimiento(LocalDate.parse(fechaNacimiento.trim()));
            datoCambiado = true;
        }

        if (!datoCambiado) {
            throw new InformacionVaciaException(ColorANSI.ROJO.pintar("\n Ningun campo puede estar vacio, Intente de nuevo."));
        }
        ClienteRepositoryJPA.actualizarCliente(usuario);
        System.out.println(ColorANSI.VERDE.pintar("\n Cliente actualizado."));
    }

    public static boolean eliminarCliente(Long idUsuario) {
        return ClienteRepositoryJPA.eliminarCliente(idUsuario);

    }
}