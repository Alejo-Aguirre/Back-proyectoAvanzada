package co.edu.uniquindio.unimarket.controladores;

import co.edu.uniquindio.unimarket.dto.MensajeDTO;
import co.edu.uniquindio.unimarket.dto.ProductoDTO;
import co.edu.uniquindio.unimarket.dto.SesionDTO;
import co.edu.uniquindio.unimarket.dto.UsuarioDTO;
import co.edu.uniquindio.unimarket.entidades.enumeraciones.Categoria;
import co.edu.uniquindio.unimarket.servicios.interfaces.ProductoServicio;
import co.edu.uniquindio.unimarket.servicios.interfaces.SesionServicio;
import co.edu.uniquindio.unimarket.servicios.interfaces.UsuarioServicio;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/auth")
@AllArgsConstructor
public class AuthControlador {
    private final UsuarioServicio usuarioServicio;
    private final SesionServicio sesionServicio;

    @Operation(summary = "Iniciar sesión",
            description = "Se inicia sesión con la información especificada en el DTO, en este caso correo electrónico y contraseña.")
    @PostMapping("/login")
    public ResponseEntity<MensajeDTO> login(@Valid @RequestBody SesionDTO sesionDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(new MensajeDTO(HttpStatus.OK, false,
                sesionServicio.login(sesionDTO)));
    }

    @Operation(summary = "Crear un nuevo usuario",
            description = "Se crea un usuario con la información especificada en el DTO.")
    @PostMapping("/registro")
    public ResponseEntity<MensajeDTO> CrearUsuario(@Valid @RequestBody UsuarioDTO usuarioDTO) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new MensajeDTO(
                        HttpStatus.CREATED,
                        false,
                        usuarioServicio.crearUsuario(usuarioDTO)));
    }


}
