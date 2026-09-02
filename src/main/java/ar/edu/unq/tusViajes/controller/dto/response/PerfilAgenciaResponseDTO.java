package ar.edu.unq.tusViajes.controller.dto;
 

public record PerfilAgenciaResponseDTO(
    Long id,
    String nombre,
    String apellido,
    String email,
    Long agenciaId,
    String agenciaRazonSocial
) {}
   