package ar.edu.unq.tusViajes.model;

import static org.assertj.core.api.Assertions.assertThat;
 
import org.junit.jupiter.api.Test;
 
class HotelTest {
 
    @Test
    void constructor_asignaTodosLosCampos() {
        Hotel hotel = new Hotel("Hotel Central", "Bariloche", "Av. Bustillo 123",
                "Frente al lago", "http://ejemplo.com/foto.jpg", 4);
 
        assertThat(hotel.getNombre()).isEqualTo("Hotel Central");
        assertThat(hotel.getDestino()).isEqualTo("Bariloche");
        assertThat(hotel.getDireccion()).isEqualTo("Av. Bustillo 123");
        assertThat(hotel.getDescripcion()).isEqualTo("Frente al lago");
        assertThat(hotel.getFotoUrl()).isEqualTo("http://ejemplo.com/foto.jpg");
        assertThat(hotel.getCategoria()).isEqualTo(4);
    }
 
    @Test
    void constructor_noAsignaIdTodavia() {
        // el id lo genera la base al persistir (@GeneratedValue), no el constructor java
        Hotel hotel = new Hotel("Hotel Central", "Bariloche", null, null, null, null);
 
        assertThat(hotel.getId()).isNull();
    }
 
    @Test
    void actualizarDatos_sobreescribeTodosLosCampos() {
        Hotel hotel = new Hotel("Hotel Viejo", "Mendoza", "Calle 1", "desc vieja", "foto-vieja.jpg", 2);
 
        hotel.actualizarDatos("Hotel Nuevo", "Salta", "Calle 2", "desc nueva", "foto-nueva.jpg", 5);
 
        assertThat(hotel.getNombre()).isEqualTo("Hotel Nuevo");
        assertThat(hotel.getDestino()).isEqualTo("Salta");
        assertThat(hotel.getDireccion()).isEqualTo("Calle 2");
        assertThat(hotel.getDescripcion()).isEqualTo("desc nueva");
        assertThat(hotel.getFotoUrl()).isEqualTo("foto-nueva.jpg");
        assertThat(hotel.getCategoria()).isEqualTo(5);
    }
 
    @Test
    void actualizarDatos_permiteDejarLaCategoriaEnNulo() {
        Hotel hotel = new Hotel("Hotel", "Cordoba", null, null, null, 3);
 
        hotel.actualizarDatos("Hotel", "Cordoba", null, null, null, null);
 
        assertThat(hotel.getCategoria()).isNull();
    }
}