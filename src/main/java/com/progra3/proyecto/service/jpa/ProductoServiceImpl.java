package com.progra3.proyecto.service.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.progra3.proyecto.entity.Producto;
import com.progra3.proyecto.entity.Restaurante;
import com.progra3.proyecto.repository.ProductoRepository;
import com.progra3.proyecto.repository.RestauranteRepository;
import com.progra3.proyecto.service.IProductoService;

@Service
public class ProductoServiceImpl implements IProductoService {

    @Autowired
    private ProductoRepository repo;

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Override
    public List<Producto> getAll() {
        return repo.findAll();
    }

    @Override
    public Producto getById(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public boolean exists(Long id) {
        return id != null && repo.existsById(id);
    }

    /**
     * Validar si el producto tiene stock disponible antes de guardar.
     * Lanza una excepción si el stock es menor o igual a 0.
     */
    private void validarStock(Producto producto) {
        if (producto.getStock() <= 0) {
            throw new IllegalArgumentException("El producto no tiene stock disponible.");
        }
    }

    /**
     * Método que valida la existencia y el stock del producto asociado a un restaurante,
     * y asigna valores predeterminados si es necesario.
     */
    @Override
    public Producto validarYAsignarAtributos(Producto producto) {
        // Asegúrate de que el restaurante esté asociado correctamente
        if (producto.getRestaurante() == null || producto.getRestaurante().getId() == null) {
            throw new IllegalArgumentException("El producto debe estar asociado a un restaurante.");
        }

        // Verificar si el restaurante existe
        Restaurante restaurante = restauranteRepository.findById(producto.getRestaurante().getId()).orElse(null);
        if (restaurante == null) {
            throw new IllegalArgumentException("No se encontró el restaurante con el ID: " + producto.getRestaurante().getId());
        }

        // Verificar existencia y stock disponible antes de continuar
        boolean stockDisponible = validarExistenciaYStock(producto.getId(), producto.getRestaurante().getId());
        if (!stockDisponible) {
            throw new IllegalArgumentException("El producto con ID " + producto.getId() + " no tiene stock disponible o no está asociado al restaurante " + restaurante.getNombre());
        }

        // Asignar atributos por defecto si es necesario
        if (producto.getPrecio() <= 0) {
            producto.setPrecio(10); // Establecer un precio por defecto
        }

        if (producto.getCategoria() == null || producto.getCategoria().isEmpty()) {
            producto.setCategoria("General"); // Establecer categoría por defecto
        }

        return producto;
    }

    @Override
    public Producto save(Producto producto) {
        // Validar que el producto tenga stock disponible antes de guardar
        validarStock(producto);

        // Validar y asignar los atributos necesarios al producto
        producto = validarYAsignarAtributos(producto); 

        // Guardar el producto validado
        return repo.save(producto); 
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }

    @Override
    public List<Producto> buscarPorNombre(String nombre) {
        return repo.findByNombre(nombre);
    }

    @Override
    public boolean validarExistenciaYStock(Long productoId, Long restauranteId) {
        // Verifica que el producto exista en el restaurante y que su stock sea mayor a 0
        return repo.existsByIdAndRestauranteIdAndStockGreaterThan(productoId, restauranteId, 0);
    }
}
