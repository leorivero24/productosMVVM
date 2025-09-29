package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.example.myapplication.databinding.FragmentCargarBinding;

public class CargarFragment extends Fragment {

    private FragmentCargarBinding binding;
    private ProductoViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentCargarBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(requireActivity()).get(ProductoViewModel.class);

        // Observamos mensajes del ViewModel
        viewModel.getMensaje().observe(getViewLifecycleOwner(), mensaje -> {
            if (mensaje != null) {
                Toast.makeText(getContext(), mensaje, Toast.LENGTH_SHORT).show();
                viewModel.limpiarMensaje(); // <-- limpiamos mensaje después de mostrar
            }
        });

        binding.btnGuardar.setOnClickListener(v -> {
            String codigo = binding.etCodigo.getText().toString().trim();
            String descripcion = binding.etDescripcion.getText().toString().trim();
            String precioStr = binding.etPrecio.getText().toString().trim();

            viewModel.cargarProducto(codigo, descripcion, precioStr);

            // Limpiar campos después de agregar
            binding.etCodigo.setText("");
            binding.etDescripcion.setText("");
            binding.etPrecio.setText("");
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
