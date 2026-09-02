package ar.edu.unq.tusViajes.controller;

import java.net.URI;
import java.util.List;
 
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
 
import ar.edu.unq.tusViajes.controller.dto.PerfilAgenciaRequestDTO;
import ar.edu.unq.tusViajes.controller.dto.PerfilAgenciaResponseDTO;
import ar.edu.unq.tusViajes.service.PerfilAgenciaService;
 
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
 
// El prefijo /api/admin/... es a proposito, para dejar claro que es un
// endpoint de gestion, no publico. TODO cuando exista login: proteger con
// @PreAuthorize("hasRole('ADMIN')") o el filtro JWT correspondiente -- por
// ahora queda abierto, igual que el resto de la API.
@RestController
@RequestMapping("/api/admin/perfiles-agencia")
@RequiredArgsConstructor
public class PerfilAgenciaController {
 
    private final PerfilAgenciaService perfilAgenciaService;
 
    @GetMapping
    public List<PerfilAgenciaResponseDTO> listar() {
        return perfilAgenciaService.listar();
    }
 
    @GetMapping("/{id}")
    public PerfilAgenciaResponseDTO buscarPorId(@PathVariable Long id) {
        return perfilAgenciaService.buscarPorId(id);
    }
 
    @PostMapping
    public ResponseEntity<PerfilAgenciaResponseDTO> crear(@Valid @RequestBody PerfilAgenciaRequestDTO dto) {
        PerfilAgenciaResponseDTO creado = perfilAgenciaService.crear(dto);
        return ResponseEntity.created(URI.create("/api/admin/perfiles-agencia/" + creado.id())).body(creado);
    }
}
