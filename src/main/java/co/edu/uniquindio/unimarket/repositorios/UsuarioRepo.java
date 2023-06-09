package co.edu.uniquindio.unimarket.repositorios;

import co.edu.uniquindio.unimarket.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepo extends JpaRepository<Usuario, Integer> {


    @Query("select u from Usuario u where u.email = :email")
    Usuario buscarUsuarioPorEmail(String email);

    @Query("select u from Usuario u where u.cedula = :cedula")
    Optional<Usuario> buscarUsuarioPorCedula(String cedula);

}
