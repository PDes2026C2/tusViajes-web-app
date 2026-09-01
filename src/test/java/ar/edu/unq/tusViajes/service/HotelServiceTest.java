package ar.edu.unq.tusViajes.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import ar.edu.unq.tusViajes.controller.dto.HotelRequestDTO;
import ar.edu.unq.tusViajes.controller.dto.HotelResponseDTO;
import ar.edu.unq.tusViajes.exception.ResourceNotFoundException;
import ar.edu.unq.tusViajes.model.Hotel;
import ar.edu.unq.tusViajes.repository.HotelRepository;

@ExtendWith(MockitoExtension.class)
class HotelServiceTest {

    @Mock
    private HotelRepository hotelRepository;

    private HotelService hotelService;

    @BeforeEach
    void setUp() {
        hotelService = new HotelService(hotelRepository);
    }

    @Test
    void buscarPorIdDevuelveElHotelCuandoExiste() {
        Hotel hotel = new Hotel("Hotel Central", "Bariloche", "http://ejemplo.com/foto.jpg", "Desayuno");
        when(hotelRepository.findById(1L)).thenReturn(Optional.of(hotel));

        HotelResponseDTO resultado = hotelService.buscarPorId(1L);

        assertThat(resultado.getNombre()).isEqualTo("Hotel Central");
        assertThat(resultado.getDestino()).isEqualTo("Bariloche");
    }

    @Test
    void buscarPorIdLanzaExcepcionCuandoNoExiste() {
        when(hotelRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> hotelService.buscarPorId(99L))
                .isInstanceOf(ResourceNotFoundException.class);
    }

    @Test
    void crearGuardaYDevuelveElHotelCreado() {
        HotelRequestDTO dto = new HotelRequestDTO();
        dto.setNombre("Hotel Nuevo");
        dto.setDestino("Mendoza");

        when(hotelRepository.save(any(Hotel.class)))
                .thenAnswer(invocacion -> invocacion.getArgument(0));

        HotelResponseDTO resultado = hotelService.crear(dto);

        assertThat(resultado.getNombre()).isEqualTo("Hotel Nuevo");
        assertThat(resultado.getDestino()).isEqualTo("Mendoza");
    }
}
