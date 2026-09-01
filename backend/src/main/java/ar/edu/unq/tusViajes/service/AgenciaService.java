package ar.edu.unq.tusViajes.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unq.tusViajes.controller.dto.AgenciaRequestDTO;
import ar.edu.unq.tusViajes.controller.dto.AgenciaResponseDTO;
import ar.edu.unq.tusViajes.exception.DuplicateResourceException;
import ar.edu.unq.tusViajes.exception.ResourceNotFoundException;
import ar.edu.unq.tusViajes.model.Agencia;
import ar.edu.unq.tusViajes.repository.AgenciaRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AgenciaService {
  private final AgenciaRepository agenciaRepository;
 
    @Transactional(readOnly = true)
    public List<AgenciaResponseDTO> listar() {
        return agenciaRepository.findAll().stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }
 
    @Transactional(readOnly = true)
    public AgenciaResponseDTO buscarPorId(Long id) {
        return toResponseDTO(buscarEntidadPorId(id));
    }
 
    @Transactional
    public AgenciaResponseDTO crear(AgenciaRequestDTO dto) {
        if (agenciaRepository.existsByCuit(dto.getCuit())) {
            throw new DuplicateResourceException("Ya existe una agencia con el CUIT " + dto.getCuit());
        }
        Agencia agencia = new Agencia(dto.getRazonSocial(), dto.getCuit());
        return toResponseDTO(agenciaRepository.save(agencia));
    }
 
    @Transactional
    public AgenciaResponseDTO actualizar(Long id, AgenciaRequestDTO dto) {
        Agencia agencia = buscarEntidadPorId(id);
        agencia.actualizarRazonSocial(dto.getRazonSocial());
        return toResponseDTO(agencia);
    }
 
    @Transactional
    public void eliminar(Long id) {
        if (!agenciaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Agencia " + id + " no encontrada");
        }
        agenciaRepository.deleteById(id);
    }
 
    public Agencia buscarEntidadPorId(Long id) {
        return agenciaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Agencia " + id + " no encontrada"));
    }
 
    public AgenciaResponseDTO toResponseDTO(Agencia agencia) {
        return new AgenciaResponseDTO(agencia.getId(), agencia.getRazonSocial(), agencia.getCuit());
    }
}
