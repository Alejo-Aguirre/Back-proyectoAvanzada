package co.edu.uniquindio.proyecto.dto;

import co.edu.uniquindio.proyecto.entidades.Categoria;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Getter
@Setter
public class ProductoGetDTO {

    @NotNull(message = "El id del producto no puede ser nulo")
    private int idProducto;

    private boolean ACTIVO = true;


    @NotNull(message = "La fecha de publicación no puede ser nula")
    private LocalDateTime fechaLimite;

    @NotNull(message = "El nombre del producto no puede ser nulo")
    @NotBlank(message = "El nombre del producto no puede estar vacío")
    @Size(max = 100, message = "El nombre del producto no puede tener más de 100 caracteres")
    private String nombreProducto;

    @NotNull(message = "La descripción del producto no puede ser nula")
    @NotBlank(message = "La descripción del producto no puede estar vacía")
    @Size(max = 500, message = "La descripción del producto no puede tener más de 500 caracteres")
    private String descripcionProducto;

    @NotNull(message = "El precio del producto no puede ser nulo")
    @Min(value = 1, message = "El número de unidades disponibles debe ser mayor o igual a 1")
    @Max(value = 100, message = "El número de unidades disponibles no puede ser mayor a 100")
    private int unidadesDisponibles;

    @NotNull(message = "El precio del producto no puede ser nulo")
    @Positive(message = "El precio del producto debe ser mayor que cero")
    private float precioActual;

    @NotNull(message = "El id del usuario no puede ser nulo")
    private int idUsuario;

    @NotNull(message = "La lista de imágenes no puede ser nula")
    @Size(min = 1, message = "El producto debe tener al menos una imagen")
    private Map<String, String> imagenes;

    @NotNull(message = "La lista de categorías no puede ser nula")
   // @Size(min = 1, message = "El producto debe tener al menos una categoría")
    private List<Categoria> categorias;



}
