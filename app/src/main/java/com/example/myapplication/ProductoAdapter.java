package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myapplication.databinding.ItemProductoBinding;
import java.util.List;

public class ProductoAdapter extends RecyclerView.Adapter<ProductoAdapter.ProductoViewHolder> {

    private List<Producto> productos;

    public ProductoAdapter(List<Producto> productos) {
        this.productos = productos;
    }

    @NonNull
    @Override
    public ProductoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemProductoBinding binding = ItemProductoBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false);
        return new ProductoViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductoViewHolder holder, int position) {
        holder.bind(productos.get(position));
    }

    @Override
    public int getItemCount() {
        return productos.size();
    }

    public void actualizarLista(List<Producto> nuevaLista) {
        this.productos = nuevaLista;
        notifyDataSetChanged();
    }

    static class ProductoViewHolder extends RecyclerView.ViewHolder {
        private final ItemProductoBinding binding;

        public ProductoViewHolder(ItemProductoBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Producto producto) {
            binding.tvCodigo.setText(producto.getCodigo());
            binding.tvDescripcion.setText(producto.getDescripcion());
            binding.tvPrecio.setText(String.valueOf(producto.getPrecio()));
        }
    }
}
