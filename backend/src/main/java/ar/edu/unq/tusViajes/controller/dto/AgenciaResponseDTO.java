package ar.edu.unq.tusViajes.controller.dto;
 
import lombok.AllArgsConstructor;
import lombok.Getter;
 
@Getter
@AllArgsConstructor
public class AgenciaResponseDTO {
    private Long id;
    private String razonSocial;
    private String cuit;
}

