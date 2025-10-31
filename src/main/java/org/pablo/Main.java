package org.pablo;

import org.pablo.controllers.ClienteController;
import org.pablo.entities.Cliente;
import org.pablo.utils.ColorANSI;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner sc = new Scanner(System.in);
    final static String RESET = "\u001B[0m";
    final static String ROJO = "\u001B[31m";
    final static String VERDE = "\u001B[32m";
    final static String AMARILLO = "\u001B[33m";
    final static String AZUL = "\u001B[34m";

    public static void main(String[] args) {
        System.out.println("Menú de Clientes (Hibernate + JPA)");

        while (true) {
            mostrarMenu();
            int opcion = sc.nextInt();
            sc.nextLine(); // Limpiamos el Buffer ( Si el proximo sc.next es "texto" nos daria problemas. )

            switch (opcion) {
                case 1:
                    registrarCliente();
                    break;
                case 2:
                    listarCliente();
                    break;
                case 3:
                    consultarCliente();
                    break;
                case 4:
                    filtrarCiudadCliente();
                    break;
                case 5:
                    actualizarCliente();
                    break;
                case 6:
                    eliminarCliente();
                    break;
                case 0:
                    System.out.println(ROJO + "Saliendo..." + RESET);
                    return;

                default:
                    System.out.println(ROJO + "Opción no valida." + RESET);
            }
        }
    }

    private static void mostrarMenu() {
        System.out.println(ColorANSI.AMARILLO.pintar("\n ---------------------------------"));
        System.out.println(ColorANSI.AMARILLO.pintar(" MENÚ DE CLIENTE\n"));
        System.out.println(AZUL + "1" + RESET + " - Registrar usuario.");
        System.out.println(AZUL + "2" + RESET + " - Listar usuarios.");
        System.out.println(AZUL + "3" + RESET + " - Consultar usuario por ID.");
        System.out.println(AZUL + "4" + RESET + " - Filtrar por Ciudad.");
        System.out.println(AZUL + "5" + RESET + " - Actualizar usuario.");
        System.out.println(AZUL + "6" + RESET + " - Eliminar usuario.");
        System.out.println(AZUL + "0" + RESET + " - Salir.");
        System.out.println(ColorANSI.AZUL.pintar("\n Elige una de las opciones: "));
    }

    private static void registrarCliente() {
        System.out.println(ColorANSI.AMARILLO.pintar("Nombre: "));
        String nombre = sc.nextLine().trim();
        System.out.println(ColorANSI.AMARILLO.pintar("Apellido: "));
        String apellido = sc.nextLine().trim();
        System.out.println(ColorANSI.AMARILLO.pintar("Sexo (M/F): "));
        String sexo = sc.nextLine().trim();
        System.out.println(ColorANSI.AMARILLO.pintar("Ciudad: "));
        String ciudad = sc.nextLine().trim();
        System.out.println(ColorANSI.AMARILLO.pintar("Correo electronico: "));
        String mail = sc.nextLine().trim();
        System.out.println(ColorANSI.AMARILLO.pintar("Telefono: "));
        String telefono = sc.nextLine().trim();
        System.out.println(ColorANSI.AMARILLO.pintar("Fecha de nacimiento (DD-MM-YYYY): "));
        String fechaNacimiento = sc.nextLine().trim();

        boolean register = ClienteController.registrarCliente(nombre, apellido, sexo, ciudad, mail, telefono, fechaNacimiento);
        if (register) {
            System.out.println(ColorANSI.VERDE.pintar("\n Usuario registrado correctamente."));
        }
    }

    private static void listarCliente() {
        List<Cliente> usuario = ClienteController.listarCliente();
        if (usuario.isEmpty()) {
            System.out.println(ColorANSI.ROJO.pintar("\n No hay usuarios registrados."));
        } else {
            for (Cliente cliente : usuario) {
                System.out.println("--------------------" + cliente);
            }
        }
    }

    private static void consultarCliente() {
        System.out.println(ColorANSI.AZUL.pintar("\n ID del usuario a consultar:"));
        Long idUsuario = Long.parseLong(sc.nextLine().trim());
        Cliente usuario = ClienteController.consultarCliente(idUsuario);

        if (usuario == null) {
            System.out.println(ColorANSI.ROJO.pintar("\n Cliente no encontrado."));
        } else {
            System.out.println(usuario);
        }

    }

    private static void filtrarCiudadCliente() {
        System.out.println(ColorANSI.AZUL.pintar("\n Ciudad a filtrar:"));
        String ciudad = sc.nextLine().trim();
        List<Cliente> usuario = ClienteController.filtrarCiudadCliente(ciudad);

        if (usuario.isEmpty()) {
            System.out.println(ColorANSI.ROJO.pintar("\n No se ha encontrado clientes en: " + ciudad.toUpperCase()));
        } else {
            for (Cliente cliente : usuario) {
                System.out.println(cliente);
            }
        }
    }

    private static void actualizarCliente() {
        System.out.println(ColorANSI.AZUL.pintar("\n ID del usuario a editar:"));
        Long idUsuario = Long.parseLong(sc.nextLine().trim());
        Cliente usuario = ClienteController.consultarCliente(idUsuario);
        if (usuario == null) {
            System.out.println(ColorANSI.ROJO.pintar("\n No existe ningun usuario con el ID: " + idUsuario));
            return;
        }

        System.out.println(ColorANSI.AZUL.pintar("\n Recuerda que si dejas vacio un dato, mantiene el anterior. ;)"));
        System.out.println(ColorANSI.AMARILLO.pintar("Nuevo nombre (" + usuario.getNombre() + "): "));
        String nuevoNombre = sc.nextLine();
        System.out.println(ColorANSI.AMARILLO.pintar("Nuevo apellido (" + usuario.getApellido() + "): "));
        String nuevoApellido = sc.nextLine();
        System.out.println(ColorANSI.AMARILLO.pintar("Nuevo sexo (" + usuario.getSexo() + "): "));
        String nuevoSexo = sc.nextLine();
        System.out.println(ColorANSI.AMARILLO.pintar("Nuevo ciudad (" + usuario.getCiudad() + "): "));
        String nuevoCiudad = sc.nextLine();
        System.out.println(ColorANSI.AMARILLO.pintar("Nuevo telefono (" + usuario.getTelefono() + "): "));
        String nuevoTelefono = sc.nextLine();
        System.out.println(ColorANSI.AMARILLO.pintar("Nuevo correo electronico (" + usuario.getMail() + "): "));
        String nuevoMail = sc.nextLine();
        System.out.println(ColorANSI.AMARILLO.pintar("Nuevo fecha de nacimiento (" + usuario.getFechaNacimiento() + "): "));
        String nuevoFechaNacimiento = sc.nextLine();

        ClienteController.actualizarCliente(idUsuario, nuevoNombre, nuevoApellido, nuevoSexo, nuevoCiudad, nuevoMail, nuevoTelefono, nuevoFechaNacimiento);
    }

    private static void eliminarCliente() {
        System.out.println(ColorANSI.AZUL.pintar("\n ID del usuario a eliminar: "));
        Long idUsuario = Long.parseLong(sc.nextLine().trim());
        boolean eliminado = ClienteController.eliminarCliente(idUsuario);

        if (eliminado) {
            System.out.println(ColorANSI.VERDE.pintar("\n Cliente eliminado correctamente."));
        } else {
            System.out.println(ColorANSI.ROJO.pintar("\n No se ha encontrado ningun usuario con el ID: " + idUsuario));
        }
    }
}
