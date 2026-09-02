package ar.edu.unq.tusViajes.model;

import ar.edu.unq.tusViajes.builder.PerfilCompradorBuilder;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PerfilCompradorTest {

    @Test
    void crearPerfilComprador_asignaDatosBasicosYEspecificos() {
        PerfilComprador comprador = PerfilCompradorBuilder.aPerfilComprador()
                .withNombre("Maria")
                .withApellido("Lopez")
                .withEmail("maria@example.com")
                .withTelefono("11-9999-8888")
                .withDni("40123456")
                .build();

        assertThat(comprador.getNombre()).isEqualTo("Maria");
        assertThat(comprador.getApellido()).isEqualTo("Lopez");
        assertThat(comprador.getEmail()).isEqualTo("maria@example.com");
        assertThat(comprador.getTelefono()).isEqualTo("11-9999-8888");
        assertThat(comprador.getDni()).isEqualTo("40123456");
    }
}

