package org.pablo.controllers;

import org.pablo.entities.Cliente;
import org.pablo.persistance.ClienteRepositoryJPA;
import org.pablo.utils.ColorANSI;

import java.util.List;

public class ClienteController {


    public static boolean registrarCliente(String nombre,
                                           String apellido,
                                           String sexo,
                                           String ciudad,
                                           String mail,
                                           String telefono,
                                           String fechaNacimiento) {
        if (nombre == null || nombre.isBlank() ||
                apellido == null || apellido.isBlank() ||
                sexo == null || sexo.isBlank() ||
                ciudad == null || ciudad.isBlank() ||
                mail == null || mail.isBlank() ||
                telefono == null || telefono.isBlank() ||
                fechaNacimiento == null || fechaNacimiento.isBlank()) {
            System.out.println(ColorANSI.ROJO.pintar("\n Ningun campo puede estar vacio, Intente de nuevo."));
            return false;
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
        if (usuario == null) {
            System.out.println("Cliente no encontrado por ID");
            return;
        }

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
            usuario.setSexo(sexo.trim());
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
            usuario.setFechaNacimiento(fechaNacimiento.trim());
            datoCambiado = true;
        }

        if (datoCambiado) {
            ClienteRepositoryJPA.actualizarCliente(usuario);
            System.out.println(ColorANSI.VERDE.pintar("\n Cliente actualizado."));
        } else {
            System.out.println(ColorANSI.ROJO.pintar("\n Ningun campo puede estar vacio, Intente de nuevo."));
        }
    }

    public static boolean eliminarCliente(Long idUsuario) {
        return ClienteRepositoryJPA.eliminarCliente(idUsuario);

    }
}