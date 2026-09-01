package ar.edu.unq.tusViajes.model.Usuario;

import ar.edu.unq.tusViajes.model.Agencia;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
 
@Entity
@Table(name = "perfil_agencia")
@PrimaryKeyJoinColumn(name = "id")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PerfilAgencia extends Usuario {
 
    @OneToOne
    @JoinColumn(name = "agencia_id", nullable = false, unique = true)
    private Agencia agencia;
 
    public PerfilAgencia(String nombre, String apellido,String dni, String email, String passwordHash, Agencia agencia) {
        super(nombre, apellido, dni, email, passwordHash);
        this.agencia = agencia;
    }
 
    public void actualizarDatos(String nombre, String apellido) {
        actualizarDatosBasicos(nombre, apellido);
    }
}