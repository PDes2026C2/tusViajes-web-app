package ar.edu.unq.tusViajes.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
 
@Entity
@Table(name = "perfil_comprador")
@PrimaryKeyJoinColumn(name = "id")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PerfilComprador extends Usuario {
 
    @Column(length = 30)
    private String telefono;

    @Column(unique = true, nullable = false, length = 8)
    private String dni;
 
    public PerfilComprador(String nombre, String apellido, String email,
                            String passwordHash, String telefono, String dni) {
        super(nombre, apellido, email, passwordHash);
        this.telefono = telefono;
        this.dni = dni;
    }
 
}