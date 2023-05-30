package co.edu.uniquindio.unimarket.dto;

import co.edu.uniquindio.unimarket.entidades.enumeraciones.Categoria;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductoDTO {

    @NotNull(message = "El nombre del producto no puede ser nulo")
    @NotBlank(message = "El nombre del producto no puede estar vacío")
    @Size(max = 100, message = "El nombre del producto no puede tener más de 100 caracteres")
    private String nombreProducto;

    @NotNull(message = "La descripción del producto no puede ser nula")
    @NotBlank(message = "La descripción del producto no puede estar vacía")
    @Size(max = 500, message = "La descripción del producto no puede tener más de 500 caracteres")
    private String descripcionProducto;

    @NotNull(message = "El precio del producto no puede ser nulo")
    @Min(value = 0, message = "El número de unidades disponibles debe ser mayor o igual a 1")
    @Max(value = 100, message = "El número de unidades disponibles no puede ser mayor a 100")
    @PositiveOrZero(message = "El número de unidades disponibles debe ser mayor o igual a cero")
    private int unidadesDisponibles;

    @NotNull(message = "El precio del producto no puede ser nulo")
    @PositiveOrZero(message = "El precio del producto debe ser mayor o igual a cero")
    private float precioActual;

    @NotNull(message = "El precio del producto no puede ser nulo")
    private int idPersona;

    @NotNull
    private List<ImagenDTO> imagenes;
    @NotNull(message = "La lista de categorías no puede ser nula")
    @Size(min = 1, message = "El producto debe tener al menos una categoría")
    private List<Categoria> categorias;


}
