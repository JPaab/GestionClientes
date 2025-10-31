# CRUD de Clientes — Java & Hibernate/JPA (CLI)

Este programa se usa en la consola para manejar clientes con **CRUD** (crear, listar, consultar, filtrar, actualizar y eliminar).
Usando **JPA (Jakarta Persistence)**, **Hibernate** y **MySQL** para guardar los clientes en la base de datos.

---

## ⚙️ ¿Como arrancarlo?

Para ejecturar el programa, confirma que los siguientes puntos estan correctos.

1. **Java instalado**

   * JDK 17 o superior.

2. **Base de datos lista**

   * MySQL +8 (o compatible).
   * Crea el schema de la BD `clientes` antes de arrancar el programa.

3. **`persistence.xml` correcto**

   * Path: `src/main/resources/META-INF/persistence.xml`.
   * Para probar, deja `hibernate.hbm2ddl.auto=update`, `create` elimina la BD anterior.

4. **Dependencias usadas**

   * Usé **Maven** (desde IntelliJ): agregué las dependencias de JPA/Hibernate/MySQL en `pom.xml`.

---

## 🖥️ Uso (Menú CLI)

El menú está en `Main` con el siguiente menú:

1. **Registrar usuario.**                     
2. **Listar usuarios.**
3. **Consultar usuario por ID**
4. **Filtrar por ciudad**
5. **Actualizar usuario**
6. **Eliminar usuario**
7. **Salir**

Las opciones usan colores ANSI mediante `ColorANSI` para una lectura mas clara. Los métodos del controlador (`ClienteController`) validan entradas y delegan en `ClienteRepositoryJPA` para las transacciones JPA hacia la BD.

<img width="445" height="417" alt="image" src="https://github.com/user-attachments/assets/340e419c-3091-4d50-ad52-e8a78aa2e194"/>

---

## 📁 Estructura

```
org.pablo
 ├─ Main.java                         # Menú CLI.
 ├─ controllers
 │   └─ ClienteController.java        # Logica del programa.
 ├─ entities
 │   └─ Cliente.java                  # Configuración tablas, entidades y columnas.
 ├─ persistance
 │   ├─ ConfigJPA.java                # Configuración EMF & EM.
 │   └─ ClienteRepositoryJPA.java     # Comunicar hacia la base de datos.
 └─ utils
     └─ ColorANSI.java                # Añadidos para la estetica del CLI.
```

---

## 🦜 Entidad "Cliente"

Campos principales:

* `id` (`Long`, ID auto-incremental)
* `nombre`, `apellido`, `sexo`, `ciudad`, `mail`, `telefono`, `fechaNacimiento`

La tabla se mapea con `@Entity` y `@Table(name = "Clientes")`. 
El `toString()` está formateado con colores ANSI para lectura en consola.

## 🧷 Configuración Persistencia

Archivo: `src/main/resources/META-INF/persistence.xml`

```xml
<persistence xmlns="https://jakarta.ee/xml/ns/persistence" version="3.0">
  <persistence-unit name="GestionClientesPU" transaction-type="RESOURCE_LOCAL">
    <properties>
      <!-- JDBC -->
      <property name="jakarta.persistence.jdbc.driver" value="com.mysql.cj.jdbc.Driver"/>
      <property name="jakarta.persistence.jdbc.url" value="jdbc:mysql://localhost:3306/clientes?serverTimezone=UTC"/>
      <property name="jakarta.persistence.jdbc.user" value="root"/>
      <property name="jakarta.persistence.jdbc.password" value=""/>
      
      <!-- Hibernate -->
      <property name="hibernate.dialect" value="org.hibernate.dialect.MySQLDialect"/>
      <property name="hibernate.hbm2ddl.auto" value="update"/>
      <property name="hibernate.show_sql" value="false"/>
      <property name="hibernate.format_sql" value="false"/>
    </properties>
  </persistence-unit>
</persistence>
```

> **Anotaciones:**
>
> * `<persistence-unit name="GestionClientesPU" transaction-type="RESOURCE_LOCAL">` el nombre de la PU debe coincidir con el nombre de la EMF `ConfigJPA`
> * `hibernate.show_sql` & `hibernate.format_sql` considera cambiar el valor por "true" para activar los logs en la consola.
> * `"jdbc:mysql://localhost:3306/clientes?serverTimezone=UTC"` considera cambiar el puerto del localhost al usado en tu PC.

### BD

Con la configuración por defecto se creará una tabla `Clientes` con las columnas del cliente. Si prefieres cambiar el nombre es sencillo.

---

## ✅ Requisitos

* **Java +17** (recomendado)
* **MySQL +8** (o compatible) y el **MySQL Connector/J** en el archivo `pom.xml`
* Dependencias JPA/Hibernate en el archivo `pom.xml`

---

## 🧟 Posibles errores

* **`Unknown database 'clientes'`**: crea la BD o ajusta la URL.
* **`ClassNotFoundException: com.mysql.cj.jdbc.Driver`**: falta el conector en dependencias.

---
