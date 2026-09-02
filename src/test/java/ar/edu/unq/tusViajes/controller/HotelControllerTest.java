package ar.edu.unq.tusViajes.controller;

import ar.edu.unq.tusViajes.controller.dto.HotelRequestDTO;
import ar.edu.unq.tusViajes.controller.dto.HotelResponseDTO;
import ar.edu.unq.tusViajes.exception.GlobalExceptionHandler;
import ar.edu.unq.tusViajes.exception.ResourceNotFoundException;
import ar.edu.unq.tusViajes.service.HotelService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class HotelControllerTest {

    private MockMvc mockMvc;

    @Mock
    private HotelService hotelService;

    @InjectMocks
    private HotelController hotelController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(hotelController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();
    }

    @Test
    void listar_retorna200YListaDeHoteles() throws Exception {
        HotelResponseDTO hotel = new HotelResponseDTO(1L, "Hotel Central", "Bariloche", "foto.jpg", "WiFi");
        when(hotelService.listar()).thenReturn(List.of(hotel));

        mockMvc.perform(get("/api/hoteles"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].nombre").value("Hotel Central"));
    }

    @Test
    void buscarPorId_retorna200CuandoExiste() throws Exception {
        HotelResponseDTO hotel = new HotelResponseDTO(1L, "Hotel Central", "Bariloche", "foto.jpg", "WiFi");
        when(hotelService.buscarPorId(1L)).thenReturn(hotel);

        mockMvc.perform(get("/api/hoteles/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nombre").value("Hotel Central"));
    }

    @Test
    void buscarPorId_retorna404CuandoNoExiste() throws Exception {
        when(hotelService.buscarPorId(99L)).thenThrow(new ResourceNotFoundException("Hotel 99 no encontrado"));

        mockMvc.perform(get("/api/hoteles/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    void crear_retorna201YLocationHeader() throws Exception {
        HotelResponseDTO response = new HotelResponseDTO(1L, "Hotel Nuevo", "Mendoza", null, null);
        when(hotelService.crear(any(HotelRequestDTO.class))).thenReturn(response);

        String json = """
                {
                    "nombre": "Hotel Nuevo",
                    "destino": "Mendoza"
                }
                """;

        mockMvc.perform(post("/api/hoteles")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "/api/hoteles/1"))
                .andExpect(jsonPath("$.id").value(1));
    }
}

