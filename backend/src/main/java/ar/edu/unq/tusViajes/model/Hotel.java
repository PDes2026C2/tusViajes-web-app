package ar.edu.unq.tusViajes.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "hotel")
@Getter
@NoArgsConstructor
public class Hotel {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, length = 150)
    private String nombre;

    @Column(nullable = false, length = 100)
    private String destino;

    @Column(length = 200)
    private String direccion;

    @Column(length = 500)
    private String descripcion;

    @Column(name = "foto_url", length = 300)
    private String fotoUrl;

    private Integer categoria;

    public Hotel(String nombre, String destino, String direccion, String descripcion,
                 String fotoUrl, Integer categoria) {
        this.nombre = nombre;
        this.destino = destino;
        this.direccion = direccion;
        this.descripcion = descripcion;
        this.fotoUrl = fotoUrl;
        this.categoria = categoria;
    }

    public void actualizarDatos(String nombre, String destino, String direccion,
                                 String descripcion, String fotoUrl, Integer categoria) {
        this.nombre = nombre;
        this.destino = destino;
        this.direccion = direccion;
        this.descripcion = descripcion;
        this.fotoUrl = fotoUrl;
        this.categoria = categoria;
    }
}
