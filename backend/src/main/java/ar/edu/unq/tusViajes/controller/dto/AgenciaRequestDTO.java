package ar.edu.unq.tusViajes.controller.dto;
 
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
 
@Data
public class AgenciaRequestDTO {
 
    @NotBlank(message = "La razon social es obligatoria")
    private String razonSocial;
 
    @NotBlank(message = "El CUIT es obligatorio")
    @Pattern(regexp = "\\d{2}-\\d{8}-\\d{1}", message = "El CUIT debe tener el formato XX-XXXXXXXX-X")
    private String cuit;
 
}
 