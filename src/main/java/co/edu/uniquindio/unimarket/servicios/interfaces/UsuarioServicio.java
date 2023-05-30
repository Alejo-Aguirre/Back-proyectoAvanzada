package co.edu.uniquindio.unimarket.servicios.interfaces;

import co.edu.uniquindio.unimarket.dto.UsuarioDTO;
import co.edu.uniquindio.unimarket.dto.UsuarioGetDTO;
import co.edu.uniquindio.unimarket.entidades.Usuario;

public interface UsuarioServicio {

    int crearUsuario(UsuarioDTO usuarioDTO) throws Exception;

    UsuarioGetDTO actualizarUsuario(int idUsuario,String contrasenia, UsuarioDTO usuarioDTO) throws Exception;


    int eliminarUsuario(int idUsuario, String contrasenia) throws Exception;

    UsuarioGetDTO obtenerUsuario(int idUsuario) throws Exception;

    Usuario obtener(int idUsuario) throws Exception;

    Usuario obtenerUsuarioPorId(int idPersona)throws Exception;
}
