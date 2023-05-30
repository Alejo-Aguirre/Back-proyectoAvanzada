package co.edu.uniquindio.unimarket.entidades;

import co.edu.uniquindio.unimarket.entidades.enumeraciones.Categoria;
import co.edu.uniquindio.unimarket.entidades.enumeraciones.EstadoProducto;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(callSuper = true)
public class Producto implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idProducto;

    @Column(nullable = false, length = 100)
    private String nombreProducto;

    @Column(nullable = false, columnDefinition = "TEXT", length = 1000)
    private String descripcionProducto;

    @Column(nullable = false)
    private float precioActual;

    @Column(nullable = false)
    private int unidadesDisponibles;

    @Column(nullable = false)
    private LocalDateTime fechaLimite;

    @Column(nullable = false)
    private LocalDateTime fechaCreacion;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoProducto estadoProducto;

    @OneToMany(mappedBy = "producto")
    private List<Descuento> descuentos;

    @ElementCollection
    @CollectionTable(name = "producto_imagen", joinColumns = @JoinColumn(name = "producto_id"))
    @MapKeyColumn(name = "imagen_key")
    @Column(name = "imagen")
    private Map<String, String> Imagen;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private List<Categoria> categorias;

    @OneToMany(mappedBy = "producto")
    @ToString.Exclude
    private List<Comentario> comentario;

    @OneToMany(mappedBy = "producto")
    @ToString.Exclude
    private List<Favorito> favorito;

    @OneToMany(mappedBy = "producto")
    @ToString.Exclude
    private List<DetalleCompra> detalleCompra;

    @OneToMany(mappedBy = "producto")
    @ToString.Exclude
    private List<ProductoModerador> productoModerador;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Usuario usuario;


}
