package ar.edu.unq.tusViajes.controller.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class HotelRequestDTO {

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @NotBlank(message = "El destino es obligatorio")
    private String destino;

    private String fotoUrl;

    private String servicio;
}
