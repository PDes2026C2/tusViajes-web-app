package ar.edu.unq.tusViajes.controller;

import ar.edu.unq.tusViajes.builder.PerfilCompradorBuilder;
import ar.edu.unq.tusViajes.model.PerfilComprador;
import ar.edu.unq.tusViajes.repository.PerfilCompradorRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Testcontainers
@SpringBootTest
@AutoConfigureMockMvc
@Transactional 
class PerfilCompradorControllerTest {

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15-alpine");

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PerfilCompradorRepository perfilCompradorRepository;

    @Test
    void listar_retorna200YListaDeCompradores() throws Exception {
        perfilCompradorRepository.save(
                PerfilCompradorBuilder.aPerfilComprador()
                        .withNombre("Lucas")
                        .withEmail("lucas@example.com")
                        .build()
        );

        mockMvc.perform(get("/api/compradores"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").isNotEmpty())
                .andExpect(jsonPath("$[0].nombre").value("Lucas"));
    }

    @Test
    void buscarPorId_retorna200CuandoExiste() throws Exception {
        PerfilComprador guardado = perfilCompradorRepository.save(
                PerfilCompradorBuilder.aPerfilComprador()
                        .withDni("38123456")
                        .build()
        );

        mockMvc.perform(get("/api/compradores/" + guardado.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(guardado.getId()))
                .andExpect(jsonPath("$.dni").value("38123456"));
    }

    @Test
    void buscarPorId_retorna404CuandoNoExiste() throws Exception {
        mockMvc.perform(get("/api/compradores/99999"))
                .andExpect(status().isNotFound());
    }

    @Test
    void registrar_retorna201YLocationHeader() throws Exception {
        String json = """
                {
                    "nombre": "Lucas",
                    "apellido": "Gomez",
                    "email": "lucas@example.com",
                    "password": "secretPassword123",
                    "telefono": "11223344",
                    "dni": "38123456"
                }
                """;

        mockMvc.perform(post("/api/compradores")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"))
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.dni").value("38123456"));
    }
}