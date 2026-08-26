package ar.edu.unq.tusViajes.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class HotelResponseDTO {
    private Long id;
    private String nombre;
    private String destino;
    private String direccion;
    private String descripcion;
    private String fotoUrl;
    private Integer categoria;
}
