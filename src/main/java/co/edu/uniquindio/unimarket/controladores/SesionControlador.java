package co.edu.uniquindio.unimarket.controladores;

import co.edu.uniquindio.unimarket.dto.TokenDTO;
import co.edu.uniquindio.unimarket.servicios.interfaces.SesionServicio;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/sesion")
@AllArgsConstructor
public class SesionControlador {

    private final SesionServicio sesionServicio;

    public TokenDTO login(TokenDTO tokenDTO) {
        return null;
    }

    public void logout(int idUsuario) {

    }
}