package com.example.myapplication;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ProductoViewModel extends ViewModel {

    private final MutableLiveData<List<Producto>> productos = new MutableLiveData<>(new ArrayList<>());
    private final MutableLiveData<String> mensaje = new MutableLiveData<>();

    public LiveData<List<Producto>> getProductos() {
        return productos;
    }

    public LiveData<String> getMensaje() {
        return mensaje;
    }

    // Cargar producto y emitir mensaje
    public void cargarProducto(String codigo, String descripcion, String precioStr) {
        if (codigo.isEmpty() || descripcion.isEmpty() || precioStr.isEmpty()) {
            mensaje.setValue("Por favor complete todos los campos");
            return;
        }

        double precio;
        try {
            precio = Double.parseDouble(precioStr);
        } catch (NumberFormatException e) {
            mensaje.setValue("Precio inválido");
            return;
        }

        // Evitar códigos duplicados
        for (Producto p : productos.getValue()) {
            if (p.getCodigo().equals(codigo)) {
                mensaje.setValue("El código ya existe");
                return;
            }
        }

        Producto nuevo = new Producto(codigo, descripcion, precio);
        List<Producto> lista = new ArrayList<>(productos.getValue());
        lista.add(nuevo);

        // Orden alfabético por descripción
        Collections.sort(lista, Comparator.comparing(Producto::getDescripcion));
        productos.setValue(lista);

        mensaje.setValue("Producto agregado");
    }

    // Limpiar mensaje para que no se muestre nuevamente
    public void limpiarMensaje() {
        mensaje.setValue(null);
    }

    // Para listar productos si necesitás refrescar
    public void listarProductos() {
        productos.setValue(productos.getValue());
    }
}
