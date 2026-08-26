package ar.edu.unq.tusViajes.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unq.tusViajes.controller.dto.HotelRequestDTO;
import ar.edu.unq.tusViajes.controller.dto.HotelResponseDTO;
import ar.edu.unq.tusViajes.exception.ResourceNotFoundException;
import ar.edu.unq.tusViajes.model.Hotel;
import ar.edu.unq.tusViajes.repository.HotelRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor 
public class HotelService {

    private final HotelRepository hotelRepository;

    @Transactional(readOnly = true)
    public List<HotelResponseDTO> listar() {
        return hotelRepository.findAll().stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public HotelResponseDTO buscarPorId(Long id) {
        return toResponseDTO(buscarEntidadPorId(id));
    }

    @Transactional
    public HotelResponseDTO crear(HotelRequestDTO dto) {
        Hotel hotel = new Hotel(dto.getNombre(), dto.getDestino(), dto.getDireccion(),
                dto.getDescripcion(), dto.getFotoUrl(), dto.getCategoria());
        return toResponseDTO(hotelRepository.save(hotel));
    }

    @Transactional
    public HotelResponseDTO actualizar(Long id, HotelRequestDTO dto) {
        Hotel hotel = buscarEntidadPorId(id);
        hotel.actualizarDatos(dto.getNombre(), dto.getDestino(), dto.getDireccion(),
                dto.getDescripcion(), dto.getFotoUrl(), dto.getCategoria());
        return toResponseDTO(hotel);
    }

    @Transactional
    public void eliminar(Long id) {
        if (!hotelRepository.existsById(id)) {
            throw new ResourceNotFoundException("Hotel " + id + " no encontrado");
        }
        hotelRepository.deleteById(id);
    }

    private Hotel buscarEntidadPorId(Long id) {
        return hotelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel " + id + " no encontrado"));
    }

    private HotelResponseDTO toResponseDTO(Hotel hotel) {
        return new HotelResponseDTO(hotel.getId(), hotel.getNombre(), hotel.getDestino(),
                hotel.getDireccion(), hotel.getDescripcion(), hotel.getFotoUrl(), hotel.getCategoria());
    }
}
