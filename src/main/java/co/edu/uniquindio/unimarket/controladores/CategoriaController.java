package co.edu.uniquindio.unimarket.controladores;// Importa las clases necesarias

import co.edu.uniquindio.unimarket.dto.MensajeDTO;
import co.edu.uniquindio.unimarket.entidades.enumeraciones.Categoria;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/categorias")
@AllArgsConstructor
public class CategoriaController {
    @Operation(summary = "Listar categorías", description = "Obtiene la lista de categorías disponibles.")
    @GetMapping("/listar")
    public ResponseEntity<MensajeDTO> listar() {
        List<Categoria> categorias = Arrays.asList(Categoria.values());

        if (categorias.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(
                    new MensajeDTO(HttpStatus.NO_CONTENT, true, "No se encontraron categorías.")
            );
        }

        return ResponseEntity.status(HttpStatus.OK).body(
                new MensajeDTO(HttpStatus.OK, false, categorias)
        );
    }
}





