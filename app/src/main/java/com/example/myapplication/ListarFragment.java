package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.example.myapplication.databinding.FragmentListarBinding;
import java.util.ArrayList;

public class ListarFragment extends Fragment {

    private FragmentListarBinding binding;
    private ProductoViewModel viewModel;
    private ProductoAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentListarBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(requireActivity()).get(ProductoViewModel.class);

        adapter = new ProductoAdapter(new ArrayList<>());
        binding.rvProductos.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rvProductos.setAdapter(adapter);

        viewModel.getProductos().observe(getViewLifecycleOwner(), productos -> {
            adapter.actualizarLista(productos);
        });

        viewModel.listarProductos();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
