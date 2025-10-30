package org.pablo.entities;

import jakarta.persistence.*;
import org.pablo.utils.ColorANSI;

@Entity
@Table(name = "Clientes")

public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 40)
    private String nombre, apellido, sexo, ciudad, mail, telefono, fechaNacimiento;

    public Cliente() {
    }

    public Cliente(String nombre, String apellido, String sexo, String ciudad, String mail, String telefono, String fechaNacimiento) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.sexo = sexo;
        this.ciudad = ciudad;
        this.mail = mail;
        this.telefono = telefono;
        this.fechaNacimiento = fechaNacimiento;
    }

    // GETTER'S

    public String getNombre() {
        return nombre;
    }
    public String getApellido() {
        return apellido;
    }
    public String getSexo() {
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
    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    // SETTER'S

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    public void setSexo(String sexo) {
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
    public void setFechaNacimiento(String fechaNacimiento) {
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