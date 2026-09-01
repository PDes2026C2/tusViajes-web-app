package ar.edu.unq.tusViajes.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
 
import ar.edu.unq.tusViajes.controller.dto.PerfilCompradorRequestDTO;
import ar.edu.unq.tusViajes.controller.dto.PerfilCompradorResponseDTO;
import ar.edu.unq.tusViajes.model.PerfilComprador;
import ar.edu.unq.tusViajes.repository.PerfilCompradorRepository;
import ar.edu.unq.tusViajes.validator.EntityValidator;
import ar.edu.unq.tusViajes.validator.UsuarioValidator;
import lombok.RequiredArgsConstructor;
 
@Service
@RequiredArgsConstructor
public class PerfilCompradorService {

    private final EntityValidator entityValidator;
    private final UsuarioValidator usuarioValidator;
    private final PerfilCompradorRepository perfilCompradorRepository;
    private final PasswordEncoder passwordEncoder;
 
    @Transactional(readOnly = true)
    public List<PerfilCompradorResponseDTO> listar() {
        return perfilCompradorRepository.findAll().stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }
 
    @Transactional(readOnly = true)
    public PerfilCompradorResponseDTO buscarPorId(Long id) {
        return toResponseDTO(buscarEntidadPorId(id));
    }
 
    @Transactional
    public PerfilCompradorResponseDTO registrar(PerfilCompradorRequestDTO dto) {
        usuarioValidator.validarEmailDisponible(dto.email());
        String hash = passwordEncoder.encode(dto.password());
        PerfilComprador perfil = new PerfilComprador(dto.nombre(), dto.apellido(), dto.email(), hash,
                dto.telefono(), dto.dni());
        return toResponseDTO(perfilCompradorRepository.save(perfil));
    }
 
    private PerfilComprador buscarEntidadPorId(Long id) {
        return entityValidator.findByIdOrThrow(perfilCompradorRepository,id,"Comprador");
    }
 
    private PerfilCompradorResponseDTO toResponseDTO(PerfilComprador perfil) {
        return new PerfilCompradorResponseDTO(perfil.getId(), perfil.getNombre(), perfil.getApellido(),
                perfil.getEmail(), perfil.getTelefono(), perfil.getDni());
    }
}