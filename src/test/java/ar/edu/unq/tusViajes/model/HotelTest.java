package ar.edu.unq.tusViajes.model;

import static org.assertj.core.api.Assertions.assertThat;
 
import org.junit.jupiter.api.Test;
 
class HotelTest {
 
    @Test
    void constructor_asignaTodosLosCampos() {
        Hotel hotel = new Hotel("Hotel Central", "Av. Bustillo 123, Bariloche",
         "http://ejemplo.com/foto.jpg", "Desayuno");
 
        assertThat(hotel.getNombre()).isEqualTo("Hotel Central");
        assertThat(hotel.getDestino()).isEqualTo("Av. Bustillo 123, Bariloche");
        assertThat(hotel.getFotoUrl()).isEqualTo("http://ejemplo.com/foto.jpg");
        assertThat(hotel.getServicio()).isEqualTo("Desayuno");
    }
 
    @Test
    void constructor_noAsignaIdTodavia() {
        Hotel hotel = new Hotel("Hotel Central", "Bariloche", null, null);
 
        assertThat(hotel.getId()).isNull();
    }
 
    @Test
    void actualizarDatos_sobreescribeTodosLosCampos() {
        Hotel hotel = new Hotel("Hotel Viejo", "Calle 1, Mendoza", "foto-vieja.jpg", "Todo incluido");
 
        hotel.actualizarDatos("Hotel Nuevo", "Calle 2, Salta", "foto-nueva.jpg", "Nada");
 
        assertThat(hotel.getNombre()).isEqualTo("Hotel Nuevo");
        assertThat(hotel.getDestino()).isEqualTo("Calle 2, Salta");
        assertThat(hotel.getFotoUrl()).isEqualTo("foto-nueva.jpg");
        assertThat(hotel.getServicio()).isEqualTo("Nada");
    }
 
}