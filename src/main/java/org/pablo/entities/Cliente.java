package org.pablo.entities;

import jakarta.persistence.*;
import org.pablo.utils.ColorANSI;

import java.time.LocalDate;

@Entity
@Table(name = "Clientes")

public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "Nombre", nullable = false, length = 40)
    private String nombre;
    @Column(name = "Apellido", nullable = false, length = 40)
    private String apellido;
    @Enumerated(EnumType.STRING)
    @Column(name = "Sexo", nullable = false, length = 1)
    private Sexo sexo;
    @Column(name = "Ciudad", nullable = false, length = 40)
    private String ciudad;
    @Column(name = "Email", nullable = false, length = 40)
    private String mail;
    @Column(name = "Telefono", nullable = false, length = 40)
    private String telefono;
    @Column(name = "Fecha_Nacimiento", nullable = false, length = 40)
    private LocalDate fechaNacimiento;

    public Cliente() {
    }

    public Cliente(String nombre, String apellido, Sexo sexo, String ciudad, String mail, String telefono, LocalDate fechaNacimiento) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.sexo = sexo;
        this.ciudad = ciudad;
        this.mail = mail;
        this.telefono = telefono;
        this.fechaNacimiento = fechaNacimiento;
    }
    public enum Sexo {M, F, X}

    // GETTER'S

    public String getNombre() {
        return nombre;
    }
    public String getApellido() {
        return apellido;
    }
    public Sexo getSexo() {
        return sexo;
    }
    public String getCiudad() {
        return ciudad;
    }
    public String getMail() {
        return mail;
    }
    public String getTelefono() {
        return telefono;
    }
    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    // SETTER'S

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }
    public void setMail(String mail) {
        this.mail = mail;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    // GETTER del ID
    public Long getId() {
        return id;
    }

    // toString
    @Override
    public String toString() {
        return  ColorANSI.AMARILLO.pintar("\n Cliente con ID: ") + id + "\n"
                + " - " + ColorANSI.AZUL.pintar("Nombre: ") + nombre + "\n"
                + " - " + ColorANSI.AZUL.pintar("Apellido: ") + apellido + "\n"
                + " - " + ColorANSI.AZUL.pintar("Sexo: ") + sexo + "\n"
                + " - " + ColorANSI.AZUL.pintar("Ciudad: ") + ciudad + "\n"
                + " - " + ColorANSI.AZUL.pintar("Email: ") + mail + "\n"
                + " - " + ColorANSI.AZUL.pintar("Telefono: ") + telefono + "\n"
                + " - " + ColorANSI.AZUL.pintar("Fecha de nacimiento: ") + fechaNacimiento + "\n";
    }
}