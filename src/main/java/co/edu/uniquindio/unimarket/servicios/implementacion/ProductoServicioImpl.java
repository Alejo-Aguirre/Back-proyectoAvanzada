package co.edu.uniquindio.unimarket.servicios.implementacion;

import co.edu.uniquindio.unimarket.dto.ImagenDTO;
import co.edu.uniquindio.unimarket.dto.ProductoDTO;
import co.edu.uniquindio.unimarket.dto.ProductoGetDTO;
import co.edu.uniquindio.unimarket.entidades.Producto;
import co.edu.uniquindio.unimarket.entidades.Usuario;
import co.edu.uniquindio.unimarket.entidades.enumeraciones.Categoria;
import co.edu.uniquindio.unimarket.entidades.enumeraciones.EstadoProducto;
import co.edu.uniquindio.unimarket.repositorios.ProductoRepo;
import co.edu.uniquindio.unimarket.servicios.excepciones.producto.PermisoDenegadoException;
import co.edu.uniquindio.unimarket.servicios.excepciones.producto.ProductoNoEncontradoException;
import co.edu.uniquindio.unimarket.servicios.excepciones.producto.SinProductosFavoritosException;
import co.edu.uniquindio.unimarket.servicios.interfaces.ProductoServicio;
import co.edu.uniquindio.unimarket.servicios.interfaces.UsuarioServicio;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
@AllArgsConstructor
public class ProductoServicioImpl implements ProductoServicio {

    private final ProductoRepo productoRepo;
    private final UsuarioServicio usuarioServicio;

    @Override
    public int crearProducto(ProductoDTO productoDTO) throws Exception {


        Producto producto = convertir(productoDTO);

        return productoRepo.save(producto).getIdProducto();
    }

    @Override
    public ProductoGetDTO actualizarProducto(int idProducto, ProductoDTO productoDTO) throws Exception {


        validarExistenciaProducto(idProducto);

        Producto producto = convertir(productoDTO);
        producto.setIdProducto(idProducto);

        Usuario usuario = usuarioServicio.obtener(productoDTO.getIdPersona());


        if (producto.getUsuario().getIdPersona() != usuario.getIdPersona()) {
            throw new PermisoDenegadoException("No tiene permisos para actualizar este producto");
        }

        return convertir(productoRepo.save(producto));
    }

    @Override
    public void actualizarPorUnidades(int idProducto, int unidadesDisponibles) throws Exception {
        validarExistenciaProducto(idProducto);
        Producto producto = obtener(idProducto);
        producto.setUnidadesDisponibles(unidadesDisponibles);
        productoRepo.save(producto);
    }


    @Override
    public void actualizarPorEstado(int idProducto, EstadoProducto estadoAutorizacion) throws Exception {
        validarExistenciaProducto(idProducto);
        Producto producto = obtener(idProducto);
        producto.setEstadoProducto(estadoAutorizacion);
        productoRepo.save(producto);
    }

    @Override
    public float obtenerPrecioMinimoCategoria(Categoria categoria) throws Exception {
        float precioMinimo = productoRepo.obtenerPrecioMinimoCategoria(categoria);
        if (precioMinimo == 0) {
            throw new ProductoNoEncontradoException("No hay productos en esta categoría");
        }
        return precioMinimo;
    }

    @Override
    public float obtenerPrecioMaximoCategoria(Categoria categoria) throws Exception {
        float precioMaximo = productoRepo.obtenerPrecioMaximoCategoria(categoria);
        if (precioMaximo == 0) {
            throw new ProductoNoEncontradoException("No hay productos en esta categoría");
        }
        return precioMaximo;
    }
// listar categorias
    @Override
    public List<Categoria> listarCategorias() throws Exception {
        List<Categoria> lista = productoRepo.listarCategorias();
        if (lista.isEmpty()) {
            throw new ProductoNoEncontradoException("No hay productos en ninguna categoría");
        }
        return lista;
    }

    @Override
    public Map<Categoria, Integer> obtenerCantidadProductosPorCategoria() throws Exception {
        List<Object[]> resultados = productoRepo.contarProductosPorCategoria();

        if (resultados.isEmpty()) {
            throw new ProductoNoEncontradoException("No hay productos en ninguna categoría");
        }

        Map<Categoria, Integer> cantidadProductosPorCategoria = new HashMap<>();

        for (Object[] resultado : resultados) {
            Categoria categoria = (Categoria) resultado[0];
            Long cantidadProductos = (Long) resultado[1];
            cantidadProductosPorCategoria.put(categoria, cantidadProductos.intValue());
        }

        return cantidadProductosPorCategoria;
    }

    @Override
    public int eliminarProducto(int idProducto) throws Exception {
        validarExistenciaProducto(idProducto);
        productoRepo.deleteById(idProducto);
        return idProducto;
    }

    @Override
    public ProductoGetDTO obtenerProducto(int idProducto) throws Exception {
        validarExistenciaProducto(idProducto);
        return convertir(obtener(idProducto));
    }

    @Override
    public List<ProductoGetDTO> listarProductosUsuario(int idUsuario) throws Exception {

        List<Producto> lista = productoRepo.listarProductosUsuario(idUsuario);

        if (lista.isEmpty()) {
            throw new ProductoNoEncontradoException("El usuario no tiene productos");
        }

        List<ProductoGetDTO> respuesta = new ArrayList<>();

        for (Producto p : lista) {
            respuesta.add(convertir(p));
        }

        return respuesta;
    }


    @Override
    public List<ProductoGetDTO> listarProductosCategoria(Categoria categoria) throws Exception {
        List<Producto> lista = productoRepo.listarProductosCategoria(categoria);

        if (lista.isEmpty()) {
            throw new ProductoNoEncontradoException("No hay productos en esta categoria");
        }

        List<ProductoGetDTO> respuesta = new ArrayList<>();

        for (Producto p : lista) {
            respuesta.add(convertir(p));
        }

        return respuesta;
    }


    // contar productos de las categorias usando contarProductosCategoria


    @Override
    public List<ProductoGetDTO> listarProductosEstado(EstadoProducto estadoAutorizacion) throws Exception {

        List<Producto> lista = productoRepo.listarProductosEstado(estadoAutorizacion);

        if (lista.isEmpty()) {
            throw new ProductoNoEncontradoException("No hay productos con este estado");
        }

        List<ProductoGetDTO> respuesta = new ArrayList<>();

        for (Producto p : lista) {
            respuesta.add(convertir(p));
        }

        return respuesta;
    }

    @Override
    public List<ProductoGetDTO> listarFavoritosUsuarios(int idUsuario) throws Exception {

        List<Producto> lista = productoRepo.listarFavoritosUsuario(idUsuario);


        if (lista.isEmpty()) {
            throw new SinProductosFavoritosException("El usuario no tiene productos favoritos");
        }

        List<ProductoGetDTO> respuesta = new ArrayList<>();

        for (Producto p : lista) {
            respuesta.add(convertir(p));
        }

        return respuesta;
    }

    @Override
    public List<ProductoGetDTO> listarProductosNombre(String nombre) throws Exception {

        List<Producto> lista = productoRepo.listarProductosNombre(nombre);

        if (lista.isEmpty()) {
            throw new ProductoNoEncontradoException("No hay productos con este nombre");
        }
        List<ProductoGetDTO> respuesta = new ArrayList<>();

        for (Producto p : lista) {
            respuesta.add(convertir(p));
        }

        return respuesta;
    }

// obtener productos mas barato y mas caro dada una categoria

    @Override
    public List<ProductoGetDTO> listarProductosPrecio(float precioMinimo, float precioMaximo) throws Exception {

        List<Producto> lista = productoRepo.listarProductosPrecio(precioMinimo, precioMaximo);

        if (lista.isEmpty()) {
            throw new ProductoNoEncontradoException("No hay productos con este precio");
        }

        List<ProductoGetDTO> respuesta = new ArrayList<>();

        for (Producto p : lista) {
            respuesta.add(convertir(p));
        }

        return respuesta;
    }

    public Producto obtener(int idProducto) throws Exception {
        Optional<Producto> producto = productoRepo.findById(idProducto);

        if (producto.isEmpty()) {
            throw new ProductoNoEncontradoException("El código " + idProducto + " no está asociado a ningún producto");
        }
        return producto.get();
    }

    @Override
    public void actualizarPrecio(int idProducto, float precio) throws Exception {

        validarExistenciaProducto(idProducto);
        Producto producto = obtener(idProducto);
        producto.setPrecioActual(precio);
        productoRepo.save(producto);
    }

    private void validarExistenciaProducto(int idProducto) throws Exception {
        boolean existe = productoRepo.existsById(idProducto);

        if (!existe) {
            throw new ProductoNoEncontradoException("El código " + idProducto + " no está asociado a ningún producto");
        }

    }

    private ProductoGetDTO convertir(Producto producto) {

        ProductoGetDTO productoDTO = new ProductoGetDTO(
                producto.getIdProducto(),
                producto.getEstadoProducto(),
                producto.getFechaLimite(),
                producto.getNombreProducto(),
                producto.getDescripcionProducto(),
                producto.getUnidadesDisponibles(),
                producto.getPrecioActual(),
                producto.getUsuario().getIdPersona(),
                convertir(producto.getImagen()),
                producto.getCategorias()

        );

        return productoDTO;
    }

    private Producto convertir(ProductoDTO productoDTO) throws Exception {
        Producto producto = new Producto();
        producto.setNombreProducto(productoDTO.getNombreProducto());
        producto.setDescripcionProducto(productoDTO.getDescripcionProducto());
        producto.setUnidadesDisponibles(productoDTO.getUnidadesDisponibles());
        producto.setPrecioActual(productoDTO.getPrecioActual());
        producto.setUsuario(usuarioServicio.obtenerUsuarioPorId(productoDTO.getIdPersona()));
        producto.setImagen(convertir(productoDTO.getImagenes()));
        producto.setCategorias(productoDTO.getCategorias());
        producto.setEstadoProducto(EstadoProducto.SIN_REVISAR);
        producto.setFechaCreacion(LocalDateTime.now());
        producto.setFechaLimite(LocalDateTime.now().plusDays(60));

        return producto;
    }

    private Map<String, String> convertir(List<ImagenDTO> lista){
        Map<String, String> resultado = new HashMap<>();

        for (ImagenDTO imagenDTO : lista){
            String clave = imagenDTO.getIdImagen();
            String valor = imagenDTO.getUrl();
            resultado.put(clave,valor);
        }
        return resultado;
    }

    private List<ImagenDTO> convertir(Map<String, String> map){
        List<ImagenDTO> lista = new ArrayList<>();

        for (Map.Entry<String, String> entry : map.entrySet()){
            String clave = entry.getKey();
            String valor = entry.getValue();

            ImagenDTO imagenDTO = new ImagenDTO(clave, valor);
            imagenDTO.setIdImagen(clave);
            imagenDTO.setUrl(valor);

            lista.add(imagenDTO);
        }

        return lista;
    }


    @Override
    public List<ProductoGetDTO> listarProductos() throws Exception {

        List<Producto> lista = productoRepo.listarProductos();
        List<ProductoGetDTO> respuesta = new ArrayList<>();

        for (Producto p : lista){
            respuesta.add(convertir(p));
        }

        return respuesta;
    }



}
