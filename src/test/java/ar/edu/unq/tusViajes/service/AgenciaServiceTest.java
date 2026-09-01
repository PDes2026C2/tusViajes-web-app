package ar.edu.unq.tusViajes.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import ar.edu.unq.tusViajes.controller.dto.AgenciaRequestDTO;
import ar.edu.unq.tusViajes.controller.dto.AgenciaResponseDTO;
import ar.edu.unq.tusViajes.exception.ResourceNotFoundException;
import ar.edu.unq.tusViajes.exception.DuplicateResourceException;
import ar.edu.unq.tusViajes.model.Agencia;
import ar.edu.unq.tusViajes.repository.AgenciaRepository;
import ar.edu.unq.tusViajes.validator.EntityValidator;

@ExtendWith(MockitoExtension.class)
public class AgenciaServiceTest {

    @Mock
    private AgenciaRepository agenciaRepository;
    private EntityValidator entityValidator;

    private AgenciaService agenciaService;

    @BeforeEach
    void setUp() {
        agenciaService = new AgenciaService(entityValidator, agenciaRepository);
    }

    @Test
    void buscarPorIdDevuelveLaAgenciaCuandoExiste() {
        Agencia agencia = new Agencia("Huryn", "20-44576859-8");
        when(agenciaRepository.findById(1L)).thenReturn(Optional.of(agencia));

        AgenciaResponseDTO resultado = agenciaService.buscarPorId(1L);

        assertThat(resultado.razonSocial()).isEqualTo("Huryn");
        assertThat(resultado.cuit()).isEqualTo("20-44576859-8");
    }

    
    void crearLanzaRecursoDuplicadoExceptionCuandoElCuitYaExiste() {
        AgenciaRequestDTO dto = new AgenciaRequestDTO("Viajes del Sur SA", "30-12345678-9");
 
        when(agenciaRepository.existsByCuit("30-12345678-9")).thenReturn(true);
 
        assertThatThrownBy(() -> agenciaService.crear(dto))
                .isInstanceOf(DuplicateResourceException.class)
                .hasMessageContaining("30-12345678-9");
        verify(agenciaRepository, never()).save(any());
    }

    @Test
    void buscarPorIdLanzaExcepcionCuandoNoExiste() {
        when(agenciaRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> agenciaService.buscarPorId(99L))
                .isInstanceOf(ResourceNotFoundException.class);
    }

    @Test
    void crearGuardaYDevuelveLaAgenciaCreada() {
        AgenciaRequestDTO dto = new AgenciaRequestDTO("Huryn","20-44576859-8");

        when(agenciaRepository.save(any(Agencia.class)))
                .thenAnswer(invocacion -> invocacion.getArgument(0));

        AgenciaResponseDTO resultado = agenciaService.crear(dto);

        assertThat(resultado.razonSocial()).isEqualTo("Huryn");
        assertThat(resultado.cuit()).isEqualTo("20-44576859-8");
    }
}
