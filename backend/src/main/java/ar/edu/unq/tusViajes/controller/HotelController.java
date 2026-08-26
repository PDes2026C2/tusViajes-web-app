package ar.edu.unq.tusViajes.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ar.edu.unq.tusViajes.controller.dto.HotelRequestDTO;
import ar.edu.unq.tusViajes.controller.dto.HotelResponseDTO;
import ar.edu.unq.tusViajes.service.HotelService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/hoteles")
@RequiredArgsConstructor
public class HotelController {

    private final HotelService hotelService;

    @GetMapping
    public List<HotelResponseDTO> listar() {
        return hotelService.listar();
    }

    @GetMapping("/{id}")
    public HotelResponseDTO buscarPorId(@PathVariable Long id) {
        return hotelService.buscarPorId(id);
    }

    @PostMapping
    public ResponseEntity<HotelResponseDTO> crear(@Valid @RequestBody HotelRequestDTO dto) {
        HotelResponseDTO creado = hotelService.crear(dto);
        return ResponseEntity.created(URI.create("/api/hoteles/" + creado.getId())).body(creado);
    }

    @PutMapping("/{id}")
    public HotelResponseDTO actualizar(@PathVariable Long id, @Valid @RequestBody HotelRequestDTO dto) {
        return hotelService.actualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        hotelService.eliminar(id);
    }
}
