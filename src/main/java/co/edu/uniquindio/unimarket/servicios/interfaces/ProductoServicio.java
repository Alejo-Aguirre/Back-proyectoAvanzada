package co.edu.uniquindio.unimarket.servicios.interfaces;

import co.edu.uniquindio.unimarket.dto.ProductoDTO;
import co.edu.uniquindio.unimarket.dto.ProductoGetDTO;
import co.edu.uniquindio.unimarket.entidades.Producto;
import co.edu.uniquindio.unimarket.entidades.enumeraciones.Categoria;
import co.edu.uniquindio.unimarket.entidades.enumeraciones.EstadoProducto;

import java.util.List;
import java.util.Map;

public interface ProductoServicio {

    int crearProducto(ProductoDTO productoDTO) throws Exception;

    float obtenerPrecioMinimoCategoria(Categoria categoria) throws Exception;

    float obtenerPrecioMaximoCategoria(Categoria categoria) throws Exception;

    // listar categorias
    List<Categoria> listarCategorias() throws Exception;

    Map<Categoria, Integer> obtenerCantidadProductosPorCategoria() throws Exception;

    int eliminarProducto(int idProducto) throws Exception;

    ProductoGetDTO actualizarProducto(int idProducto, ProductoDTO productoDTO) throws Exception;


    void actualizarPorEstado(int idProducto, EstadoProducto estadoAutorizacion) throws Exception;

    void actualizarPorUnidades(int idProducto, int unidadesDisponibles) throws Exception;

    ProductoGetDTO obtenerProducto(int idProducto) throws Exception;

    List<ProductoGetDTO> listarProductosUsuario(int idUsuario) throws Exception;

    List<ProductoGetDTO> listarProductosCategoria(Categoria categoria) throws Exception;

    List<ProductoGetDTO> listarProductosEstado(EstadoProducto estadoAutorizacion) throws Exception;

    List<ProductoGetDTO> listarProductosNombre(String nombreProducto) throws Exception;

    List<ProductoGetDTO> listarProductosPrecio(float precioMinimo, float precioMaximo) throws Exception;

    List<ProductoGetDTO> listarFavoritosUsuarios(int idUsuario) throws Exception;

    Producto obtener(int idProducto) throws Exception;

    void actualizarPrecio(int idProducto, float precio) throws Exception;
    List<ProductoGetDTO> listarProductos() throws Exception;


}
