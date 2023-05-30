package co.edu.uniquindio.unimarket.repositorios;

import co.edu.uniquindio.unimarket.entidades.Producto;
import co.edu.uniquindio.unimarket.entidades.enumeraciones.Categoria;
import co.edu.uniquindio.unimarket.entidades.enumeraciones.EstadoProducto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepo extends JpaRepository<Producto, Integer> {

    // listar productos por usuario
    @Query("select p from Producto p where p.usuario.idPersona = :codigoUsuario")
    List<Producto> listarProductosUsuario(int codigoUsuario);

    @Query("select p from Producto p")
    List<Producto> listarProductos();

    // listar productos por nombre
    @Query("select p from Producto p where p.nombreProducto like concat( '%', :nombre, '%') and p.estadoProducto <> 'INACTIVO'")
    List<Producto> listarProductosNombre(String nombre);


    // listar productos por categoria

    @Query("select p from Producto p where :categoria member of p.categorias and p.fechaLimite > CURRENT_DATE() and p.estadoProducto = co.edu.uniquindio.unimarket.entidades.enumeraciones.EstadoProducto.ACTIVO")
    List<Producto> listarProductosCategoria(Categoria categoria);


    // listar productos por estado moderador
    @Query("select p from ProductoModerador pm join pm.producto p where pm.estadoAutorizacion = :estadoAutorizacion")
    List<Producto> listarProductosEstado(EstadoProducto estadoAutorizacion);


    // listar productos por precio minimo y maximo
    @Query("select p from Producto p where p.precioActual between :precioMinimo and :precioMaximo and p.fechaLimite > CURRENT_DATE() and p.estadoProducto = co.edu.uniquindio.unimarket.entidades.enumeraciones.EstadoProducto.ACTIVO")
    List<Producto> listarProductosPrecio(float precioMinimo, float precioMaximo);


    // Listar favoritos de un usuario
    @Query("select f.producto from Favorito f where f.usuario.idPersona = :idUsuario and f.producto.fechaLimite > CURRENT_DATE() and f.producto.estadoProducto = co.edu.uniquindio.unimarket.entidades.enumeraciones.EstadoProducto.ACTIVO and f.usuario.idPersona = :idUsuario")
    List<Producto> listarFavoritosUsuario(int idUsuario);

    // Contar productos de las categorias
    @Query("SELECT p.categorias, COUNT(p) FROM Producto p GROUP BY p.categorias")
    List<Object[]> contarProductosPorCategoria();

    // obtener precio minimo de una categoria
    @Query("SELECT MIN(p.precioActual) FROM Producto p WHERE p.categorias = :categoria")
    float obtenerPrecioMinimoCategoria( Categoria categoria);

    // obtener precio maximo de una categoria
    @Query("SELECT MAX(p.precioActual) FROM Producto p WHERE p.categorias = :categoria")
    float obtenerPrecioMaximoCategoria( Categoria categoria);
    // Listar producto precio mayor dada una categoria
    @Query("select p from Producto p where p.precioActual = (select max(p.precioActual) from Producto p where :categoria member of p.categorias and p.fechaLimite > CURRENT_DATE() and p.estadoProducto = co.edu.uniquindio.unimarket.entidades.enumeraciones.EstadoProducto.ACTIVO)")
    List<Producto> productoPrecioMayor(Categoria categoria);

    // listar categorias
    @Query("select distinct p.categorias from Producto p")
    List<Categoria> listarCategorias();
}
