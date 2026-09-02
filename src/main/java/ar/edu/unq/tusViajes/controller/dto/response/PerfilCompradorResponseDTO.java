package ar.edu.unq.tusViajes.controller.dto;

  public record PerfilCompradorResponseDTO (
    Long id,
    String nombre,
    String apellido,
    String email,
    String telefono,
    String dni
) {}
