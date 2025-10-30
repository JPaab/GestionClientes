package org.pablo.persistance;

import jakarta.persistence.EntityManager;
import org.pablo.entities.Cliente;

import java.util.Collections;
import java.util.List;

public class ClienteRepositoryJPA {
    public static void registrarCliente(Cliente usuario) {
        try (EntityManager em = ConfigJPA.getEntityManager();) {
            em.getTransaction().begin();
            em.persist(usuario);
            em.getTransaction().commit();
        }
    }

    public static List<Cliente> listarCliente() {
        try (EntityManager em = ConfigJPA.getEntityManager();) {
            return em.createQuery("SELECT u FROM Cliente u", Cliente.class).getResultList();
        }
    }

    public static Cliente consultarCliente(Long idUsuario) {
        try (EntityManager em = ConfigJPA.getEntityManager();) {
            return em.find(Cliente.class, idUsuario);
        }
    }

    public static List<Cliente> filtrarCiudadCliente(String ciudad) {
        if (ciudad == null || ciudad.isBlank())
            return Collections.emptyList();

        String busqueda = ciudad.trim().toLowerCase() + "%";

        try (EntityManager em = ConfigJPA.getEntityManager()) {
            return em.createQuery(
                            "SELECT u FROM Cliente u " +
                                    "WHERE LOWER(TRIM(COALESCE(u.ciudad, ''))) LIKE :busqueda " +
                                    "ORDER BY u.id", Cliente.class)
                    .setParameter("busqueda", busqueda)
                    .getResultList();
        }
    }

    public static void actualizarCliente(Cliente usuario) {
        try (EntityManager em = ConfigJPA.getEntityManager();) {
            em.getTransaction().begin();
            em.merge(usuario);
            em.getTransaction().commit();

        }
    }

    public static boolean eliminarCliente(Long idUsuario) {
        try (EntityManager em = ConfigJPA.getEntityManager();) {
            Cliente usuario = em.find(Cliente.class, idUsuario);
            if (usuario == null) {
                return false;
            } else {
                em.getTransaction().begin();
                em.remove(usuario);
                em.getTransaction().commit();
                return true;
            }
        }
    }
}