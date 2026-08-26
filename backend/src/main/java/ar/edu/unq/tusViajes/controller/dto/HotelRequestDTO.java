package ar.edu.unq.tusViajes.controller.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class HotelRequestDTO {

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @NotBlank(message = "El destino es obligatorio")
    private String destino;

    private String direccion;

    private String descripcion;

    private String fotoUrl;

    @Min(value = 1, message = "La categoria debe ser entre 1 y 5")
    @Max(value = 5, message = "La categoria debe ser entre 1 y 5")
    private Integer categoria;
}
