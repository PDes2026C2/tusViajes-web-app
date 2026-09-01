package ar.edu.unq.tusViajes.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "paquete")
@Getter
@NoArgsConstructor
public class Paquete {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(length = 1000)
    private String descripcion;

    @Column(nullable = false)
    private Double precio;

    @Column(name = "fecha_inicio", nullable = false)
    private LocalDateTime fechaInicio;

    @Column(name = "fecha_fin", nullable = false)
    private LocalDateTime fechaFin;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "agencia_id", nullable = false)
    private Agencia agencia;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vuelo_ida_id")
    private Vuelo vueloIda;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vuelo_vuelta_id")
    private Vuelo vueloVuelta;

    public Paquete(String nombre, String descripcion, Double precio, LocalDateTime fechaInicio,
                   LocalDateTime fechaFin, Hotel hotel, Agencia agencia, Vuelo vueloIda,
                   Vuelo vueloVuelta) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.hotel = hotel;
        this.agencia = agencia;
        this.vueloIda = vueloIda;
        this.vueloVuelta = vueloVuelta;
    }

    public void actualizarDatos(String nombre, String descripcion, Double precio, LocalDateTime fechaInicio,
                   LocalDateTime fechaFin, Hotel hotel, Agencia agencia, Vuelo vueloIda,
                   Vuelo vueloVuelta) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.hotel = hotel;
        this.agencia = agencia;
        this.vueloIda = vueloIda;
        this.vueloVuelta = vueloVuelta;
    }
}
